//For running more than one method at a time.
package tools_programs;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MultiThreading extends Thread {
	
	public static void main(String[] args) throws Exception {
		MultiThreading m= new MultiThreading();
		//launch site
		System.setProperty("webdriver.chrome.driver", "e:\\selenium\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		m.start();//to start execution of run method
		driver.get("https://eforms.agility.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.close();
	}
	
	public void run()
	{
		try {
			Thread.sleep(10000);
			StringSelection x=new StringSelection("admin");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(x, null);
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			StringSelection y=new StringSelection("password");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(y, null);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);			
		}
		catch(Exception ex) {}
	}
	
	

}
