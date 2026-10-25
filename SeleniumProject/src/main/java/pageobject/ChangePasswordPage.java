package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class ChangePasswordPage extends RootPage{

	

    WebDriver driver;
	
	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-password")
	WebElement password;

	public void enterNewPassword(String pass) {
		password.sendKeys(pass);
	}
	
	@FindBy(id="input-confirm")
	WebElement passwordconfirm;
	public void enterConfirmPass(String confirmpass) {
		passwordconfirm.sendKeys(confirmpass);
	}
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement Continue;
	public MyAccountPage clickOnContinueButton() {
		Continue.click();
		return new MyAccountPage(driver);
	}
	

}
