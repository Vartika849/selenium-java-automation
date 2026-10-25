package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class RegisterPage extends RootPage{

	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	WebElement firstNameField;
	
	public void enterTextIntoFirstNameField(String firstname) {
		firstNameField.sendKeys(firstname);
	}
	public String getAttributeFirstNameField() {
		return firstNameField.getDomAttribute("placeholder");
	}
	public String getHeightValueFirstNameField() {
		return firstNameField.getCssValue("height");
	}
	public WebElement getFirstnameFieldElement() {
		return firstNameField;
	}
	
	@FindBy(css="label[for='input-firstname']")
	private WebElement FirstNameFieldLabel;
	
	public WebElement getFirstNameFieldLabel() {
		return FirstNameFieldLabel;
	}
	
	@FindBy(css="label[for='input-lastname']")
	private WebElement LastNameFieldLabel;
	
	public WebElement getLastNameFieldLabel() {
		return LastNameFieldLabel;
	}
	
	@FindBy(css="label[for='input-email']")
	private WebElement EmailFieldLabel;
	
	public WebElement getEmailFieldLabel() {
		return EmailFieldLabel;
	}
	
	@FindBy(css="label[for='input-telephone']")
	private WebElement TelephoneFieldLabel;
	
	public WebElement getTelephoneFieldLabel() {
		return TelephoneFieldLabel;
	}
	
	@FindBy(css="label[for='input-password']")
	private WebElement PasswordFieldLabel;
	
	public WebElement getPasswordFieldLabel() {
		return PasswordFieldLabel;
	}
	
	@FindBy(css="label[for='input-confirm']")
	private WebElement ConfirmPassLabel;
	
	public WebElement getConfirmPassFieldLabel() {
		return ConfirmPassLabel;
	}
	
	@FindBy(css="div[class='pull-right']")
	private WebElement PrivacyFieldLabel;
	
	public WebElement getPrivacyFieldLabel() {
		return PrivacyFieldLabel;
	}
	
	
	@FindBy(id="input-lastname")
	WebElement lastNameField;
	
	public void enterTextIntoLastNameField(String lastname) {
		lastNameField.sendKeys(lastname);
	}
	public String getAttributeLastNameField() {
		return lastNameField.getDomAttribute("placeholder");
	}
	public String getHeightValueLastNameField() {
		return lastNameField.getCssValue("height");
	}
	public WebElement getLastnameFieldElement() {
		return lastNameField;
	}
	
	@FindBy(id="input-email")
	WebElement emailField;
	
	public void enterTextIntoEmailField(String email) {
		emailField.sendKeys(email);
	}
	public String getAttributeEmailField() {
		return emailField.getDomAttribute("placeholder");
	}
	public WebElement getEmailFieldElement() {
		return emailField;
	}
	
	
	@FindBy(id="input-telephone")
	WebElement telephoneField;
	
	public void enterTextIntoTelephoneField(String telephone) {
		telephoneField.sendKeys(telephone);
	}
	public String getAttributeTelephoneField() {
		return telephoneField.getDomAttribute("placeholder");
	}
	public WebElement getTelephoneElement() {
		return telephoneField;
	}
	
	
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	public void enterTextIntoPasswordField(String pass) {
		passwordField.sendKeys(pass);
	}
	public String getAttributePasswordField() {
		return passwordField.getDomAttribute("placeholder");
	}
	public String getTypeAttributePasswordField() {
		return passwordField.getDomAttribute("type");
	}
	public WebElement getPasswordElement() {
		return passwordField;
	}
	
	
	@FindBy(id="input-confirm")
	WebElement passconfirmField;
	
	public void enterTextIntoPasswordConfirmField(String passconfirm) {
		passconfirmField.sendKeys(passconfirm);
	}
	public String getAttributeConfirmPassField() {
		return passconfirmField.getDomAttribute("placeholder");
	}
	public String getTypeAttributeConfirmPassField() {
		return passconfirmField.getDomAttribute("type");
	}
	public WebElement getPasswordConfirmElement() {
		return passconfirmField;
	}
	
	
	
	@FindBy(name="agree")
	WebElement privacyPolicy;
	
	public void selectPrivacyPolicyField() {
		elementUtils.clickOnElement(privacyPolicy);
	}
	public boolean isPrivacyPolicySelected() {
		return privacyPolicy.isSelected();
	}
	
	
	@FindBy(xpath="//input[@name='newsletter' and @value='1']")
	WebElement newsletterY;
	public void selectNewsLetterFieldAsY() {
		elementUtils.clickOnElement(newsletterY);
	}
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	public AccountSuccessPage clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
		return new AccountSuccessPage(driver);
	}
	public WebElement getContinueButtonElement() {
		continueButton.click();
		return continueButton;
	}
	
	@FindBy(xpath="//div[@id=\"content\"]//following-sibling::h1")
	WebElement RegisterAccountText;
	
	public String getRegisterAccountText() {
		return RegisterAccountText.getText(); 
	}
	
	@FindBy(xpath="//ul[@class=\"breadcrumb\"] //a[text()='Register']")
	WebElement RegisterBreadcrumb;
	
	public String getRegisterBreadcrumbText() {
		return RegisterBreadcrumb.getText(); 
	}
	
	public boolean registerBreadcrumbisDisplayed() {
		return RegisterBreadcrumb.isDisplayed();
	}
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement existingEmailWarningText;
	
	public String getExistingEmailWarningText() {
		return existingEmailWarningText.getText(); 
	}
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	WebElement firstnamewarning;
	
	public String getfirstNameWarningText() {
		return firstnamewarning.getText(); 
	}
	public boolean firstNameWarningTextisDisplayed() {
		return firstnamewarning.isDisplayed();
	}
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	WebElement lastnamewarning;
	
	public String getlastNameWarningText() {
		return lastnamewarning.getText();
	}
	public boolean lastNameWarningTextisDisplayed() {
		return lastnamewarning.isDisplayed();
	}
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	WebElement emailwarning;
	
	public String getemailWarningText() {
		return emailwarning.getText(); 
	}
	public boolean emailWarningTextisDisplayed() {
		return emailwarning.isDisplayed();
	}
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	WebElement telephonewarning;
	
	public String gettelephoneWarningText() {
		return telephonewarning.getText();
	}
	public boolean telephoneWarningTextisDisplayed() {
		return telephonewarning.isDisplayed();
	}
	
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	WebElement passwordwarning;
	
	public String getpasswordWarningText() {
		return passwordwarning.getText();
	}
	public boolean passwordWarningTextisDisplayed() {
		return passwordwarning.isDisplayed();
	}
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement privacywarning;
	
	public String getprivacyWarningText() {
		return privacywarning.getText();
	}
	
	@FindBy(linkText="Login")
	WebElement Login;
	
	public RightCloumnOptionsPage selectLogin() {
		Login.click();
		return new RightCloumnOptionsPage(driver);
	}
	
	@FindBy(xpath="//div[text()='Password confirmation does not match password!']")
	WebElement blankPassWarning;
	public String getblankPassWarningText() {
		return blankPassWarning.getText();
	}
	
	@FindBy(xpath="//div[text()='Password confirmation does not match password!']")
	WebElement mismatchpassWarning;
	public boolean mismatchpassWarningisDisplayed() {
		return mismatchpassWarning.isDisplayed();
	}
	
	@FindBy(xpath="//div[text()='First Name must be between 1 and 32 characters!']")
	WebElement charFNameWarningText;
	public String getCharFNameWarningText() {
		return charFNameWarningText.getText();
	}
	
	@FindBy(xpath="//div[text()='Last Name must be between 1 and 32 characters!']")
	WebElement charLNameWarningText;
	public String getCharLNameWarningText() {
		return charLNameWarningText.getText(); 
	}
	
	@FindBy(xpath="//div[text()='E-Mail Address does not appear to be valid!']")
	WebElement charEmailWarningText;
	public String getCharemailWarningText() {
		return charEmailWarningText.getText(); 
	}
	
}

