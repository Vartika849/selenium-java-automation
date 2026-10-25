package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class AccountLogoutPage extends RootPage{

	
	 WebDriver driver;
		
		public AccountLogoutPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		

		@FindBy(linkText="Continue")
		WebElement ContinueButton;
		public HomePage clickOnContinueButton() {
			elementUtils.clickOnElement(ContinueButton);
			return new HomePage(driver);
		}
}
