package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class RightCloumnOptionsPage extends RootPage{

WebDriver driver;
	
	public RightCloumnOptionsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Continue")
	WebElement Continue;
	
	public void selectContinue() {
		elementUtils.clickOnElement(Continue);
		
	}
	
	@FindBy(xpath="//aside[@id='column-right']//a[text()='Register']")
	
	WebElement RegisterLink;
	public void selectRegisterOption() {
		elementUtils.clickOnElement(RegisterLink);
		
	}
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")
	WebElement Logout;
	public AccountLogoutPage selectLogoutOption() {
		elementUtils.clickOnElement(Logout);
		return new AccountLogoutPage(driver);
		
	}
	
	
}
