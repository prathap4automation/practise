package tools_pragrams;

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

public class WayToSmsLoginTest {
	public static void main(String[] args) throws Exception {
		//Take input from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter mobile number");
		String mobile=sc.nextLine();
		System.out.println("Enter mobile criteria");
		String mobile_criteria=sc.nextLine();
		System.out.println("Enter Password");
		String pwd=sc.nextLine();
		System.out.println("Enter password criteria");
		String pwd_criteria=sc.nextLine();
		//start extent reports
		String workingDir = System.getProperty("user.dir");
		ExtentReports er=new ExtentReports(workingDir+"\\ExtentReports\\way2sms_test.html",false);
		ExtentTest et=er.startTest("way2sms login test");
		//launch browser
		System.setProperty("webdriver.chrome.driver", "e:\\selenium\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://www.way2sms.com");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Login to Way2SMS']")));
		//fill fields
		driver.findElement(By.name("mobileNo")).sendKeys(mobile);
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[contains(text(),'Login ')]")).click();
		Thread.sleep(3000);
		int flag=0;
		try {
			if(mobile.length()==0 && mobile_criteria.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//b[text()='Enter your mobile number']")).isDisplayed())
			{
				et.log(LogStatus.PASS, "Blank mobile number validation test passed",et.addScreenCapture(screenshot(driver)));
			}
			else if(mobile.length()!=0 && mobile.length()<10 && driver.findElement(By.xpath("//b[text()='Enter valid mobile number']")).isDisplayed())
			{
				et.log(LogStatus.PASS, "Invalid length mobile number test passed",et.addScreenCapture(screenshot(driver)));
			}
			else if(mobile.length()!=0 && mobile_criteria.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//*[contains(text(),'Try Again')]")).isDisplayed())
			{
				et.log(LogStatus.PASS, "Invalid mobile number test passed",et.addScreenCapture(screenshot(driver)));
			}
			else if(pwd.length()==0 && pwd_criteria.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//b[text()='Enter password']")).isDisplayed())
			{
				et.log(LogStatus.PASS, "Password blank test passed",et.addScreenCapture(screenshot(driver)));
			}
			else if(pwd.length()!=0 && pwd_criteria.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//b[contains(text(),'Try Again')]")).isDisplayed())
			{
				et.log(LogStatus.PASS, "Invalid password test passed",et.addScreenCapture(screenshot(driver)));
			}
			else if(mobile_criteria.equalsIgnoreCase("valid") && pwd_criteria.equalsIgnoreCase("valid"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//small[text()='welcome']")));
//				driver.findElement(By.xpath("(//span[text()='Current Balance'])[2]")).isDisplayed();
				et.log(LogStatus.PASS, "valid mobile and password test passed",et.addScreenCapture(screenshot(driver)));
			}
			else
			{
				et.log(LogStatus.FAIL, "Test failed, something went wrong!",et.addScreenCapture(screenshot(driver)));
			}
		}
		catch(Exception ex)
		{
			et.log(LogStatus.ERROR, "Test inturrupted "+ex.getMessage(),et.addScreenCapture(screenshot(driver)));
		}
		//close
		driver.close();
		//save and close extent report
		er.endTest(et);
		er.flush();
	}
	//screenshot
	static String screenshot(Object driver) throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
		Date d=new Date();
		String ssname="screenshots\\"+sf.format(d)+".png";
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("ExtentReports\\"+ssname));
		return ssname;
	}
}
