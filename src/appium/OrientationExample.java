package appium;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class OrientationExample {
	static AndroidDriver driver=null;
	static WebDriverWait wait=null;
	public static void main(String[] args) throws Exception {
		//desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		//clear cache
		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start appium
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//create driver object
		while(1==1) {
			try {
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception e) {}
		}
		//automation code
		try {
			wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='7']")));
			driver.pressKeyCode(AndroidKeyCode.HOME);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Apps']")));
			//re launch app
			driver.launchApp();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='7']")));
			String o=driver.getOrientation().name();
			TouchAction ta =new TouchAction(driver);
			//rotate screen
			if(o=="LANDSCAPE") {
				driver.rotate(ScreenOrientation.PORTRAIT);
				ta.waitAction(Duration.ofSeconds(5)).perform();//for wait purpose
			}
			else {
				driver.rotate(ScreenOrientation.LANDSCAPE);
				ta.waitAction(Duration.ofSeconds(5)).perform();//for wait purpose
			}
			driver.rotate(ScreenOrientation.valueOf(o));
			ta.waitAction(Duration.ofSeconds(5)).perform();//for wait purpose
			//lock device
//			driver.lockDevice();
//			ta.waitAction(Duration.ofSeconds(5)).perform();//for wait purpose
//			//unlock device
//			if(driver.isLocked())
//				driver.unlockDevice();
			//Goto home screen
			driver.pressKeyCode(AndroidKeyCode.HOME);
			//hide keyboard
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Voice Search']")));
			driver.findElement(By.xpath("//*[@content-desc='Voice Search']")).click();
			ta.waitAction(Duration.ofSeconds(5)).perform();
			if(driver.isKeyboardShown())
				driver.hideKeyboard();
//			ta.waitAction(Duration.ofSeconds(5)).perform();
//			driver.key
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
		driver.closeApp();
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
