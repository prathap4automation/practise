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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderInSameCls {
	static ChromeDriver driver=null;
	static WebDriverWait wait=null;
	@DataProvider(name="searchword")
	public Object[][] testdata()
	{
		return new Object[][]
		{
			{"kalam"},
			{"appium"},
			{"selenium"}
		};		
	}
	
	@BeforeMethod
	public void setup() throws Exception
	{
		if(driver==null) {
			System.setProperty("webdriver.chrome.driver", "e:\\selenium\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://google.co.in");
			wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		}
	}
	
	@Test(dataProvider="searchword")
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
			String cwd=System.getProperty("user.dir");
			String path="<img src=\""+cwd+"\\screenshots\\"+ssname+"\" alt=\"failed image\" />";
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
