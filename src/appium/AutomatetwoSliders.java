package appium;

import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class AutomatetwoSliders {
	static AndroidDriver driver;
	static WebDriverWait wait;
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
			wait = new WebDriverWait(driver,40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));
			driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Slider']")));
			driver.findElement(By.xpath("//*[@text='Slider']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='slider']")));
			MobileElement m1=(MobileElement)driver.findElement(By.xpath("//*[@content-desc='slider']"));
			MobileElement m2=(MobileElement)driver.findElement(By.xpath("//*[@content-desc='slider1']"));
			//swipte left to right
			Duration d=Duration.of(10, ChronoUnit.SECONDS);
			//first element co-ordinates
			int e1x1=m1.getLocation().getX();
			int e1y1=m1.getLocation().getY();
			int e1x2=e1x1+150;
			int e1y2=e1y1;
			TouchAction ta1=new TouchAction(driver);
			ta1.press(e1x1, e1y1).waitAction(d).moveTo(e1x2, e1y2).release();
			//second element co-ordinates
			int e2x1=m2.getLocation().getX();
			int e2y1=m2.getLocation().getY();
			int e2x2=e2x1+150;
			int e2y2=e2y1;
			TouchAction ta2=new TouchAction(driver);
			ta2.press(e2x1,e2y1).waitAction(d).moveTo(e2x2, e2y2).release();
			MultiTouchAction mul=new MultiTouchAction(driver);
			mul.add(ta1).add(ta2).perform();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='0']")));
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
