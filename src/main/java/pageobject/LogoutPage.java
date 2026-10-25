package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class LogoutPage extends RootPage{

WebDriver driver;
	
	public LogoutPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}
	
	public MyAccountPage getMyAccountPage() {
		return new MyAccountPage(driver);
	}
	
}
