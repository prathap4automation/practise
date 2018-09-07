package tools_pragrams;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GoogleTitleTest {
	public static void main(String[] args) throws Exception {
		String workingDir = System.getProperty("user.dir");
		ExtentReports er=new ExtentReports(workingDir+"\\ExtentReports\\ExtentReportResults.html", false);
		ExtentTest et=er.startTest("Google Title Test");
		//take input to search in google
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a word to search");
		String searchString=sc.nextLine();
		//initiate browser
		System.setProperty("webdriver.chrome.driver", "e:\\selenium\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://www.google.co.in/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		driver.findElement(By.name("q")).sendKeys(searchString,Keys.ENTER);
		int flag=0;
		while(1==1) {
			String title=driver.getTitle();
			if(!title.contains(searchString)) {
				flag=1;
				break;
			}
			try
			{
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nobr[contains(text(),'seconds')]")));
			}
			catch(Exception ex) {
				break;
			}			
		}
		if(flag==1) {
			et.log(LogStatus.FAIL, "Google title test failed");
		}
		else {
			Date d=new Date();
			SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yy-H-mm-ss");
			String ssname=df.format(d)+".png";
			File src=driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("ExtentReports\\screenshots\\"+ssname));
			et.log(LogStatus.PASS, "Google title test passed",et.addScreenCapture("screenshots\\"+ssname));
		}
		er.endTest(et);
		er.flush();
		driver.close();
	}
}
