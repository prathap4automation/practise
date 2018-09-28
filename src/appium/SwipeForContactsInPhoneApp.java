package appium;

import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class SwipeForContactsInPhoneApp {
	static AndroidDriver driver=null;
	static WebDriverWait wait=null;
	static WebDriverWait search=null;
	public static void main(String[] args) throws Exception {
		//take 2 contact names
		//starting contact in your mobile contacts app
		String startingContact="Mallipeddi Prathap";
		//Last contact in your mobile
		String lastContact="Yesvanth@mindq";
		Scanner sc=new Scanner(System.in);
		String firstContact="Annaya AP";
		String secondContact="Kishore Chegu";
		//desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.android.contacts");
		dc.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		//clear cache
		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start appium
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		URL u = new URL("http://0.0.0.0:4723/wd/hub");
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='All contacts']")));
			int w=driver.manage().window().getSize().getWidth();
			int h=driver.manage().window().getSize().getHeight();
			TouchAction ta=new TouchAction(driver);
			//find which contact is greater and find it in app
			String first=null;
			String second=null;
			if(firstContact.charAt(0)==secondContact.charAt(0)) {
				if(firstContact.charAt(1)==secondContact.charAt(1)) {
					if(firstContact.charAt(2)>secondContact.charAt(2)) {
						first=firstContact;
						second=secondContact;
					}
					else {
						first=secondContact;
						second=firstContact;
					}
				}
				else if(firstContact.charAt(1)>secondContact.charAt(1)) {
					first=firstContact;
					second=secondContact;
				}
				else {
					first=secondContact;
					second=firstContact;
				}
			}
			else if(firstContact.charAt(0)>secondContact.charAt(0)) {
				first=firstContact;
				second=secondContact;
			}
			else {
				first=secondContact;
				second=firstContact;
			}
			//first finding the last contact for bottom to top swipe
			//swipe bottom to top
			int x1=(int)(w/2);
			int y1=(int)(h*0.9);//near to bottom
			int x2=x1;
			int y2=(int)(h*0.4);//near to center from top
			while(1==1) {
				try {
					search.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+first+"']")));
					break;
				}
				catch(Exception e1)
				{
					if(!driver.findElements(By.xpath("//*[@text='"+lastContact+"']")).isEmpty())
					{
						System.out.println(first+" contact not found");
						break;
					}
					ta.press(x1, y1).waitAction(Duration.ofSeconds(1)).moveTo(x2,y2).release().perform();
				}
			}
			//finding the first contact to swipe top to bottom
			int xx1=(int)(w/2);
			int yy1=(int)(h*0.3);
			int xx2=xx1;
			int yy2=(int)(h*0.7);
			while(1==1) {
				try {
					search.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='"+second+"']")));
					break;
				}
				catch(Exception e2) {
					if(!driver.findElements(By.xpath("//*[@text='"+startingContact+"']")).isEmpty()) {
						System.out.println(second+" contact not found");
						break;
					}
					ta.press(xx1,yy1).waitAction(Duration.ofSeconds(1)).moveTo(xx2, yy2).release().perform();
				}
			}
			System.out.println("Test passed");
		}
		catch(Exception ex) { System.out.println(ex.getMessage());}
		//close app
		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
