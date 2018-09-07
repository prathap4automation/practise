package tools_programs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class gmailLoginTest {

	public static void main(String[] args) throws Exception {
		//scanner 
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter userid");
		String userid=sc.nextLine();
		System.out.println("Enter userid criteria");
		String userid_criteria=sc.nextLine();
		if(!userid_criteria.contentEquals("valid") && !userid_criteria.contentEquals("invalid")) {
			System.out.println("Enter valid/invalid userid criteria");
			System.exit(0);
		}
		String pwd=null;
		String pwd_criteria=null;
		if(userid_criteria.equalsIgnoreCase("valid"))
		{
			System.out.println("Enter password");
			pwd=sc.nextLine();
			System.out.println("Enter password criteria");
			pwd_criteria=sc.nextLine();
			if(!pwd_criteria.equalsIgnoreCase("valid") && !pwd_criteria.equalsIgnoreCase("invalid")) {
				System.out.println("Enter valid/invalid userid criteria");
				System.exit(0);
			}
		}		
		//extent report
		ExtentReports report=new ExtentReports("ExtentReports\\gmail_login_test.html",false);
		ExtentTest test=report.startTest("Gmail login Test");
		//launch
		System.setProperty("webdriver.chrome.driver", "e:\\selenium\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://www.gmail.com/");
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
		driver.manage().window().maximize();
		//fill user id
		driver.findElement(By.name("identifier")).sendKeys(userid);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		Thread.sleep(3000);
		//validation
		try {
			if(userid_criteria.equalsIgnoreCase("invalid"))
			{
				if(userid.length()==0 && driver.findElement(By.xpath("//div[text()='Enter an email or phone number']")).isDisplayed())
					test.log(LogStatus.PASS, "blank userid test passed");
				else if(driver.findElement(By.xpath("(//*[contains(text(),'Could')])[2]")).isDisplayed())
					test.log(LogStatus.PASS, "Invalid userid test passed");
				else 
					test.log(LogStatus.FAIL, "userid test failed",test.addScreenCapture(screenshot(driver)));
			}
			else {
				//fill password
				driver.findElement(By.name("password")).sendKeys(pwd);
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(2000);
				if(pwd_criteria.equalsIgnoreCase("invalid"))
				{
					if(pwd.length()==0 && driver.findElement(By.xpath("//div[text()='Enter a password']")).isDisplayed())
						test.log(LogStatus.PASS, "blank password test passed");
					else if(driver.findElement(By.xpath("//div[contains(text(),'Wrong password')]")).isDisplayed())
						test.log(LogStatus.PASS, "Invalid password test passed");
					else 
						test.log(LogStatus.FAIL, "password test failed");
				}
				else {
					if(driver.findElement(By.xpath("//div[text()='Compose']")).isDisplayed())
						test.log(LogStatus.PASS, "Google login test passed");
					else
						test.log(LogStatus.ERROR, "Google title test failed, somthing went wrong",test.addScreenCapture(screenshot(driver)));
				}					
			}
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, "gmail login test inturrupted",test.addScreenCapture(screenshot(driver)));
		}
		//close
		driver.close();
		//save and close report
		report.endTest(test);
		report.flush();
	}
	//screen shot
	static String screenshot(Object driver) throws Exception
	{
		Date d=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yy-H-mm-ss");
		String ssname="screenshots\\"+sf.format(d)+".png";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("ExtentReports\\"+ssname));
		return ssname;
	}
}
