package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;
import utils.ElementUtils;

public class SiteMapPage extends RootPage{

	
	 WebDriver driver;
	 ElementUtils elementUtils;
		
		public SiteMapPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			elementUtils = new ElementUtils(driver);
			PageFactory.initElements(driver,this);
		}
		
		@FindBy (linkText="Search")
		WebElement SearchLink;
		public SearchPage clickOnSearchLink() {
			elementUtils.clickOnElement(SearchLink);
			return new SearchPage(driver);
		}
}
