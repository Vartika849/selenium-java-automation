package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class AccountSuccessPage extends RootPage{

	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")
	private WebElement logOutOption;
	
	public boolean isUserLoggedIn() {
		return logOutOption.isDisplayed();
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement successBreadcrumb;
	
	public boolean isSuccessDisplayed() {
		return successBreadcrumb.isDisplayed();
	}
	
	@FindBy(linkText="Continue")
	private WebElement continueButtonACSuccessPage;
	
	public MyAccountPage clickOnContinueButtonOnAccountSuccessPage() {
		elementUtils.clickOnElement(continueButtonACSuccessPage);
		 return new MyAccountPage(driver);
	}

	
}
