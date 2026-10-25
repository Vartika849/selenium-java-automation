package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobject.root.RootPage;

public class EditInformationPage extends RootPage{

WebDriver driver;
	
	public EditInformationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	WebElement fname;
	public String getFnameValue() {
		return fname.getDomAttribute("value");

	}
	
	@FindBy(id="input-lastname")
	WebElement lname;
	public String getLnameValue() {
		return lname.getDomAttribute("value");
	}
	
	@FindBy(id="input-telephone")
	WebElement telephone;
	public String getTelephoneValue() {
		return telephone.getDomAttribute("value");
	}
	
	
}
