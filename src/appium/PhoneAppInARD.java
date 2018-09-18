package appium;

import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PhoneAppInARD {
	static AndroidDriver driver=null;
	static WebDriverWait wait=null;
	static String phone;
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		restart:
			while(true) {
				System.out.println("Enter phone number");
				phone=sc.nextLine();
				String phoneRegex="^[0-9]{10}$|^[0-9]{3}$";
				Pattern p=Pattern.compile(phoneRegex);
				Matcher m=p.matcher(phone);
				if(!m.find()) {
					System.out.println("Enter a valid phone number");
					continue restart;
				}
				else {
					break;
				}
			}		
		//desired capabilites
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.android.dialer");
		dc.setCapability("appActivity", "com.android.dialer.DialtactsActivity");
		//clear cache
		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start appium server
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
			wait=new WebDriverWait(driver,40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Recents']")));
			driver.findElement(By.xpath("//*[@content-desc='dial pad']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='2,ABC']")));
			//enter phone number
			for(int i=0;i<phone.length();i++)
			{
				char c=phone.charAt(i);
				driver.findElement(By.xpath("//*[contains(@content-desc,"+c+")]")).click();
			}			
			//click dial
			driver.findElement(By.xpath("//*[@content-desc='dial']")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='Dialling']")));
			driver.findElement(By.xpath("//*[@content-desc='Speaker']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='00:30']")));
			driver.findElement(By.xpath("//*[@content-desc='End Call']")).click();
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close app
		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
