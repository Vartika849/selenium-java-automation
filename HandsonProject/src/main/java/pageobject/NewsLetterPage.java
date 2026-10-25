package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class NewsLetterPage extends RootPage{

	WebDriver driver;
	
	public NewsLetterPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Newsletter']")
	WebElement newsletterBreadcrumb;
	
	public boolean newsLetterBreadcrumbisDisplayed() {
		return newsletterBreadcrumb.isDisplayed();
	}
	
	@FindBy(xpath="//input[@name='newsletter' and @value='1']")
	WebElement newsletterradiobutton;
	
	public boolean newsletterRadiobuttonisSelected() {
		return newsletterradiobutton.isSelected();
		
	}
}
