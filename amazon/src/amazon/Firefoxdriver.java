package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Firefoxdriver {
	
	public static WebDriver driver;
	public String baseURL="https://www.amazon.in";
	

@BeforeTest
public void beforetest() {
	
	//system property needs modification according to your file paths, as it is responsible for execution and handling of web drivers.
	System.setProperty("webdriver.gecko.driver", "M:\\WebTesting\\firefoxdriver\\geckodriver.exe");

	driver=new FirefoxDriver();
	driver.manage().window().maximize();//to maximize the window.	
	
	driver.get(baseURL);//to open amazon in the browser.
}

//Search functionality..
@Test
public void search() throws InterruptedException {
	//Searching for dell using search bar.
	WebElement element1 = driver.findElement(By.id("twotabsearchtextbox"));
	element1.sendKeys("Dell");
	element1.sendKeys(Keys.ENTER);
	
	Thread.sleep(2000);//letting the page load properly in case of slow connections.
	
	//using a partial text in order to search for core i5 8th Gen dell laptops..
	WebElement element2 = driver.findElement(By.partialLinkText("Core i5 8th gen"));
	element2.click();
	
	
}

@AfterTest
public void afertest() throws InterruptedException {
	Thread.sleep(2000);
	driver.close();
}

}
