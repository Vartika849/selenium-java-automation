package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;


public class HeaderOptions extends RootPage{

	WebDriver driver;
	
	
	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	public void clickOnMyAccountDropDown() {
		elementUtils.clickOnElement(myAccountDropMenu);
		
	}
	
	public RegisterPage selectRegisterOption() {
		elementUtils.clickOnElement(registerOption);
		return new RegisterPage (driver);
	}
	
	@FindBy(linkText="Login")
	private WebElement LoginOption;
	public LoginPage selectLoginOption() {
		elementUtils.clickOnElement(LoginOption);
		return new LoginPage(driver);
	
	}
	
	@FindBy(linkText="Logout")
	WebElement Logout;
	public boolean didWeNavigateToMyAccountPage() {
		return Logout.isDisplayed();
	}
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Logout']")
	WebElement LogoutOption;
	public LogoutPage clickOnLogoutOption() {
		elementUtils.clickOnElement(Logout);
		return new LogoutPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {
		clickOnMyAccountDropDown();
		return selectLoginOption();
	}
	
	@FindBy(name="search")
	WebElement SearchField;
	public void  searchAnItem(String text) {
		elementUtils.enterTextIntoElement(SearchField, text);
		
	}
	
	public SearchPage clickOnSearchButton() {
		elementUtils.clickOnElement(SearchButton);
		return new SearchPage(driver);
	}
	
	@FindBy(xpath="//i[@class='fa fa-search']")
	WebElement SearchButton;
}
