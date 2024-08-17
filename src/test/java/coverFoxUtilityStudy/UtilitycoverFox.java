package coverFoxUtilityStudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class UtilitycoverFox {

	public static void takeScreenShot(WebDriver driver, String fileName) throws IOException 
	{

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Reporter.log("taking screenShot", true);
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File dest = new File(System.getProperty("user.dir") + "\\ScreenShotSaver\\" + fileName + timeStamp + ".png");
		FileHandler.copy(src, dest);
		Reporter.log("saving file at location " + dest, true);
	}

	public static String readDataFromExcel(String filePath, String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException 
	{
		FileInputStream myFile = new FileInputStream(filePath);
		String value = WorkbookFactory.create(myFile).getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return value;
	}

	public static String readDataFromPropertiesFile(String key) throws IOException 
	{
		FileInputStream myFile = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\CoverFoxTest\\src\\test\\java\\coverFoxUtilityStudy\\fbTest.properties");
		Properties prop = new Properties();
		prop.load(myFile);
		Reporter.log("reading data from property file " + key, true);
		String value = prop.getProperty(key);
		return value;
	}

}
