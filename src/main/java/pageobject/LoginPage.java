package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class LoginPage extends RootPage {

WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Continue")
	WebElement Continue;
	
	public void selectContinue() {
		elementUtils.clickOnElement(Continue);
	}
	
	@FindBy(id="input-email")
	WebElement emailField;
	public void enterEmail(String emailText) {
		elementUtils.enterTextIntoElement(emailField, emailText);
	}
	
	@FindBy(id="input-password")
	WebElement passwordField;
	public void enterPassword(String passwordText) {
		elementUtils.enterTextIntoElement(passwordField, passwordText);
	}
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;
	public MyAccountPage clickOnLoginButton() {
		elementUtils.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}
	
	public MyAccountPage logIntoApplication(String emailText, String passwordText) {
		elementUtils.enterTextIntoElement(emailField, emailText);
		elementUtils.enterTextIntoElement(passwordField, passwordText);
		elementUtils.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}
	
	@FindBy(xpath="//ul[@class=\"breadcrumb\"]//a[text()='Login']")
	WebElement LoginBreadcrumb;
	public boolean didWeNavigateToLoginPage() {
		return LoginBreadcrumb.isDisplayed();
	}
	
	@FindBy(xpath="//div[@class='form-group']//a[text()='Forgotten Password']")
	WebElement ForgotPassword;
	public void clickOnForgotPassLink() {
		elementUtils.clickOnElement(ForgotPassword);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Forgotten Password']")
	WebElement ForgotPasswordBreadcrumb;
	public boolean isForgotPassBreadcrumbDisplayed() {
		return ForgotPasswordBreadcrumb.isDisplayed();
	}
	
	public String getPasswordPlaceholderAttribute() {
		return passwordField.getDomAttribute("placeholder");
	}
	
	public String getEmailPlaceholderAttribute() {
		return emailField.getDomAttribute("placeholder");
	}
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement MultipleLoginWarning;
	public String MultipleLoginWarningText() {
		return MultipleLoginWarning.getText();
	}
	
	
	
}
