package seleniumgrid;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sun.jna.Platform;

public class GridTwo {
	public static WebDriver driver;
	
	@Test
	@Parameters({"nodeOneUrl"})
	public void test(String nodeOneUrl) throws Exception
	{
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setBrowserName("firefox");
		FirefoxOptions ffoptions = new FirefoxOptions();
		ffoptions.merge(cap);
		driver = new RemoteWebDriver(new URL(nodeOneUrl), ffoptions);
		driver.navigate().to("http://www.google.co.in");
		driver.manage().window().maximize();
		String title=driver.getTitle();
		if(title.contains("Google"))
			System.out.println("Test passed");
		else
			System.out.println("Test failed");
		driver.quit();
	}
}
