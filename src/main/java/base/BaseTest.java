package base;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;

public class BaseTest {
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static EventFiringWebDriver e_driver;
	public static WebDriverWait wait;
	public static WebElement dropdown;
	public static WebElement clk;
	public static Robot robot;
	public static Set<String> winids;
	public static Iterator<String> iterate;
	public static Actions action;
	public static Select select;

	public void setUp() {
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Config.load(fis);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			OR.load(fis);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void startup() {

		if (Config.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (Config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Config.getProperty("browser").equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

		driver.get(Config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),TimeUnit.SECONDS);

	}
	public static void click(String locatorKey) {
		if (locatorKey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
			// clk.click();
		} else if (locatorKey.endsWith("_CSS")) {
			clk = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			clk.click();
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			clk = driver.findElement(By.id(locatorKey));
			clk.click();
		}
	}

	public static void type(String locatorKey, String value) throws IOException, AddressException, MessagingException {

		if (locatorKey.endsWith("_XPATH")) {
			clk = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
			clk.sendKeys(value);
		}

		else if (locatorKey.endsWith("_CSS")) {
			clk = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			clk.sendKeys(value);
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			clk = driver.findElement(By.id(locatorKey));
			clk.sendKeys(value);
		}

	}

	public static void select(String locatorKey, String value)
			throws IOException, AddressException, MessagingException {
		if (locatorKey.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			dropdown = driver.findElement(By.id(locatorKey));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	}

	public static void selectIndex(String locatorKey, String indexNum)
			throws IOException, AddressException, MessagingException {

		double doub = Double.parseDouble(indexNum);
		int intValue = (int) doub;
		if (locatorKey.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
		} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
			dropdown = driver.findElement(By.id(locatorKey));
		}

		Select select = new Select(dropdown);
		select.selectByIndex(intValue);


	}

	public static boolean isElementPresent(String locatorKey) {

		try {
			if (locatorKey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
			} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {
				driver.findElement(By.id(locatorKey));
			}
		
			return true;
		} catch (Throwable t) {
			return false;

		}

	}

	public void tearDown() {
		driver.quit();
	}

	
	public static void sendMail() throws AddressException, MessagingException {
		System.out.println("Done!!!!!!!!!!!");
	}

}
