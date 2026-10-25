package utils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {

	WebDriver driver;
	
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickOnElement(WebElement element) {
		if(element.isDisplayed()&& element.isEnabled()) {
			element.click();
		}
	}
	
	public void enterTextIntoElement(WebElement element,String elementtext) {
		element.clear();
		element.sendKeys(elementtext);
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	public boolean isElementDisplayedOnPage(WebElement element) {
		return element.isDisplayed();
	}
	
	public int getProductCount(List<WebElement> elements) {
		int count= 0;
		
		if(isElementDisplayedOnPage(elements.get(0))) {
			count=elements.size();
		}
		return count;
	}
	
}
