package ExcelValidation;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import base.BaseTest;
import utilities.TestUtil;

public class ExcelVerification extends BaseTest {
	@BeforeMethod
	public void init() {
		setUp();
		startup();
	}

	@AfterMethod
	public void close() {
		tearDown();
	}

	@Test(priority = 1, dataProvider = "CMStatus")
	public void TestLogic(Hashtable<String, String> data)
			throws AddressException, IOException, MessagingException, InterruptedException, AWTException {
		driver.findElement(By.id("j_username")).sendKeys("djames001");
		driver.findElement(By.id("j_password")).sendKeys("password12");
		driver.findElement(By.className("login-middle")).click();
		Thread.sleep(8000);
		driver.findElement(By.id("__o3.appsearch.searchText")).sendKeys(data.get("IHSS_CS_ID"));
		driver.findElement(By.id("__o3.appsearch.search-btn")).click();
		Thread.sleep(5000);
		/*
		 * List <WebElement> frames = driver.findElements(By.tagName("iframe"));
		 * System.out.println("The total nuber of frames are ->" +
		 * frames.size()); for(WebElement frame : frames){
		 * System.out.println("The frame ID is -->" + frame.getAttribute("id"));
		 * System.out.println("The frame class is -->" +
		 * frame.getAttribute("class"));
		 * System.out.println("The frame title is -->" +
		 * frame.getAttribute("title"));
		 * System.out.println("The frame tagName is -->" + frame.getTagName());
		 * }
		 */
		driver.switchTo().frame(2);
		String status = driver.findElement(By.xpath("//*[@id='content']/div[5]/div/table/tbody/tr[1]/td[2]")).getText();
		System.out.println("The status is --> " + status);
		Assert.assertEquals(status, data.get("CM_CASE_STATUS"),"The failed test case and IHSS_CS_ID is --> "+data.get("IHSS_CS_ID"));
	
	}

	

	@DataProvider()
	public Object[][] CMStatus() {
		return TestUtil.getData("CMStatus");
	}
}
