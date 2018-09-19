package appium;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class SetLocationExample {
	static AndroidDriver driver=null;
	static WebDriverWait wait=null;
	public static void main(String[] args) throws Exception {
		//capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.google.android.apps.maps");
		dc.setCapability("appActivity", "com.google.android.maps.MapsActivity");
		//clean cache
		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
//		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//create driver object
		while(1==1) {
			try {
				driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),dc);
				break;
			}
			catch(Exception e) {}
		}
		//automation code
		try {
			wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='GO']")));
			TouchAction ta=new TouchAction(driver);
			ta.waitAction(Duration.ofSeconds(5)).perform();
			Location l=new Location(27.1750151,78.04215520000002,500);
			driver.setLocation(l);
			ta.waitAction(Duration.ofSeconds(20)).perform();
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
