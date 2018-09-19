package appium;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class AppiumMethodsExample1 {
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
			TouchAction ta=new TouchAction(driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='7']")));
			//run app in background
			driver.runAppInBackground(Duration.ofSeconds(10));
			//cheching whether the app installed or not
			if(driver.isAppInstalled("com.google.android.deskclock")) {
				System.out.println("app installed");
				//start new app
				driver.startActivity(new Activity("com.google.android.deskclock","com.android.deskclock.DeskClock"));
			}
			else 
				System.out.println("app not installed");
			//get current package name and activity name
			System.out.println(driver.getCurrentPackage()+"/"+driver.currentActivity());
			//get context handles
			Set context=driver.getContextHandles();
			System.out.println("Count of context handles is : "+context.size());
			System.out.print("Contexts are : ");
			System.out.println(context);
			//reset an app
			driver.resetApp();//it reset the initially lainched app
			//get remote address methods
			System.out.println("url is : "+driver.getRemoteAddress().getPath());//give give url after the port
			System.out.println("ipaddress is : "+driver.getRemoteAddress().getHost());//gives the ipaddress
			System.out.println("Port number is : "+driver.getRemoteAddress().getPort());//gives the port number using by appium
			System.out.println("Request type is : "+driver.getRemoteAddress().getProtocol());//gives the request type
			
			System.out.println("Device time is : "+driver.getDeviceTime());//gives device time
			System.out.println("Platform name is : "+driver.getPlatformName());//gives the platform name
			System.out.println("Display density is : "+driver.getDisplayDensity());//gives the brightness density
			
			
			driver.openNotifications();//open notifications
			ta.waitAction(Duration.ofSeconds(5)).perform();
			driver.pressKeyCode(AndroidKeyCode.HOME);//to close notifications
			//get supported performance data types
			List<String> pl=driver.getSupportedPerformanceDataTypes();
			for(String p :pl)
			{
				System.out.println(p);
			}
			//get performance data
			List<List<Object>> ci=driver.getPerformanceData("com.google.android.deskclock", "memoryinfo", 1000);
			for(int i=0;i<ci.size();i++)
			{
				for(int j=0;j<ci.get(i).size();j++)
				{
					try {
						System.out.print(ci.get(i).get(j).toString()+ "\t");
					}
					catch(Exception e1) {System.out.println(0);}
				}
			}
			//launch an initially launched app
			driver.launchApp();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='7']")));
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
