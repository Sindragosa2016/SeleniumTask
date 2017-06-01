package pragmatic.qa8.finalExamEva;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FinalTask {

	static WebDriver driver;
	@FindBy(name = "username")
	private static WebElement username;
	@FindBy(name = "password")
	private static WebElement password;
	@FindBy(name = "filter_name")
	private static WebElement filter;
	@FindBy(name = "product_description[1][name]")
	private static WebElement productName;
	@FindBy(name = "model")
	private static WebElement model;
	
	@Before
	public void setUpDriver() {
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://shop.pragmatic.bg/admin/");
	}
	public static void adminLogIn(String admin, String parola) throws InterruptedException {
	username.sendKeys("admin");
	password.sendKeys("parola");
	WebElement loginButton = driver.findElement(By.cssSelector("a.button"));
	loginButton.click();
	Thread.sleep(1000);
	assert ("You are logged in as admin") != null;
	}
	
	@Test
	public static void goToPage() {
	new Select(driver.findElement(By.id("Catalog"))).selectByVisibleText("Products");
	}
	public static void verifyAvailability (String Yoga) throws InterruptedException {
		filter.sendKeys("Yoga");
		WebElement searchButton = driver.findElement(By.name("filter();"));
		if ("No results!" != null) {
			WebElement checkbox = driver.findElement(By.name("selected[]" ));
			checkbox.click();
			WebElement deleteButton = driver.findElement(By.name("Delete"));
			deleteButton.click();
			Thread.sleep(1000);
			assert ("Success: You have modified products!") !=null;
		}
	}
	
	public static void addProductYoga () throws InterruptedException {
		WebElement insertButton = driver.findElement(By.name("Insert"));
		insertButton.click();
		productName.sendKeys("Yoga");
		driver.switchTo().window("#tab-data");
		model.sendKeys("3");
		WebElement saveButton = driver.findElement(By.name("Save"));
		saveButton.click();
		Thread.sleep(1000);
		assert ("Success: You have modified products!") !=null;
		
	}
	@AfterClass
	public void quitDriver() {
		driver.quit();
	}
	}