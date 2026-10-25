package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;
import utils.ElementUtils;

public class ProductComparePage extends RootPage{

	WebDriver driver;

	ElementUtils elementUtils;
	
	public ProductComparePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Product Comparison']")
	WebElement ProductCompareBreadcrumb;
	
	@FindBy(xpath="//div[@id='content']//h1[text()='Product Comparison']")
	WebElement ProductComparisonText;
	public boolean didWeNavigateToProductComparePage1() {
		return ProductComparisonText.isDisplayed();
	}
	
	public boolean didWeNavigateToProductComparePage() {
		return ProductCompareBreadcrumb.isDisplayed();
	}
}

