package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseTest;

public class TestUtil extends BaseTest {
	
	
	public void switchToFrame(String frameTo){
		driver.switchTo().frame(frameTo);
	}
	
	
	public static String fileName;

	public static void captureScreenshot() throws IOException {

		Date d = new Date();
		fileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\test-output\\html\\" + fileName));

	}
	
	
	public static Object[][] getData(String sheetName) {

		
		int rowNum = excel.getRowCount(sheetName);
		int colNum = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rowNum - 1][1];
		Hashtable<String,String> table = null;
		
		for (int rows = 2; rows <= rowNum; rows++) {
			table = new Hashtable<String,String>();
				
			for (int cols = 0; cols < colNum; cols++) {

				table.put(excel.getCellData(sheetName, cols, 1), excel.getCellData(sheetName, cols, rows));	
				data[rows-2][0]=table;	

			}

		}

		return data;

	}


}
