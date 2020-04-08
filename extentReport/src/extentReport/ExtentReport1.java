package extentReport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport1 {
	
	static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;

@BeforeTest

	public static void startTest()
	{
		System.out.println("Executing Before Test");
		//M:\WebTesting\Web-Application-Testing\extentReport
		report = new ExtentReports("M:\\WebTesting\\Web-Application-Testing\\extentReport\\AmazonReport.html");
		test = report.startTest("AMAZON TESTING REPORT");
		
		System.setProperty("webdriver.chrome.driver", "M:\\WebTesting\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

@Test
public void extentReportsDemo()
{
	driver.get("https://www.amazon.in");
	if(driver.getTitle().equals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in"))
	{
		test.log(LogStatus.PASS, "Navigated to the specified URL");
	}
	else
	{
		test.log(LogStatus.FAIL, "Test Failed");	
	}
}

@Test(enabled=true)
public void search_item() throws InterruptedException {
	Thread.sleep(1000);
	//By Id....
	WebElement element2 = driver.findElement(By.id("twotabsearchtextbox"));
	element2.sendKeys("detol");
	test.log(LogStatus.INFO, "Search box on amazon");
	
	//By class name....
	WebElement element3 = driver.findElement(By.className("nav-input"));
	element3.click();
	
	Thread.sleep(2000);
	//By Xpath
	WebElement element4 = driver.findElement(By.xpath("//*[@id=\"p_85/10440599031\"]/span/a/i"));
	element4.click();
	test.log(LogStatus.INFO, "Only the prime products to be displayed");
	
	Thread.sleep(1000);
	//By Linktext
	WebElement element5 = driver.findElement(By.linkText("Dettol Original Soap, 125g (Pack Of 4)"));
	element5.click();
	test.log(LogStatus.INFO, "Selecting a detol hand wash");
	
	test.log(LogStatus.PASS, "Searched a Dettol Product over amazon");

}

@AfterTest
public static void endTest()
{
	System.out.println("Executing the after test method");
report.endTest(test);
report.flush();
driver.quit();
}
	
	
}
