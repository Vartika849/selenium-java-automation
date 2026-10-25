package register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageobject.AccountLogoutPage;
import pageobject.ChangePasswordPage;
import pageobject.HeaderOptions;
import pageobject.HomePage;
import pageobject.RightCloumnOptionsPage;
import utils.CommonUtilities;

public class Login extends Base {

	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=openBrowserAndgetAppURL();
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		loginpage1=headeroptions.selectLoginOption();
		
	}
	
	//@AfterMethod
	//public void teardown() {
		//closeBrowser(driver);
	//}
	
	@Test(priority=1)
	public void logIntoApplicationValidCreds() {
		Assert.assertTrue(loginpage1.didWeNavigateToLoginPage());
		myaccountpage=loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("validpassword"));
		Assert.assertTrue(myaccountpage.didWeNavigateToMyAccountPage());	
	}
	
	@Test(priority=2)
	public void logIntoApplicationWithInvalidCreds() {
		Assert.assertTrue(loginpage1.didWeNavigateToLoginPage());
		myaccountpage=loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("validpassword"));
		String expectedWarning="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginpage1.getPageLevelWarning(), expectedWarning);
		
	}
	
	@Test(priority=3)
	public void logIntoApplicationWithoutCreds() {
		Assert.assertTrue(loginpage1.didWeNavigateToLoginPage());
		myaccountpage=loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("validpassword"));
		String expectedWarning="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginpage1.getPageLevelWarning(), expectedWarning);
		
	}
	
	@Test(priority=4)
	public void verifyForgotPasswordLink() {
		Assert.assertTrue(loginpage1.didWeNavigateToLoginPage());
		loginpage1.clickOnForgotPassLink();
		Assert.assertTrue(loginpage1.isForgotPassBreadcrumbDisplayed());
		
	}
	
	@Test(priority=5)
	public void verifyPlaceholdersOnLoginPage() {
		Assert.assertTrue(loginpage1.didWeNavigateToLoginPage());
		String expectedemailplaceholder="E-Mail Address";
		String expectedpassplaceholder="Password";
		Assert.assertEquals(loginpage1.getEmailPlaceholderAttribute(), expectedemailplaceholder);
		Assert.assertEquals(loginpage1.getPasswordPlaceholderAttribute(), expectedpassplaceholder);	
	}
	
	@Test(priority=6)
	public void verifyBrowsingBackAfterLogin() {
		myaccountpage=loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("validpassword"));
		navigateBackInBrowser(myaccountpage.getDriver());
		refreshPage(myaccountpage.getDriver());
		Assert.assertTrue(myaccountpage.didWeNavigateToMyAccountPage());
	}
	
	@Test(priority=7)
	public void verifyBrowsingBackAfterLogout() {
		myaccountpage=loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("validpassword"));
		headeroptions=myaccountpage.getHeaderOptions();
		headeroptions.clickOnMyAccountDropDown();
		logoutPage=headeroptions.clickOnLogoutOption();
		navigateBackInBrowser(logoutPage.getDriver());
		refreshPage(logoutPage.getDriver());
		loginpage1=logoutPage.getLoginPage();
		Assert.assertTrue(loginpage1.didWeNavigateToLoginPage());
		
	}
	
	@Test(priority=8)
	public void clickOnLoginMultipleTimesWithInvalidcCreds() throws InterruptedException {
		String invalidemail=CommonUtilities.generateNewEmail();
		loginpage1.enterEmail(invalidemail);
		loginpage1.enterPassword(prop.getProperty("invalidpassword"));
		for(int i=1;i<=6;i++) {
			loginpage1.clickOnLoginButton();
			Thread.sleep(2000);
		}
		String expectedWarning="Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals( loginpage1.MultipleLoginWarningText(),expectedWarning);
	}
	
	@Test(priority=9)//Need to change current password and changedpassword everytime
	public void changePasswordAndLogin() {
		myaccountpage=loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("validpassword"));
		changepasswordpage=myaccountpage.clickOnChangeYourPassword();
		
		changepasswordpage.enterNewPassword(prop.getProperty("changedpassword"));
		changepasswordpage.enterConfirmPass(prop.getProperty("confirmchangedpass"));
		myaccountpage=changepasswordpage.clickOnContinueButton();
		Assert.assertTrue(myaccountpage.didWeNavigateToMyAccountPage());
		myaccountpage.isSuccessMessageDisplayed();
		rightcolumnoptions = myaccountpage.getRightColumnOptions();
		accountlogoutpage = rightcolumnoptions.selectLogoutOption();
		homepage = accountlogoutpage.clickOnContinueButton();
		headeroptions=homepage.getHeaderOptions();
		loginpage1=headeroptions.navigateToLoginPage();
		loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("validpassword"));
		String expectedmessge="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginpage1.getPageLevelWarning(), expectedmessge);
		loginpage1.logIntoApplication(prop.getProperty("existingemail"), prop.getProperty("changedpassword"));
		Assert.assertTrue(myaccountpage.didWeNavigateToMyAccountPage());
	}
	
	
	
	
	
	
}
