package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class MyAccountPage extends RootPage {

    WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Edit your account information")
	private WebElement editTextOnAcSuccessPage;
	
	public boolean isEditTextDisplayed() {
		return editTextOnAcSuccessPage.isDisplayed();
		
	}
	public EditInformationPage selectEditAccInfoLink() {
		elementUtils.clickOnElement(editTextOnAcSuccessPage);
		return new EditInformationPage(driver);
	}
	
	@FindBy(linkText="Subscribe / unsubscribe to newsletter")
	private WebElement subscribeUnsubscribeLinkk;
	
	public NewsLetterPage clickOnSubscribeUnsubscribeLink() {
		elementUtils.clickOnElement(subscribeUnsubscribeLinkk);
		return new NewsLetterPage(driver);
	}
	
	@FindBy(linkText="Logout")
	WebElement Logout;
	public boolean didWeNavigateToMyAccountPage() {
		return Logout.isDisplayed();
	}
	
	public void clickOnLogout() {
		Logout.click();
	}
	
	public LogoutPage getLogoutPage() {
		return new LogoutPage(driver);
	}
	
	public ChangePasswordPage getChangePasswordPage() {
		return new ChangePasswordPage(driver);
	}
	
	@FindBy(linkText="Change your password")
	WebElement ChangePassword;
	public ChangePasswordPage clickOnChangeYourPassword() {
		ChangePassword.click();
		return new ChangePasswordPage(driver);
	}
	
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement Successmessage;
	public boolean isSuccessMessageDisplayed() {
		return Successmessage.isDisplayed();
		
	}
	
	
}
