package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageobject.root.RootPage;
import utils.ElementUtils;

public class SearchPage extends RootPage {

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	WebElement SearchBreadCrumb;

	public boolean didWeNavigateToSearchPage() {
		return SearchBreadCrumb.isDisplayed();
	}

	@FindBy(xpath = "//p[text()='There is no product that matches the search criteria.']")
	WebElement NonExistentProductMessage;

	public boolean isNonExistentProductMessageDisplayed() {
		return NonExistentProductMessage.isDisplayed();
	}
	
	public String getNoProductMessage() {
		return elementUtils.getElementText(NonExistentProductMessage);
	}
	
	@FindBy(xpath="//div[@class='product-thumb']")
	List <WebElement> ProductThumbnail;
	
	
	@FindBy(xpath="//div[@class='product-thumb']")
	 WebElement ProductThumbnail2;
	public int getCountOfProduct() {
		return elementUtils.getProductCount(ProductThumbnail);
	}
	
	public boolean isProductDisplayed() {
		return elementUtils.isElementDisplayedOnPage(ProductThumbnail2);
	}
	
	@FindBy (xpath="//input[@id='description']")
	WebElement SearchCheckBox;
	
	public void clickOnCheckBox() {
		SearchCheckBox.click();
	}
	@FindBy(xpath="//input[@id='input-search']")
	WebElement SearchCriteriaField;
	
	public void enterTextIntoSearchCriteriaField(String text) {
		elementUtils.enterTextIntoElement(SearchCriteriaField, text);
	}
	
	@FindBy(xpath="//input[@id='button-search']")
	WebElement Searchbutton;
	
	public void clickOnSearchButton() {
		elementUtils.clickOnElement(Searchbutton);
	}
	
	@FindBy (xpath="//select[@name='category_id']")
	WebElement CategoryDropdown;
	
	public void selectDropDownElement() {
		Select dropdown = new Select(CategoryDropdown);
		dropdown.selectByValue("27");
	}
	
	@FindBy(xpath="//select[@id='input-sort']")
	WebElement SortDropDown;
	public void selectSortDropDownElement() {
		Select dropdown1 = new Select(SortDropDown);
		dropdown1.selectByVisibleText("Price (Low > High)");
	}
	
	@FindBy(xpath="//button[@id='list-view']")
	WebElement ListView;
	
	public void clickOnListView() {
		elementUtils.clickOnElement(ListView);
	}
	
	@FindBy(xpath="//button[@id='grid-view']")
	WebElement GridView;
	
	public void clickOnGridView() {
		elementUtils.clickOnElement(GridView);
	}
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement ProductImage;
	
	public void clickOnProductImage() {
		elementUtils.clickOnElement(ProductImage);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='iMac']")
	WebElement ProductBreadcrumb;
	
	public boolean isProductBreadcrumbDisplayed() {
		return ProductBreadcrumb.isDisplayed();
	}
	
	@FindBy (xpath="//*[@id=\"content\"]/div[1]/div[2]/div[1]/button[1]/i")
	WebElement WishListIcon;
	
	public void clickOnWishlist() {
		elementUtils.clickOnElement(WishListIcon);
	}
	
	@FindBy (xpath="//*[@id=\"content\"]/div[1]/div[2]/div[1]/button[2]/i")
	WebElement CompareProductIcon;
	
	public void clickOnCompareProducPage() {
		elementUtils.clickOnElement(CompareProductIcon);
	}
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement AddToCart;
	
	public void clicOnCartIcon() {
		elementUtils.clickOnElement(AddToCart);
	}
	
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement AlertMessage;
	
	public void isWarningMessageDisplayed() {
		AlertMessage.isDisplayed();
	}

	@FindBy(xpath="//div[text()='Radio required!']")
	WebElement MandatoryFieldsToAddTocart;
		
	public boolean isMandatoryFieldWarningDisplayed() {
		return MandatoryFieldsToAddTocart.isDisplayed();
	}
	
	@FindBy(xpath="//div[@class='product-layout product-list col-xs-12']")
	List<WebElement> ProductOutputList; 
	
	public int getCountOfListItems() {
		return elementUtils.getProductCount(ProductOutputList);
	}
	
	@FindBy(xpath="//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']")
	List<WebElement> GridViewList;
	public int getCountOfGridItems() {
		return elementUtils.getProductCount(GridViewList);
	}
	
	@FindBy(linkText="Product Compare (0)")
	WebElement ProductCompareLink;
	public ProductComparePage clickOnProductCompareLink() {
		elementUtils.clickOnElement(ProductCompareLink);
		return new ProductComparePage(driver);
	}
	
	@FindBy(xpath="//select[@id='input-limit']")
	WebElement NoOfItemsToView;
	
	public void selectNoOfViewsDropDownElement() {
		Select dropdown2 = new Select(NoOfItemsToView);
		dropdown2.selectByVisibleText("25");
	}
}

