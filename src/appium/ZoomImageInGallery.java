package appium;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class ZoomImageInGallery {
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
			TouchAction ta=new TouchAction(driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));
			driver.pressKeyCode(AndroidKeyCode.HOME);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Apps']")));
			driver.findElement(By.xpath("//*[@content-desc='Apps']")).click();
			//swipe bottom to top to get gallery
			int h=driver.manage().window().getSize().getHeight();
			int w=driver.manage().window().getSize().getWidth();
			int x1=(int)(w/2);
			int y1=(int)(h*0.9);
			int x2=x1;
			int y2=(int)(h*0.4);
			while(2==2) {
				try {
					search.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Gallery']")));
					driver.findElement(By.xpath("//*[@text='Gallery']")).click();
					break;
				}
				catch(Exception e1) {
					if(!driver.findElements(By.xpath("//*[@text='µTorrent']")).isEmpty())
					{
						System.out.println("Gallery not found");
						break;
					}
					TouchAction ta1=new TouchAction(driver);
					ta1.press(x1, y1).waitAction(Duration.ofSeconds(2)).moveTo(x2, y2).release().perform();
				}
			}
			ta.waitAction(Duration.ofSeconds(3)).perform();
			int a1=(int)(w*0.2);
			int b1=(int)(h*0.2);
			ta.press(a1, b1).release().perform();
			ta.waitAction(Duration.ofSeconds(3)).perform();
			ta.press(a1, b1).release().perform();
			ta.waitAction(Duration.ofSeconds(3)).perform();
			//for right hand side
			int r_x1=482;
			int r_y1=598;
			int r_x2=614;
			int r_y2=545;
			TouchAction ta2=new TouchAction(driver);
			ta2.press(r_x1, r_y1).waitAction(Duration.ofMillis(3)).moveTo(r_x2, r_y2).release();
			//for left hand side
			int l_x1=222;
			int l_y1=658;
			int l_x2=96;
			int l_y2=806;
			TouchAction ta3=new TouchAction(driver);
			ta3.press(l_x1, l_y1).waitAction(Duration.ofMillis(3)).moveTo(l_x2, l_y2).release();
			MultiTouchAction m=new MultiTouchAction(driver);
			m.add(ta2).add(ta3).perform();
			ta.waitAction(Duration.ofSeconds(10)).perform();
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			List<WebElement> list=driver.findElements(By.xpath("//*[@resource-id='com.android.systemui:id/task_view_thumbnail']"));
//			System.out.println(list.size());
//			WebElement e=list.get(list.size()-1);
			driver.findElement(By.xpath("//*[@content-desc='Dismiss Gallery.'[@index='"+(list.size()-1)+"']]")).click();
			ta.waitAction(Duration.ofSeconds(10)).perform();
			driver.pressKeyCode(AndroidKeyCode.HOME);
			driver.launchApp();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']")));
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
//		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
