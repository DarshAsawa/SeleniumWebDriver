 package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IEDriver {
	
	public WebDriver driver;
	public static String baseURL="https://www.amazon.in";
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Executing Before Test....");
		System.setProperty("webdriver.ie.driver", "M:\\WebTesting\\IEdriver\\IEDriverServer.exe");

		driver=new InternetExplorerDriver();
		driver.manage().window().maximize();//to maximize the window.	
	}
	@Test
	public void search() throws InterruptedException {
		System.out.println("Searching over Amazon...");
		driver.navigate().to(baseURL);
		
		//Element to search in Amazon..
		String searchElement="Samsung TV";
		
		Thread.sleep(1000);
		WebElement searchbox=driver.findElement(By.id("twotabsearchtextbox"));
		searchbox.sendKeys(searchElement);
		
		WebElement searchicon=driver.findElement(By.className("nav-search-submit nav-sprite nav-focus"));
		searchicon.click();
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
}
