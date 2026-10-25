package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import pageobject.AccountLogoutPage;
import pageobject.AccountSuccessPage;
import pageobject.ChangePasswordPage;
import pageobject.EditInformationPage;
import pageobject.FooterOptionsPage;
import pageobject.HeaderOptions;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.LogoutPage;
import pageobject.MyAccountPage;
import pageobject.NewsLetterPage;
import pageobject.ProductComparePage;
import pageobject.RegisterPage;
import pageobject.RightCloumnOptionsPage;
import pageobject.SearchPage;
import pageobject.SiteMapPage;
import utils.CommonUtilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public String browsername;
	public HeaderOptions headeroptions;
	public RegisterPage registerpage;
	public LoginPage loginpage1;
	public AccountSuccessPage accountsuccesspage;
	public MyAccountPage myaccountpage;
	public NewsLetterPage newsletterpage;
	public RightCloumnOptionsPage loginpage;
	public EditInformationPage editinfopage;
	public LogoutPage logoutPage;
	public ChangePasswordPage changepasswordpage;
	public RightCloumnOptionsPage rightcolumnoptions;
	public AccountLogoutPage accountlogoutpage ;
	public HomePage homepage ;
	public SearchPage searchpage;
	public ProductComparePage prodcomparepage;
	public FooterOptionsPage footeroptionspage;
	public SiteMapPage sitemappage;
	
	public WebDriver openBrowserAndgetAppURL() {
		prop=CommonUtilities.loadPropertiesFile();
		browsername=prop.getProperty("browsername");
		
		if(browsername.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browsername.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browsername.equals("Edge")) {
			driver=new EdgeDriver();
		}else if(browsername.equals("Safari")) {
			driver=new SafariDriver();
		}
	
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appURL"));
		
		return driver;
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void closeBrowser(WebDriver driver) {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
}
