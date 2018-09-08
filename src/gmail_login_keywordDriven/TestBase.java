package gmail_login_keywordDriven;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	public WebDriver driver=null;
	public WebDriverWait wait=null;
	void driverCheck()
	{
		if(driver==null)
		{
			System.out.println("driver not initiated");
			System.exit(0);
		}
	}
	//launch
	public String launch(String e,String d,String c)
	{
		if(driver==null) 
		{
			switch(e)
			{
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "e:\\selenium\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "e:\\selenium\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", "e:\\selenium\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			case "opera":
				OperaOptions options=new OperaOptions();
				options.setBinary("C:\\Program Files\\Opera\\55.0.2994.44\\opera.exe");
				System.setProperty("webdriver.opera.driver", "e:\\selenium\\operadriver.exe");
				driver = new OperaDriver(options);
				break;
			}
			wait = new WebDriverWait(driver,20);
			driver.manage().window().maximize();
			driver.get("http://www.gmail.com/");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//content[text()='Continue to Gmail']")));			
		}
		return "Done";
	}
	//fill
	public String fill(String e,String d,String c) throws Exception
	{
		this.driverCheck();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(e)));
			driver.findElement(By.xpath(e)).sendKeys(d);
		}
		catch(Exception ex) {
			return "Test failed goto "+this.screenshot()+".png";
		}
		return "Done";
	}
	//click
	public String click(String e,String d,String c) throws Exception
	{
		this.driverCheck();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(e)));
			driver.findElement(By.xpath(e)).click();
			Thread.sleep(3000);
		}
		catch(Exception ex) {
			return "Test inturupted goto "+this.screenshot()+".png";
		}
		return "Done";
	}
	//close
	public String close(String e,String d,String c)
	{
		this.driverCheck();
		driver.close();
		driver=null;
		wait=null;
		return "Done";
	}
	//screen shot
	String screenshot() throws Exception
	{
		this.driverCheck();
		Date d=new Date();
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
		String ssname="screenshots\\"+df.format(d)+".png";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(ssname));
		return ssname;
	}
	//validate Login
	public String validateLogin(String e,String d,String c) throws Exception
	{
		this.driverCheck();
		try {
			if((c.equals("all_valid")) && (driver.findElement(By.xpath("//*[text()='Compose']")).isDisplayed()))
				return "Passed";
			else if(c.equals("userid_blank") && driver.findElement(By.xpath("//div[text()='Enter an email or phone number']")).isDisplayed())
				return "Passed";
			else if(c.equals("userid_invalid") && driver.findElement(By.xpath("(//*[contains(text(),'Could')])[2]")).isDisplayed())
				return "Passed";
			else if(c.equals("pwd_blank") && driver.findElement(By.xpath("//div[text()='Enter a password']")).isDisplayed())
				return "Passed";
			else if(c.equals("pwd_invalid") && driver.findElement(By.xpath("//*[contains(text(),'Wrong password')]")).isDisplayed())
				return "Passed";
			else {
				return "Test failed and goto "+this.screenshot()+".png";
			}
		}
		catch(Exception ex)
		{
			return "Test inturrupted & goto screenshots folder "+this.screenshot()+".png";
		}
	}
}
