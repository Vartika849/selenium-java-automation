package register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageobject.FooterOptionsPage;
import pageobject.HeaderOptions;


public class Search extends Base{

WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=openBrowserAndgetAppURL();	
		headeroptions=new HeaderOptions(driver);
		
	}
	
	//@AfterMethod
		//public void teardown() {
			//closeBrowser(driver);
		//}
	
	@Test(priority=1)
	public void searchForExistingItemWithoutLoggingIn() {
		headeroptions.searchAnItem(prop.getProperty("SearchTextExistingItem"));
		searchpage=headeroptions.clickOnSearchButton();
		Assert.assertTrue(searchpage.didWeNavigateToSearchPage());
	}
	
	@Test(priority=1)
	public void searchForNonExistingItemWithoutLoggingIn() {
		headeroptions.searchAnItem(prop.getProperty("SearchTextNonExistingItem"));
		searchpage=headeroptions.clickOnSearchButton();
		Assert.assertTrue(searchpage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchpage.isNonExistentProductMessageDisplayed());
		Assert.assertEquals(searchpage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}
	
	@Test(priority=1)
	public void searchResultsInMultipleProducts() {
		headeroptions.searchAnItem(prop.getProperty("ProductItem"));
		searchpage=headeroptions.clickOnSearchButton();
		Assert.assertTrue(searchpage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchpage.getCountOfProduct()>0);
	}
	
	@Test(priority=1)
	public void searchUsingSearchCriteriaField() {
		searchpage=headeroptions.clickOnSearchButton();
		searchpage.enterTextIntoSearchCriteriaField(prop.getProperty("ProductItem"));
		searchpage.clickOnSearchButton();
		Assert.assertTrue(searchpage.isProductDisplayed());
	}
	
	@Test(priority=1)
	public void searchUsingSearchCriteriaFieldInProductDescription() {
		searchpage=headeroptions.clickOnSearchButton();
		searchpage.enterTextIntoSearchCriteriaField(prop.getProperty("ProductItem"));
		searchpage.clickOnCheckBox();
		searchpage.clickOnSearchButton();
		Assert.assertTrue(searchpage.isProductDisplayed());
	}
	
	@Test(priority=1)
	public void searchUsingCategoryDropDown() {
		searchpage=headeroptions.clickOnSearchButton();
		searchpage.enterTextIntoSearchCriteriaField(prop.getProperty("ProductItem"));
		searchpage.selectDropDownElement();
		searchpage.clickOnSearchButton();
		Assert.assertTrue(searchpage.isProductDisplayed());
	}
	
	@Test(priority=1)
	public void verifyListAndGridViews() {
		headeroptions.searchAnItem(prop.getProperty("ProductItem"));
		searchpage=headeroptions.clickOnSearchButton();
		searchpage.clickOnListView();//searchpage.clickOnGridView();
		searchpage.clickOnProductImage();
		searchpage.isProductBreadcrumbDisplayed();
		searchpage.clickOnWishlist();
		//searchpage.isWarningMessageDisplayed();
		searchpage.clickOnCompareProducPage();
		//searchpage.isWarningMessageDisplayed();
		searchpage.clicOnCartIcon();
		//Assert.assertTrue(searchpage.isMandatoryFieldWarningDisplayed());
		
	}
	
	@Test(priority=1)
	public void searchforMultipleProducts() {
		headeroptions.searchAnItem(prop.getProperty("MultipleProductItem"));
		searchpage=headeroptions.clickOnSearchButton();
		searchpage.clickOnListView();
		Assert.assertTrue(searchpage.getCountOfProduct()>0);
		searchpage.clickOnListView();
		Assert.assertTrue(searchpage.getCountOfProduct()>0);
		searchpage.clickOnGridView();
		Assert.assertTrue(searchpage.getCountOfGridItems()>0);
	}
	
	@Test(priority=1)//failing
	public void verifyProductCompareLink() {
		headeroptions.searchAnItem(prop.getProperty("ProductItem"));
		searchpage=headeroptions.clickOnSearchButton();
		searchpage.clickOnProductCompareLink();
		Assert.assertTrue(prodcomparepage.didWeNavigateToProductComparePage1());
	}
	
	@Test(priority=1)
	public void verifysortOption() {
		searchpage=headeroptions.clickOnSearchButton();
		searchpage.enterTextIntoSearchCriteriaField(prop.getProperty("MultipleProductItem"));
		searchpage.clickOnSearchButton();
		searchpage.selectSortDropDownElement();
		Assert.assertTrue(searchpage.isProductDisplayed());
		searchpage.selectNoOfViewsDropDownElement();
	}
	
	@Test(priority=1)
	public void verifySearchThroughSiteMapLink() {
		sitemappage=footeroptionspage.clickOnSiteMap();
		searchpage=sitemappage.clickOnSearchLink();
		searchpage.didWeNavigateToSearchPage();
	}
}
