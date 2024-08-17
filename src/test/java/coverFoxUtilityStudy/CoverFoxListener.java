package coverFoxUtilityStudy;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import coverFoxBaseStudy.*;

public class CoverFoxListener extends Base implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
	Reporter.log(result.getName() + " TC execution started", true);
	}
	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("TC " + result.getName() + " is failed", true);
		try {
			UtilitycoverFox.takeScreenShot(	driver, result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
