package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Firefoxdriver {
	
	public static WebDriver driver;
	public String email;
	public String password;
	

@BeforeTest
public void beforetest() {
	System.setProperty("webdriver.gecko.driver", "M:\\WebTesting\\firefoxdriver\\geckodriver.exe");

	driver=new FirefoxDriver();
	driver.manage().window().maximize();
	
}

@Test
public void login() {
	
}

@AfterTest
public void afertest() throws InterruptedException {
	Thread.sleep(2000);
	driver.close();
}

}
