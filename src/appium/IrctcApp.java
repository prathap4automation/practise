package appium;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class IrctcApp {
	static AndroidDriver driver=null;
	static WebDriverWait wait=null;
	public static void main(String[] args) throws Exception {
		//desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "www.irctc.co.in.irctcrailticketbooking");
		dc.setCapability("appActivity", "www.irctc.co.in.irctcrailticketbooking.main");
		//clear cache
		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start appium
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
			TouchAction ta=new TouchAction(driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='OK']")));
			driver.findElement(By.xpath("//*[@text='OK']")).click();
			ta.waitAction(Duration.ofSeconds(10)).perform();
			driver.context("NATIVE_APP");
			ta.waitAction(Duration.ofSeconds(10)).perform();
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
//		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /K /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
