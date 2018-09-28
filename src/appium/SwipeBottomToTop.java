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

public class SwipeBottomToTop {
	static AndroidDriver driver;
	static WebDriverWait wait;
	public static void main(String[] args) throws Exception {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.google.android.youtube");
		dc.setCapability("appActivity", "com.google.android.apps.youtube.app.WatchWhileActivity");
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
		//automation code start
		try {
			wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='YouTube']")));
			//get height and width of mobile screen
			int h=driver.manage().window().getSize().getHeight();
			int w=driver.manage().window().getSize().getWidth();
			//swipte bottom to top
			int x1=(int)(w/2);
			int y1=(int)(h*0.9);//near to bottom
			int x2=x1;
			int y2=(int)(h*0.4);
			TouchAction ta=new TouchAction(driver);
			for(int i=0;i<3;i++) {
				ta.press(x1, y1).waitAction(Duration.ofSeconds(3)).moveTo(x2, y2).release().perform();
				ta.waitAction(Duration.ofSeconds(5)).perform();
			}
			
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		
	}

}
