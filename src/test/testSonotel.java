package test;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testSonotel {
	static WebDriver driver=null;
	static WebDriverWait wait=null;
	public static void main(String[] args) {
		//take bro
		Scanner sc=new Scanner(System.in);
		restart:
			while(true) {
				System.out.println("please select browser \n 1 for chrome \n 2 for firefox \n 3 for internet explorer \n 4 for opera");
				int browser=sc.nextInt();
				switch(browser)
				{
				case 1:
					System.setProperty("webdriver.chrome.driver","e:\\selenium\\chromedriver.exe");
					driver = new ChromeDriver();
					break;
				case 2:
					System.setProperty("webdriver.gecko.driver","e:\\selenium\\geckodriver.exe");
					driver = new FirefoxDriver();
					break;
				case 3:
					System.setProperty("webdriver.ie.driver", "e:\\selenium\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					break;
				case 4:
					OperaOptions o=new OperaOptions();
					o.setBinary("C:\\Program Files\\Opera\\55.0.2994.61\\opera.exe");
					System.setProperty("webdriver.opera.driver", "E:\\selenium\\operadriver_win64\\operadriver.exe");
					driver = new OperaDriver(o);
					break;
				default:
					System.out.println("invalid browser");
					continue restart;
				}
				break;
			}
		try {
			wait = new WebDriverWait(driver,20);
			driver.manage().window().maximize();
			driver.get("https://sonetel.com/en/");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Try free')]")));
			driver.findElement(By.xpath("//a[contains(text(),'Try free')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'Price if you keep this number')]")));
			String packageName=driver.findElement(By.xpath("//table/tbody/tr/td/div/div/span")).getText();
			String perMonthPrice=driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]")).getText();
			String callForward=driver.findElement(By.xpath("//table/tbody/tr[5]/td[2]")).getText();
			System.out.println(packageName+" "+perMonthPrice+" "+callForward);
			Actions a=new Actions(driver);
			WebElement dropdown=driver.findElement(By.xpath("//table/tbody/tr/td/div"));
			a.click(dropdown).build().perform();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[text()='Try free']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));
			driver.findElement(By.id("firstname")).sendKeys("prathap");
			driver.findElement(By.id("lastname")).sendKeys("mallipeddi");
			driver.findElement(By.id("email")).sendKeys("prathapmallipeddi@gmail.com");
			driver.findElement(By.id("phonenumber")).sendKeys("9885675068");
			driver.findElement(By.id("password")).sendKeys("test123");
			driver.findElement(By.xpath("//div[text()='Verify my mobile']")).click();
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		//close site
		driver.close();
	}

}
