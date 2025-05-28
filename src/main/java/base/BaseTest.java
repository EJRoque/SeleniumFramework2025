package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.Log;

public class BaseTest {

	protected WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		Log.info("Setting up WebDriver...");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to test URL");
		driver.get("https://admin-demo.nopcommerce.com/login");
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			Log.info("Closing the Browser...");
			driver.quit();
		}
	}
	
}
