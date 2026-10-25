package pageobject.root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.HeaderOptions;
import pageobject.RightCloumnOptionsPage;
import utils.ElementUtils;


public class RootPage {

	WebDriver driver;
	public ElementUtils elementUtils;
	
	public RootPage(WebDriver driver) {
		this.driver=driver;
		elementUtils=new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement pageLevelWarning;
	public String getPageLevelWarning() {
		return pageLevelWarning.getText();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public HeaderOptions getHeaderOptions() {
		return new HeaderOptions(driver);
	}
	
	public RightCloumnOptionsPage getRightColumnOptions() {
		return new RightCloumnOptionsPage(driver);
	}
}
	
