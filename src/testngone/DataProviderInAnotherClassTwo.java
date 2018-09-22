package testngone;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataProviderInAnotherClassTwo {
	static ChromeDriver driver=null;
	static WebDriverWait wait=null;
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "e:\\selenium\\chromedriver.exe");
		driver=new ChromeDriver();
		wait=new WebDriverWait(driver,20);
		driver.manage().window().maximize();
		driver.get("http://www.google.co.in");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
	}
	
	@Test(dataProvider="searchword",dataProviderClass=DataProviderInAnotherClassOne.class)
	public void search(String x) throws Exception
	{
		driver.findElement(By.name("q")).sendKeys(x,Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='All']")));
		String title=driver.getTitle();
		if(title.contains(x))
			Assert.assertTrue("Google title test passed", true);
		else {
			SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yy-HH-mm-ss");
			String ssname=sf.format(new Date())+".png";
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("screenshots\\"+ssname));
			String path="<img src=\""+System.getProperty("user.dir")+"\\screenshots\\"+ssname+"\" alt=\"\" />";
			Reporter.log(path);
			Assert.assertTrue("Google title test failed", false);
		}
	}
	
	@AfterMethod
	public void close()
	{
		driver.close();
		driver=null;
		wait=null;
	}
}
