package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Scanner;
public class Chromedriver {
	public static WebDriver driver;
	public String email;
	public String password;
	

@BeforeTest
public void beforetest() {
	System.setProperty("webdriver.chrome.driver", "M:\\WebTesting\\chromedriver\\chromedriver.exe");
	
	Scanner scan=new Scanner(System.in);
	System.out.print("Enter email or phone number:\n");
	email=scan.nextLine();
	
	System.out.print("Enter password: \n");
	password=scan.nextLine();
	
	scan.close();
	
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	
}

@Test
public void login() throws InterruptedException {
	
	driver.navigate().to("https://www.amazon.in");
	
	WebElement element= driver.findElement(By.id("nav-signin-tooltip"));
	element.click();

	WebElement element2=driver.findElement(By.id("ap_email"));
	element2.sendKeys(email);
	
	WebElement element3=driver.findElement(By.id("continue"));
	element3.click();
	
	WebElement element4=driver.findElement(By.id("ap_password"));
	element4.sendKeys(password);
	
	WebElement element5= driver.findElement(By.id("signInSubmit"));
	element5.click();
	
}

@Test
public void search() {
	driver.get("https://www.amazon.in");
	WebElement element1 = driver.findElement(By.id("twotabsearchtextbox"));
	element1.sendKeys("Hp");
	element1.sendKeys(Keys.ENTER);
	
	WebElement element2 = driver.findElement(By.partialLinkText("HP Pavilion Gaming"));
	element2.click();
}
@AfterTest
public void aftertest() throws InterruptedException {
	Thread.sleep(10000);
	driver.quit();
	
}

}
