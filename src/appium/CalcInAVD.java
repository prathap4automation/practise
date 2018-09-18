package appium;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class CalcInAVD {
	static AndroidDriver driver=null;
	public static void main(String[] args) throws Exception {
		//take 2 inputs from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first input");
		String input1=sc.nextLine();
		System.out.println("Enter second input");
		String input2=sc.nextLine();
		//desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emilator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "4.2.2");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		//clear cache
		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		URL u = new URL("http://0.0.0.0:4723/wd/hub");
		//create driver object for android driver
		while(1==1) {
			try {
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception e) {}
		}
		//start automation code
		try {
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='7']")));
			//enter first input
			for(int i=0;i<input1.length();i++)
			{
				char c=input1.charAt(i);
				driver.findElement(By.xpath("//*[@text='"+c+"']")).click();				
			}
			//click add
			driver.findElement(By.xpath("//*[@text='+']")).click();
			//enter second input
			for(int i=0;i<input2.length();i++) {
				char c=input2.charAt(i);
				driver.findElement(By.xpath("//*[@text='"+c+"']")).click();
			}
			//click equals to
			driver.findElement(By.xpath("//*[@text='=']")).click();
			//get result
			String result=driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).getAttribute("text");
			System.out.println("addition of "+input1+" and "+input2+" result is: "+result);
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		//close app
		driver.closeApp();
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
