package seleniumgrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GridOne {
	public static WebDriver driver;
	@Test
	@Parameters({"nodeTwoUrl"})
	public void test(String nodeTwoUrl) throws Exception
	{
		ChromeOptions coptions=new ChromeOptions();
		driver = new RemoteWebDriver(new URL(nodeTwoUrl), coptions);
		driver.navigate().to("https://flipkart.com");
		driver.manage().window().maximize();
		String ftitle=driver.getTitle();
		if(ftitle.contains("Online Shopping Site"))
			System.out.println("Flipkart title test passed");
		else 
			System.out.println("Flipkart title test failed");
		Thread.sleep(3000);
		driver.quit();
	}
}
