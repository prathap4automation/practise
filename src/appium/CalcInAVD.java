package appium;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class CalcInAVD {

	public static void main(String[] args) throws Exception {
		//Scanner for 2 inputs
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first number");
		String x=sc.nextLine();
		System.out.println("Enter second number");
		String y=sc.nextLine();
		//assign capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "4.2.2");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		//start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		//url
		URL u=new URL("http://0.0.0.0:4723/wd/hub");		
		//Android driver
		AndroidDriver driver=null;
		while(1==1)
		{
			try {
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception ex)
			{
				
			}
		}
		//Automation
		try {
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='9']")));
			//Enter first number
			for(int i=0;i<x.length();i++)
			{
				char d=x.charAt(i);
				driver.findElement(By.xpath("//*[@text='"+d+"']")).click();	
			}
			//click plus
			driver.findElement(By.xpath("//*[@content-desc='plus']")).click();
			//enter second number
			for(int j=0;j<y.length();j++) {
				char d=y.charAt(j);
				driver.findElement(By.xpath("//*[@text='"+d+"']")).click();
			}
			//click equals
			driver.findElement(By.xpath("//*[@content-desc='equals']")).click();
			//get value
			String val=driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).getAttribute("text");
			System.out.println("value : "+val);
			if(Integer.parseInt(val)==Integer.parseInt(x)+Integer.parseInt(y))
			{
				System.out.println("Test passed");
				File src=driver.getScreenshotAs(OutputType.FILE);
				File dest=new File("test_pass.png");
				FileUtils.copyFile(src, dest);
			}
			else
			{
				System.out.println("Test failure");
				File src=driver.getScreenshotAs(OutputType.FILE);
				File dest=new File("test_fail.png");
				FileUtils.copyFile(src, dest);
			}
			//close
			driver.closeApp();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}		
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}
