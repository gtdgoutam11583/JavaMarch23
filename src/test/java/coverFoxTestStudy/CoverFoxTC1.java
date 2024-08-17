package coverFoxTestStudy;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
import coverFoxUtilityStudy.*;

public class CoverFoxTC1 extends Base {

	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxAddressDetailsPage addressDetailsPage;
	CoverFoxMemberDetailsPage memberDetailsPage;
	CoverFoxResultPage resultPage;
	String filePath;

	@BeforeTest
	public void launchBrowser() {
		openBrowser();
		homePage = new CoverFoxHomePage(driver);
		healthPlanPage = new CoverFoxHealthPlanPage(driver);
		addressDetailsPage = new CoverFoxAddressDetailsPage(driver);
		memberDetailsPage = new CoverFoxMemberDetailsPage(driver);
		resultPage = new CoverFoxResultPage(driver);
//		filePath = System.getProperty("user.dir")+"\\myExcel.xlsx";
		filePath = "C:\\Users\\Admin\\eclipse-workspace\\CoverFoxTest\\src\\test\\java\\coverFoxUtilityStudy\\fbTest.properties";
	}

	@BeforeClass
	public void preConditions() throws InterruptedException, EncryptedDocumentException, IOException {
		homePage.clickOnGenderButton();
		Thread.sleep(1000);
		healthPlanPage.clickOnNextButton();
		Thread.sleep(1000);

		memberDetailsPage.handleAgeDropDown(UtilitycoverFox.readDataFromPropertiesFile("age"));

		memberDetailsPage.clickOnNextButton();
		Thread.sleep(1000);

		addressDetailsPage.enterPincode(UtilitycoverFox.readDataFromPropertiesFile("pinCode"));

		addressDetailsPage.enterMobileNumber(UtilitycoverFox.readDataFromPropertiesFile("mobNum"));

		addressDetailsPage.clickOnContinueButton();
	}

	@Test
	public void validateBanners() throws InterruptedException {
		Thread.sleep(4000);
		Assert.fail();
		int bannerPlanNumbers = resultPage.getPlanNumersFromBanners();
		int StringplanNumbers = resultPage.getPlanNumersFromString();
		Assert.assertEquals(StringplanNumbers, bannerPlanNumbers,"Plans on banners not matching with results, TC failed");

	}

	@Test
	public void validatePresenceOfSortButton() throws InterruptedException, IOException {

		Thread.sleep(4000);
		Assert.fail();
		Assert.assertTrue(resultPage.sortPlanFilterIsDisplayed(), "Sort Plan filter is not displayed,TC is failed");
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		browserClose();
	}

}
