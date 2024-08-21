package coverFoxTestStudy;

import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import coverFoxBaseStudy.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxPOM.CoverFoxResultPage;
import coverFoxUtilityStudy.UtilitycoverFox;

public class CoverFoxTC_POM_EXCEL extends Base{
	
	WebDriver driver;
	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxAddressDetailsPage addressDetailsPage;
	CoverFoxMemberDetailsPage memberDetailsPage;
	CoverFoxResultPage resultPage;
	String filePath;
	public static Logger logger;
	@BeforeTest
	public void launchBrowser()
	{ 
		logger = Logger.getLogger("CoverFoxInsurance");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Opening Browser...");
		
		openBrowser();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.get("https://www.coverfox.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

		homePage = new CoverFoxHomePage(driver);
		healthPlanPage = new CoverFoxHealthPlanPage(driver);
		addressDetailsPage = new CoverFoxAddressDetailsPage(driver);
		memberDetailsPage = new CoverFoxMemberDetailsPage(driver);
		resultPage = new CoverFoxResultPage(driver);
		filePath = System.getProperty("user.dir") + "\\myExcel.xlsx";
		
		
	
	}

	@BeforeClass
	public void preConditions() throws InterruptedException,EncryptedDocumentException, IOException
	{
		
		homePage.clickOnGenderButton();
		logger.info("Clicking on gender btn");
		Thread.sleep(1000);
		
		healthPlanPage.clickOnNextButton();
		logger.info("Clicking on  HealthPlan page next btn");
		Thread.sleep(1000);
		
		memberDetailsPage.handleAgeDropDown(UtilitycoverFox.readDataFromExcel(filePath, "Sheet5", 0, 0));
		logger.warn("Age should be between 18 to 90");
		logger.info("Handling Age DropDown");
		Thread.sleep(1000);
		
		memberDetailsPage.clickOnNextButton();
		logger.info("Clicking on MemberDetails Next btn");
		Thread.sleep(1000);
		
		addressDetailsPage.enterPincode(UtilitycoverFox.readDataFromExcel(filePath, "Sheet5", 0, 1));
		logger.warn("Enter valid Pin Code");
		logger.info("Entering Pin Code");
		
		addressDetailsPage.enterMobileNumber(UtilitycoverFox.readDataFromExcel(filePath, "Sheet5", 0, 2));
		logger.warn("Enter Valid Mobile Number");
		logger.info("Entering Mobile Number");
		
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on AddressDetails Page Next btn");
	}

	@Test
	public void validateBanners() throws InterruptedException {
		Thread.sleep(4000);
		int bannerPlanNumbers = resultPage.getPlanNumersFromBanners();
		int StringplanNumbers = resultPage.getPlanNumersFromString();
		
		Assert.assertEquals(StringplanNumbers, bannerPlanNumbers,"Plans on banners not matching with results, TC failed");
		logger.info("Validating Banners...");

	}
	@AfterClass
	public void closeBrowser() throws InterruptedException
	{
		
		logger.info("Closing Browser...");
		browserClose();
	}

}
