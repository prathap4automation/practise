package appium;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class DoubletapInVodqa {
	static AndroidDriver driver;
	static WebDriverWait wait;
	static WebDriverWait search;
	public static void main(String[] args) throws Exception {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.vodqareactnative");
		dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
		//clear cache
		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//create driver object
		while(1==1) {
			try {
				driver = new AndroidDriver(u,dc);
				break;
			}
			catch(Exception e) {}
		}
		//automation code
		try {
			wait = new WebDriverWait(driver,20);
			search = new WebDriverWait(driver,3);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));
			driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Double Tap']")));
			driver.findElement(By.xpath("//*[@text='Double Tap']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Double Tap Me']")));
			MobileElement m=(MobileElement)driver.findElement(By.xpath("//*[@text='Double Tap Me']"));
			TouchAction ta1=new TouchAction(driver);
			ta1.tap(m);
			TouchAction ta2=new TouchAction(driver);
			ta2.waitAction(Duration.ofMillis(100)).tap(m);
			MultiTouchAction mlt=new MultiTouchAction(driver);
			mlt.add(ta1).add(ta2).perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Double tap successful!']")));
			System.out.println("Test passed");
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
