package seleniumgrid;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridTwo {
	public static WebDriver driver;
	static String nodeOneUrl="http://192.168.0.101:5566/wd/hub";
	static String nodeTwoUrl="http://192.168.0.101:5577/wd/hub";
	
	public static void main(String[] args) throws Exception
	{
		//node one
		FirefoxOptions ffoptions = new FirefoxOptions();
		driver = new RemoteWebDriver(new URL(nodeOneUrl), ffoptions);
		driver.navigate().to("http://www.google.co.in");
		driver.manage().window().maximize();
		String title=driver.getTitle();
		if(title.contains("Google"))
			System.out.println("Test passed");
		else
			System.out.println("Test failed");
		Thread.sleep(3000);
		driver.quit();
		
		//node two
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
