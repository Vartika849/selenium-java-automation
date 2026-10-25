package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;
import utils.ElementUtils;

public class FooterOptionsPage extends RootPage{

	
	 WebDriver driver;
		
	 ElementUtils elementUtils;
		
		public FooterOptionsPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			elementUtils = new ElementUtils(driver);
			PageFactory.initElements(driver,this);
		}
		
		@FindBy(linkText="Site Map")
		WebElement SiteMapLink;
		
		public SiteMapPage clickOnSiteMap() {
			elementUtils.clickOnElement(SiteMapLink);
			return new SiteMapPage(driver);
		}
}
