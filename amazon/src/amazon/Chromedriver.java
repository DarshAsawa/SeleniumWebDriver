package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Chromedriver {
	public static WebDriver driver;
	public String email = null;
	public String password = null;
	Scanner scan;



@BeforeTest
public void beforetest() {
	System.setProperty("webdriver.chrome.driver", "M:\\WebTesting\\chromedriver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	//adding implicit wait to the code 
	driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
	
}

@BeforeMethod
public void detailsInput() {
	scan = new Scanner(System.in);
	
	System.out.print("Enter email or phone number:\n");
	email=scan.nextLine();
	
	System.out.print("Enter password: \n");
	password=scan.nextLine();
	
	scan.close();
	
	
}

//To get the location of our current working directory of our project...
// use System.getProperty("user.dir")+"\\ExtentReportResults.html"

@Test
public void login() throws Exception {
	
	driver.navigate().to("https://www.amazon.in");
	Chromedriver.takeSnapShot(driver, "M:\\WebTesting\\Web-Application-Testing\\amazon\\Screenshot\\amzon_mainPage.jpg") ;   
	
	WebElement element= driver.findElement(By.id("nav-signin-tooltip"));
	element.click();
	Chromedriver.takeSnapShot(driver, "M:\\WebTesting\\Web-Application-Testing\\amazon\\Screenshot\\EmailEntry.jpg") ; 
	WebElement element2=driver.findElement(By.id("ap_email"));
	element2.sendKeys(email);
	
	WebElement element3=driver.findElement(By.id("continue"));
	element3.click();
	
	WebElement element4=driver.findElement(By.id("ap_password"));
	element4.sendKeys(password);
	Chromedriver.takeSnapShot(driver, "M:\\WebTesting\\Web-Application-Testing\\amazon\\Screenshot\\PasswordPage.jpg") ; 
	
	WebElement element5= driver.findElement(By.id("signInSubmit"));
	element5.click();
	
}

@AfterMethod
public void afterLogin() {
	scan.close();
}

//BeforeMethod and AfterMethod runs before and after each test case,
//and thus not suitable with this search test case.... 
@Test(enabled=false)
public void search() {
	driver.get("https://www.amazon.in");
	
	//search for HP brand using search box.
	WebElement element1 = driver.findElement(By.id("twotabsearchtextbox"));
	element1.sendKeys("Hp");
	element1.sendKeys(Keys.ENTER);
	
	WebElement element_HP = driver.findElement(By.partialLinkText("HP"));
	element_HP.click();
	
	//Open the first HP Chromebook being displayed dynamically.
	WebElement element2 = driver.findElement(By.partialLinkText("Core i5 8th Gen"));
	element2.click();
}

@AfterTest
public void aftertest() throws InterruptedException {
	Thread.sleep(10000);
	//driver.quit();
	
}


public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

    //Convert web driver object to TakeScreenshot

    TakesScreenshot ss =((TakesScreenshot)webdriver);

    //Call getScreenshotAs method to create image file

            File SrcFile=ss.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

            File DestFile=new File(fileWithPath);

            //Copy file at destination

            FileUtils.copyFile(SrcFile, DestFile);

}

}
