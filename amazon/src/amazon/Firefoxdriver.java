package amazon;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Firefoxdriver {
	
	public static WebDriver driver;
	public String baseURL="https://www.amazon.in";
	public String searchelement = null;
	Scanner scan;
	

@BeforeTest
public void beforetest() {
	//system property needs modification according to your file paths, as it is responsible for execution and handling of web drivers.
	System.setProperty("webdriver.gecko.driver", "M:\\WebTesting\\firefoxdriver\\geckodriver.exe");
	driver=new FirefoxDriver();
	driver.get(baseURL);
	driver.manage().window().maximize();//to maximize the window.	
	
	
}
@BeforeMethod
public void searchElement() {
	//Method for asking user for search element before every test case
	scan = new Scanner(System.in);
	System.out.println("Enter the product name for searching : ");
	searchelement = scan.nextLine();
}

//Search functionality..
@Test
public void search() throws InterruptedException {
	//Searching for dell using search bar.
	WebElement element1 = driver.findElement(By.id("twotabsearchtextbox"));
	element1.sendKeys(searchelement);
	element1.sendKeys(Keys.ENTER);
	
	Thread.sleep(2000);//letting the page load properly in case of slow connections.
	
	//Only asking for prime supported items only ...
	WebElement element2 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/div[3]/span/div/div[1]/ul/li/span/a/i"));
	element2.click();
		
}

@AfterMethod 
public void afterSearch() {
	scan.close();
}

@AfterTest
public void afertest() throws InterruptedException {
	Thread.sleep(2000);
	driver.close();
}

}
