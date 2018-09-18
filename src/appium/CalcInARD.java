package appium;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class CalcInARD {
	static AndroidDriver driver=null;
	static WebDriverWait wait=null;
	public static void main(String[] args) throws Exception {
		//take 2 inputs
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first input");
		String input1=sc.nextLine();
		System.out.println("Enter second input2");
		String input2=sc.nextLine();
		//desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "ZY222Z99XV");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "6.0.1");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		//clean cache
//		Runtime.getRuntime().exec("cmd.exe npm cache clear -f");
		//start apium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//create object
		while(1==1) {
			try {
				driver = new AndroidDriver(u,dc);
				break;
			}
			catch(Exception e) {}
		}
		//automation code
		try {
			System.out.println("automation code started");
			wait = new WebDriverWait(driver,20);
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
			for(int i=0;i<input2.length();i++)
			{
				char c=input2.charAt(i);
				driver.findElement(By.xpath("//*[@text='"+c+"']")).click();
			}
			//click equals to
			driver.findElement(By.xpath("//*[@text='=']")).click();
			//get result
			String result=driver.findElement(By.xpath("//*[@resource-id='com.android.calculator2:id/formula']")).getAttribute("text");
			System.out.println("Rsult of addition of "+input1+" and "+input2+" is : "+result);
			if(Integer.parseInt(result)==(Integer.parseInt(input1)+Integer.parseInt(input2)))
				System.out.println("Test passed");
			else
				System.out.println("Test Failed");
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		//close app
		driver.closeApp();
		//stop appium
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
//	static void enterInput(char c)
//	{
//		AndroidDriver driver=(AndroidDriver)driver;
//		
//		driver.findElement(By.xpath("//*[@text='"+c+"']")).click();
//		
//	}

}
