package amazon;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	//adding implicit wait to the code in place of Thread.sleep() 
	driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		
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
public void search() throws Exception {
	//Searching for dell using search bar.
	WebElement element1 = driver.findElement(By.id("twotabsearchtextbox"));
	element1.sendKeys(searchelement);
	element1.sendKeys(Keys.ENTER);
	

	
	//Explicit wait condition until it find the 'prime' image element.....
	 WebDriverWait wait = new WebDriverWait(driver,20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("a-list-item")));
	
    //Take snap after search, Product Page...
    Firefoxdriver.takeSnapShot(driver,System.getProperty("user.dir") +"\\Screenshot\\SearchedProductionPage.jpg") ; 
    
    //Prime supported items only ...
	WebElement element2 = driver.findElement(By.className("a-list-item"));
	element2.click();
	
	//Take snap of Prime eligible products.
	Firefoxdriver.takeSnapShot(driver,System.getProperty("user.dir") +"\\Screenshot\\OnlyPrimeProducts.jpg") ; 
		
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
