package register;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pageobject.AccountSuccessPage;
import pageobject.EditInformationPage;
import pageobject.HeaderOptions;
import pageobject.MyAccountPage;
import pageobject.NewsLetterPage;
import pageobject.RegisterPage;
import pageobject.RightCloumnOptionsPage;
import utils.CommonUtilities;

public class Register extends Base{
	WebDriver driver;
	
	@AfterMethod
	public void teardown() {
		closeBrowser(driver);
	}
	
	@BeforeMethod
	public void setup() {
		driver=openBrowserAndgetAppURL();
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();	
	}
	
	@Test(priority=1)
	public  void verifyRegisterAccount(){
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		registerpage.enterTextIntoEmailField(CommonUtilities.generateNewEmail());
		registerpage.enterTextIntoTelephoneField(prop.getProperty("telephone"));
		registerpage.enterTextIntoPasswordField(prop.getProperty("validpassword"));
		registerpage.enterTextIntoPasswordConfirmField(prop.getProperty("validpassword"));
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		Assert.assertTrue(accountsuccesspage.isUserLoggedIn());
		Assert.assertTrue(accountsuccesspage.isSuccessDisplayed());
		
		myaccountpage=accountsuccesspage.clickOnContinueButtonOnAccountSuccessPage();
		Assert.assertTrue(myaccountpage.isEditTextDisplayed());
		
	}
	
	@Test(priority=2)
	public void RegisterAccountUsingKeyboardkeys() {
		
		Actions action=new Actions(driver);
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofSeconds(2)). sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(2)).sendKeys(Keys.ENTER).build().perform();
		
		for(int i=1;i<=23;i++) {
			action.sendKeys(Keys.TAB).build().perform();
		}
		
		action.sendKeys("Vartika").sendKeys(Keys.TAB)
		.sendKeys("Chougule").sendKeys(Keys.TAB)
		.sendKeys(CommonUtilities.generateNewEmail()).sendKeys(Keys.TAB)
		.sendKeys("3265148545").sendKeys(Keys.TAB)
		.sendKeys("784qwe").sendKeys(Keys.TAB)
		.sendKeys("784qwe").sendKeys(Keys.TAB)
		.sendKeys(Keys.ARROW_LEFT)
		.sendKeys(Keys.TAB).sendKeys(Keys.TAB)
		.sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		
		Assert.assertTrue(accountsuccesspage.isSuccessDisplayed());
	}
	
	@Test(priority=3)
	public void verifyRegisterPageTitleBreadcrumbURLHeading() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		String expectedTitle="Register Account";
		String Title=driver.getTitle();
		
		String expectedUrl=prop.getProperty("expectedURL");
		String url=driver.getCurrentUrl();
		
		String expectedheading="Register Account";
		String heading=registerpage.getRegisterAccountText();
		
		String expectedBreadcrumb="Register";
		String breadcrumb=registerpage.getRegisterBreadcrumbText();
				
		Assert.assertEquals(Title, expectedTitle);
		Assert.assertEquals(url, expectedUrl);
		Assert.assertEquals(heading, expectedheading);
		Assert.assertEquals(breadcrumb, expectedBreadcrumb);
		
	}
	
	@Test(priority=4)
	public void verifyOpeningUrlInAllBrowsers() {
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");	
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		driver.quit();
		
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");	
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		
	}
	
	@Test(priority=5)
	public void verifyInvalidEmails() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		registerpage.enterTextIntoEmailField(CommonUtilities.generateNewEmail());
		registerpage.enterTextIntoTelephoneField(prop.getProperty("telephone"));
		registerpage.enterTextIntoPasswordField(prop.getProperty("validpassword"));
		registerpage.enterTextIntoPasswordConfirmField(prop.getProperty("validpassword"));
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage=registerpage.clickOnContinueButton();
		String Amessage1 =driver.findElement(By.id("input-email")).getAttribute("validationMessage");
		String Emessage1="Please include an '@' in the email address. 'vartikahajeri' is missing an '@'.";
		Assert.assertEquals(Emessage1, Amessage1);
		//Similarly for different invalid emails like *vartikahajeri@, *vartikahajeri@gmail, *vartikahajeri@gmail.
		
	}
	
	@Test(priority=6)
	public void verifyRegisterWithExistingEmail() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("existingemail"));
		registerpage.enterTextIntoTelephoneField(prop.getProperty("telephone"));
		registerpage.enterTextIntoPasswordField(prop.getProperty("validpassword"));
		registerpage.enterTextIntoPasswordConfirmField(prop.getProperty("validpassword"));
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		String ExpectedWarningMessage="Warning: E-Mail Address is already registered!";
		String ActualwarningMessage=registerpage.getExistingEmailWarningText();
		Assert.assertEquals(ActualwarningMessage, ExpectedWarningMessage);
	}
	
	@Test(priority=7)
	public void verifyWarningMessageForMandatoryFields() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		String FirstnameWarning="First Name must be between 1 and 32 characters!";
		String LastnameWarning="Last Name must be between 1 and 32 characters!";
		String EmailWarning="E-Mail Address does not appear to be valid!";
		String TelephoneWarning="Telephone must be between 3 and 32 characters!";
		String PasswordWarning="Password must be between 4 and 20 characters!";
		String PrivacyWarning="Warning: You must agree to the Privacy Policy!";
		
		Assert.assertEquals(registerpage.getfirstNameWarningText(), prop.getProperty(FirstnameWarning));
		Assert.assertEquals(registerpage.getlastNameWarningText(),  prop.getProperty(LastnameWarning));
		Assert.assertEquals(registerpage.getemailWarningText(),  prop.getProperty(EmailWarning));
		Assert.assertEquals(registerpage.gettelephoneWarningText(),  prop.getProperty(TelephoneWarning));
		Assert.assertEquals(registerpage.getpasswordWarningText(),  prop.getProperty(PasswordWarning));
		Assert.assertEquals(registerpage.getprivacyWarningText(),  prop.getProperty(PrivacyWarning));
	}
	
	@Test(priority=8)
	public void verifySubscribeNewsletterY() {
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
	
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		registerpage.enterTextIntoEmailField(CommonUtilities.generateNewEmail());
		registerpage.enterTextIntoTelephoneField(prop.getProperty("telephone"));
		registerpage.enterTextIntoPasswordField(prop.getProperty("validpassword"));
		registerpage.enterTextIntoPasswordConfirmField(prop.getProperty("validpassword"));
		
		registerpage.selectNewsLetterFieldAsY();
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		myaccountpage=accountsuccesspage.clickOnContinueButtonOnAccountSuccessPage();
		newsletterpage=myaccountpage.clickOnSubscribeUnsubscribeLink();
		
		newsletterpage.newsLetterBreadcrumbisDisplayed();
		newsletterpage.newsletterRadiobuttonisSelected();
		Assert.assertTrue(newsletterpage.newsLetterBreadcrumbisDisplayed());
		Assert.assertTrue(newsletterpage.newsletterRadiobuttonisSelected());
	}
	
	@Test(priority=9)
	public void verifyAllWaysToNavigateToRegisterPOage() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		Assert.assertTrue(registerpage.registerBreadcrumbisDisplayed());
		
		//Navigate to Register Page from My Account->Login->Continue
		headeroptions.clickOnMyAccountDropDown();
		loginpage = registerpage.selectLogin();
		loginpage.selectContinue();
		Assert.assertTrue(registerpage.registerBreadcrumbisDisplayed());
		
		//Navigate to Register Page from My Account->Login->Right Menu->Register
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		loginpage = registerpage.selectLogin();
		loginpage.selectRegisterOption();
		Assert.assertTrue(registerpage.registerBreadcrumbisDisplayed());
	}
	
	@Test(priority=10)
	public void verifyPasswordConfirmByLeavingItBlank(){
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		registerpage.enterTextIntoEmailField(CommonUtilities.generateNewEmail());
		registerpage.enterTextIntoTelephoneField(prop.getProperty("telephone"));
		registerpage.enterTextIntoPasswordField(prop.getProperty("validpassword"));
		
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();
		
		String passwordWarning="Password confirmation does not match password!";
		String ActualpassWarning=registerpage.getblankPassWarningText();
		Assert.assertEquals(ActualpassWarning, passwordWarning);
		
	}
	
	@Test(priority=11, dataProvider="passwordSupplier")
	public void verifyPasswordComplexity(String passwordtext) {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		registerpage.enterTextIntoEmailField(CommonUtilities.generateNewEmail());
		registerpage.enterTextIntoTelephoneField(prop.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(passwordtext);
		driver.findElement(By.id("input-confirm")).sendKeys(passwordtext);
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();
		
		String expectedWarning="Password is not strong enough!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")), expectedWarning);
	}
	
	// Data Driven Testing DDT
	@DataProvider(name = "passwordSupplier")
	public Object[][] supplyPasswords() {
		Object[][] password = { { "12345" }, { "abcdefghi" }, { "abcd1224" }, { "abcd1234$" }, { "ASAFSF$#" } };
		return password;
	}
	
	@Test(priority=12)
	public void verifyMismatchPassword() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		registerpage.enterTextIntoEmailField(CommonUtilities.generateNewEmail());
		registerpage.enterTextIntoTelephoneField(prop.getProperty("telephone"));
		registerpage.enterTextIntoPasswordField(prop.getProperty("validpassword"));
		
		driver.findElement(By.id("input-confirm")).sendKeys("qwer");
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();
		
		Assert.assertTrue(registerpage.mismatchpassWarningisDisplayed());
	}
	
	@Test(priority=13)
	public void verifyInvalidPhoneNumber() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstname"));
		registerpage.enterTextIntoLastNameField(prop.getProperty("lastname"));
		registerpage.enterTextIntoEmailField(CommonUtilities.generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("qeqfe");//invalid number
		registerpage.enterTextIntoPasswordField(prop.getProperty("validpassword"));
		registerpage.enterTextIntoPasswordConfirmField(prop.getProperty("validpassword"));
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		Assert.assertFalse(accountsuccesspage.isSuccessDisplayed());
	}
	
	@Test(priority=14)
	public void verifyPlaceholders() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		Assert.assertEquals(registerpage.getAttributeFirstNameField(), "First Name");
		Assert.assertEquals(registerpage.getAttributeLastNameField(), "Last Name");
		Assert.assertEquals(registerpage.getAttributeEmailField(), "E-Mail");
		Assert.assertEquals(registerpage.getAttributeTelephoneField(), "Telephone");
		Assert.assertEquals(registerpage.getAttributePasswordField(), "Password");
		Assert.assertEquals(registerpage.getAttributeConfirmPassField(), "Password Confirm");
		
	}
	
	@Test(priority=15)
	public void verifyMandatoryFields() {
	
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		
		String ExpectedContent="\"* \"";
		String ExpectedColor="rgb(255, 0, 0)";
		
		String FirstNameLabelContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('content');", registerpage.getFirstNameFieldLabel());		
		Assert.assertEquals(FirstNameLabelContent, ExpectedContent);
		String FirstNameLabelColor=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('color');", registerpage.getFirstNameFieldLabel());
		Assert.assertEquals(FirstNameLabelColor, ExpectedColor);
		
		String LastNameLabelContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('content');", registerpage.getLastNameFieldLabel());
		Assert.assertEquals(LastNameLabelContent, ExpectedContent);
		String LastNameLabelColor=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('color');", registerpage.getLastNameFieldLabel());
		Assert.assertEquals(LastNameLabelColor, ExpectedColor);
		
		String EmailLabelContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('content');", registerpage.getEmailFieldLabel());
		Assert.assertEquals(EmailLabelContent, ExpectedContent);
		String EmailLabelColor=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('color');", registerpage.getEmailFieldLabel());
		Assert.assertEquals(EmailLabelColor, ExpectedColor);
		
		String TelephoneLabelContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('content');", registerpage.getTelephoneFieldLabel());
		Assert.assertEquals(TelephoneLabelContent, ExpectedContent);
		String TelephoneLabelColor=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('color');", registerpage.getTelephoneFieldLabel());
		Assert.assertEquals(TelephoneLabelColor, ExpectedColor);
		
		String PasswordLabelContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('content');", registerpage.getPasswordFieldLabel());
		Assert.assertEquals(PasswordLabelContent, ExpectedContent);
		String PasswordLabelColor=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('color');", registerpage.getPasswordFieldLabel());
		Assert.assertEquals(PasswordLabelColor, ExpectedColor);
		
		String ConfirmLabelContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('content');", registerpage.getConfirmPassFieldLabel());
		Assert.assertEquals(ConfirmLabelContent, ExpectedContent);
		String ConfirmLabelColor=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('color');", registerpage.getConfirmPassFieldLabel());
		Assert.assertEquals(ConfirmLabelColor, ExpectedColor);
		
		String PrivacyLabelContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('content');", registerpage.getPrivacyFieldLabel());
		Assert.assertEquals(PrivacyLabelContent, ExpectedContent);
		String PrivacyLabelColor=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before')"
				+ ".getPropertyValue('color');", registerpage.getPrivacyFieldLabel());
		Assert.assertEquals(PrivacyLabelColor, ExpectedColor);
			
	}
	
	@Test(priority=16, enabled=false)
	public void verifyDatabaseValidation() {
		driver= new ChromeDriver(); //Ctrl+Shift+O
		driver.manage().window().maximize();
		driver.get("http://localhost/opencart/");
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		String firstname=prop.getProperty("firstname");
		registerpage.enterTextIntoFirstNameField(firstname);
		String lastname=prop.getProperty("lastname");
		registerpage.enterTextIntoLastNameField(lastname);
		String email=CommonUtilities.generateNewEmail();
		registerpage.enterTextIntoEmailField(email);
		String password=prop.getProperty("validpassword");
		registerpage.enterTextIntoPasswordField(password);
		
		Actions action=new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(Keys.SPACE).build().perform();	
		driver.findElement(By.name("agree")).click();
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		
		//Database
		String jdbcURL="jdbc:mysql://localhost:3306/opencart_db";
		String dbUser="root";
		String dbPass="";
		
		//SQL query
		String sqlQuery="Select * from oc_customer";
		
		//JDBC Objects
		Connection connection=null;
		Statement statement =null;
		ResultSet rs=null;
		String name=null;
		String lname=null;
		String email1=null;
		int newsletter=0;
		
		try {
			//open connection
			connection=DriverManager.getConnection(jdbcURL,dbUser,dbPass);
			System.out.println("Connected to Database");
			
			//Create statement
			statement=connection.createStatement();
			
			//execute query
			rs=statement.executeQuery(sqlQuery);
			
			//process resultset
			while(rs.next()) {
				 name=rs.getString("firstname");
				 lname=rs.getString("lastname");
				 email1=rs.getString("email");
				 newsletter=rs.getInt("newsletter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(statement != null) statement.close();
				if(connection !=null) connection.close();
				}
			catch (SQLException ex) {
				ex.printStackTrace();
			}		
		}
		Assert.assertEquals(firstname, name);
		Assert.assertEquals(lastname,lname);
		Assert.assertEquals(email.equals(email),email1.equalsIgnoreCase(email1));	
	}
	
	@Test(priority=17)
	public void verifyRegisteringWithSpaces() {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField("    ");
		registerpage.enterTextIntoLastNameField("    ");
		registerpage.enterTextIntoEmailField("    ");
		registerpage.enterTextIntoTelephoneField("    ");
		registerpage.enterTextIntoPasswordField("    ");
		registerpage.enterTextIntoPasswordField("    ");
		registerpage.enterTextIntoPasswordConfirmField("    ");
		registerpage.selectNewsLetterFieldAsY();
		registerpage.selectPrivacyPolicyField();
		registerpage.clickOnContinueButton();
		
		String EFnameWarning="First Name must be between 1 and 32 characters!";
		String ELnameWarning="Last Name must be between 1 and 32 characters!";
		String EEmailWarning="E-Mail Address does not appear to be valid!";
		//String ETelephoneWarning="Telephone does not appear to be valid!";
		
		Assert.assertEquals(registerpage.getCharFNameWarningText(), EFnameWarning);
		Assert.assertEquals(registerpage.getCharLNameWarningText(), ELnameWarning);
		Assert.assertEquals(registerpage.getCharemailWarningText(), EEmailWarning);
		
		
	}
	
	@Test(priority=18)
	public void verifyHeightWidthCharOfFields() throws InterruptedException, IOException {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		String Height= "34px";
		String Width="701.25px";
		
		WebElement FirstNameField = registerpage.getFirstnameFieldElement();
		String ActualfirstNameFieldHeight = FirstNameField.getCssValue("height");
		
		Assert.assertEquals(Height, ActualfirstNameFieldHeight);
		String ActualfirstNameFieldWidth = FirstNameField.getCssValue("width");
		Assert.assertEquals(Width, ActualfirstNameFieldWidth);
		
		//Entering No Data
		String expectedFnameWarning="First Name must be between 1 and 32 characters!";
		registerpage.clickOnContinueButton();
		Assert.assertEquals(registerpage.getfirstNameWarningText(), expectedFnameWarning);
		FirstNameField = registerpage.getFirstnameFieldElement();
		//Entering single letter
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstnameinput1"));
		registerpage.clickOnContinueButton();
		Thread.sleep(3000);
		boolean fnameWarningStatus=false;
		try {
		fnameWarningStatus=registerpage.firstNameWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			fnameWarningStatus=false;
		}
		Assert.assertFalse(fnameWarningStatus);
		Thread.sleep(3000);
		//Entering maximum limit of chars-32
		FirstNameField = registerpage.getFirstnameFieldElement();
		FirstNameField.clear();
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstnameinput2"));
		registerpage.clickOnContinueButton();
		fnameWarningStatus=false;
		try {
		fnameWarningStatus=registerpage.firstNameWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			fnameWarningStatus=false;
		}
		Assert.assertFalse(fnameWarningStatus);
		
		//Entering more than maximum limit of chars-32
		FirstNameField = registerpage.getFirstnameFieldElement();
		FirstNameField.clear();
		registerpage.enterTextIntoFirstNameField(prop.getProperty("firstnameinput3"));
		registerpage.clickOnContinueButton();		
		Assert.assertEquals(registerpage.getfirstNameWarningText(), expectedFnameWarning);
		Thread.sleep(3000);
		
		
		//Last name field check
		WebElement LastNameField =registerpage.getLastnameFieldElement();
		String ActuallastNameFieldHeight = LastNameField.getCssValue("height");
		Assert.assertEquals(Height, ActuallastNameFieldHeight);
		String ActuallastNameFieldWidth = LastNameField.getCssValue("width");
		Assert.assertEquals(Width, ActuallastNameFieldWidth);
		
		//Entering No Data
		String expectedLnameWarning="Last Name must be between 1 and 32 characters!";
		registerpage.clickOnContinueButton();
		Thread.sleep(3000);
		
		Assert.assertEquals(registerpage.getlastNameWarningText(), expectedLnameWarning);
		LastNameField = registerpage.getLastnameFieldElement();
		//Entering single letter
		LastNameField.sendKeys(prop.getProperty("lastnameinput1"));
		registerpage.clickOnContinueButton();
		Thread.sleep(3000);
		boolean lnameWarningStatus=false;
		try {
			lnameWarningStatus=registerpage.lastNameWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			lnameWarningStatus=false;
		}
		Assert.assertFalse(lnameWarningStatus);
		
		//Entering maximum limit of chars-32
		LastNameField =registerpage.getLastnameFieldElement();
		LastNameField.clear();
		LastNameField.sendKeys(prop.getProperty("lastnameinput2"));
		registerpage.clickOnContinueButton();
		lnameWarningStatus=false;
		try {
			lnameWarningStatus=registerpage.lastNameWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			lnameWarningStatus=false;
		}
		Assert.assertFalse(lnameWarningStatus);
		
		//Entering more than maximum limit of chars-32
		LastNameField =registerpage.getLastnameFieldElement();
		LastNameField.clear();
		LastNameField.sendKeys(prop.getProperty("lastnameinput3"));
		registerpage.clickOnContinueButton();
		
		Assert.assertEquals(registerpage.getlastNameWarningText(), expectedLnameWarning);
		Thread.sleep(3000);
		
		
		//Email field check
		WebElement emailField = registerpage.getEmailFieldElement();
		String ActualemailFieldHeight = emailField.getCssValue("height");
		Assert.assertEquals(Height, ActualemailFieldHeight);
		String ActualemailFieldWidth = emailField.getCssValue("width");
		Assert.assertEquals(Width, ActualemailFieldWidth);
		Thread.sleep(3000);
		//Entering any number of chars
		emailField =  registerpage.getEmailFieldElement();
		emailField.sendKeys("awerjvhbernvksdvnevnewjkvnksjcvuvndjrghdkjnvdkhger@gmail.com");
		registerpage.clickOnContinueButton();
		Thread.sleep(3000);
		boolean emailWarningStatus=false;
		try {
			emailWarningStatus=registerpage.emailWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			emailWarningStatus=false;
		}
		Assert.assertFalse(emailWarningStatus);
		
		//Telephone field check
		WebElement teleField = registerpage.getTelephoneElement();
		String ActualteleFieldHeight = teleField.getCssValue("height");
		Assert.assertEquals(Height, ActualteleFieldHeight);
		String ActualteleFieldWidth = teleField.getCssValue("width");
		Assert.assertEquals(Width, ActualteleFieldWidth);
		
		String expectedTelephoneWarning= "Telephone must be between 3 and 32 characters!";
		registerpage.clickOnContinueButton();
		Thread.sleep(3000);
		Assert.assertEquals(registerpage.gettelephoneWarningText(), expectedTelephoneWarning);
		teleField = registerpage.getTelephoneElement();
		//Entering single number
		teleField.sendKeys(prop.getProperty("telephoneinput1"));
		registerpage.clickOnContinueButton();
		Assert.assertEquals(registerpage.gettelephoneWarningText(), expectedTelephoneWarning);
		Thread.sleep(2000);
		teleField =registerpage.getTelephoneElement();
		teleField.clear();
		teleField.sendKeys(prop.getProperty("telephoneinput2"));
		registerpage.clickOnContinueButton();
		Assert.assertEquals(registerpage.gettelephoneWarningText(), expectedTelephoneWarning);
		teleField =registerpage.getTelephoneElement();
		teleField.clear();
		Thread.sleep(2000);
		teleField.sendKeys(prop.getProperty("telephoneinput3"));
		registerpage.clickOnContinueButton();
		
		boolean teleWarningStatus=false;
		try {
			teleWarningStatus=registerpage.telephoneWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			teleWarningStatus=false;
		}
		Assert.assertFalse(teleWarningStatus);
		//For max chars
		teleField =registerpage.getTelephoneElement();
		teleField.clear();
		Thread.sleep(2000);
		teleField.sendKeys(prop.getProperty("telephoneinput4"));
		registerpage.clickOnContinueButton();
		teleWarningStatus=false;
		try {
			teleWarningStatus=registerpage.telephoneWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			teleWarningStatus=false;
		}
		Assert.assertFalse(teleWarningStatus);
		//For more than max chars
		teleField =registerpage.getTelephoneElement();
		teleField.clear();
		Thread.sleep(2000);
		teleField.sendKeys(prop.getProperty("telephoneinput5"));
		registerpage.clickOnContinueButton();
		teleWarningStatus=false;
		try {
			teleWarningStatus=registerpage.telephoneWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			teleWarningStatus=false;
		}
		Assert.assertEquals(registerpage.gettelephoneWarningText(), expectedTelephoneWarning);
			
		
		//Password field check
		WebElement PasswordField =registerpage.getPasswordElement();
		String ActualpassFieldHeight = PasswordField.getCssValue("height");
		Assert.assertEquals(Height, ActualpassFieldHeight);
		String ActualPassFieldWidth = PasswordField.getCssValue("width");
		Assert.assertEquals(Width, ActualPassFieldWidth);
		
		String expectedPassWarning= "Password must be between 4 and 20 characters!";
		registerpage.clickOnContinueButton();
		Thread.sleep(3000);
		Assert.assertEquals(registerpage.getpasswordWarningText(), expectedPassWarning);
		Thread.sleep(2000);
		
		PasswordField = registerpage.getPasswordElement();
		//Entering single char
		PasswordField.clear();
		PasswordField.sendKeys(prop.getProperty("passinput1"));
		registerpage.clickOnContinueButton();
		Assert.assertEquals(registerpage.getpasswordWarningText(), expectedPassWarning);
		Thread.sleep(2000);
		
		PasswordField = registerpage.getPasswordElement();
		//Entering single char
		PasswordField.clear();
		PasswordField.sendKeys(prop.getProperty("passinput2"));
		registerpage.clickOnContinueButton();
		Thread.sleep(2000);
		Assert.assertEquals(registerpage.getpasswordWarningText(), expectedPassWarning);
		Thread.sleep(2000);
		PasswordField = registerpage.getPasswordElement();
		//Entering single char
		PasswordField.clear();
		PasswordField.sendKeys(prop.getProperty("passinput3"));
		registerpage.clickOnContinueButton();
		Thread.sleep(2000);
		Assert.assertEquals(registerpage.getpasswordWarningText(), expectedPassWarning);
		
		//For 4 chars
		Thread.sleep(2000);
		PasswordField =registerpage.getPasswordElement();
		PasswordField.clear();
		Thread.sleep(2000);
		PasswordField.sendKeys(prop.getProperty("passinput4"));
		registerpage.clickOnContinueButton();
		boolean PassWarningStatus=false;
		try {
			PassWarningStatus=registerpage.passwordWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			PassWarningStatus=false;
		}
		Assert.assertFalse(PassWarningStatus);
		//For max chars-20
		Thread.sleep(2000);
		PasswordField =registerpage.getPasswordElement();
		PasswordField.clear();
		Thread.sleep(2000);
		PasswordField.sendKeys(prop.getProperty("passinput5"));
		registerpage.clickOnContinueButton();
		PassWarningStatus=false;
		try {
			PassWarningStatus=registerpage.passwordWarningTextisDisplayed();
		}catch(NoSuchElementException e){
			PassWarningStatus=false;
		}
		Assert.assertFalse(PassWarningStatus);
		
		//For more than max chars-20
				Thread.sleep(2000);
				PasswordField = registerpage.getPasswordElement();
				PasswordField.clear();
				Thread.sleep(2000);
				PasswordField.sendKeys(prop.getProperty("passinput6"));
				registerpage.clickOnContinueButton();
				PassWarningStatus=false;
				try {
					PassWarningStatus=registerpage.passwordWarningTextisDisplayed();
				}catch(NoSuchElementException e){
					PassWarningStatus=false;
				}
				Assert.assertTrue(PassWarningStatus);
				
		//Password confirm field check
				
				WebElement PasswordConfirmField = registerpage.getPasswordConfirmElement();
				String ActualpassconfirmFieldHeight = PasswordConfirmField.getCssValue("height");
				Assert.assertEquals(Height, ActualpassconfirmFieldHeight);
				String ActualPassconfirmFieldWidth = PasswordConfirmField.getCssValue("width");
				Assert.assertEquals(Width, ActualPassconfirmFieldWidth);
				
		//Continue Button field check
				
				WebElement ContinueButton = registerpage.getContinueButtonElement();
				String ContinueButtoncolor= "rgba(255, 255, 255, 1)";
				String actualContinueButtoncolor=ContinueButton.getCssValue("color");
				Assert.assertEquals(actualContinueButtoncolor, ContinueButtoncolor);
			
		//Verifying field alignment by comparing screenshots
				headeroptions.clickOnMyAccountDropDown();
				registerpage=headeroptions.selectRegisterOption();
				TakesScreenshot ts = (TakesScreenshot)driver;
				File srcfile = ts.getScreenshotAs(OutputType.FILE);
				try {
					FileHandler.copy(srcfile, new File(System.getProperty("user.dir")+"\\Screenshots\\ActualRegisterPgSnap.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}				
				Assert.assertFalse(CommonUtilities.CompareScreenshots(System.getProperty("user.dir")+"\\Screenshots\\ActualRegisterPgSnap.png",
						System.getProperty("user.dir")+"\\Screenshots\\ExpectedRegisterPgSnap.png"));
	}
	
	@Test(priority=19)
	public void verifyLeadingTrailingSpacesinAllFields() throws InterruptedException {
		SoftAssert soft=new SoftAssert();
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		registerpage.enterTextIntoFirstNameField("    "+prop.getProperty("firstname")+"    ");
		registerpage.enterTextIntoLastNameField("    "+prop.getProperty("lastname")+"    ");
		registerpage.enterTextIntoEmailField("    " +CommonUtilities.generateNewEmail()+"   ");
		registerpage.enterTextIntoTelephoneField("    "+prop.getProperty("telephone")+"    ");
		registerpage.enterTextIntoPasswordField("    "+prop.getProperty("validpassword")+"    ");
		registerpage.enterTextIntoPasswordConfirmField("    "+prop.getProperty("validpassword")+"    ");
		registerpage.selectNewsLetterFieldAsY();
		registerpage.selectPrivacyPolicyField();
		accountsuccesspage=registerpage.clickOnContinueButton();
		Thread.sleep(2000);
		myaccountpage=accountsuccesspage.clickOnContinueButtonOnAccountSuccessPage();
		Thread.sleep(2000);
		editinfopage = myaccountpage.selectEditAccInfoLink();
		Thread.sleep(2000);
		String actualFirstName=editinfopage.getFnameValue();
		String actualLastName=editinfopage.getLnameValue();
		String actualtelephone=editinfopage.getTelephoneValue();
		
		soft.assertEquals(actualFirstName, prop.getProperty("firstname").trim());
		soft.assertEquals(actualLastName, prop.getProperty("lastname").trim());
		soft.assertEquals(actualtelephone, prop.getProperty("telephone").trim());
		soft.assertAll();
	}
	
	@Test(priority=20)
	public void verifyPrivacyisNotSelected()  {
		
		headeroptions=new HeaderOptions(driver);
		headeroptions.clickOnMyAccountDropDown();
		registerpage=headeroptions.selectRegisterOption();
		
		Assert.assertFalse(registerpage.isPrivacyPolicySelected());
		
		//Verify by not checking privacy checkbox
		registerpage.enterTextIntoFirstNameField("    "+prop.getProperty("firstname")+"    ");
		registerpage.enterTextIntoLastNameField("    "+prop.getProperty("lastname")+"    ");
		registerpage.enterTextIntoEmailField("    " +CommonUtilities.generateNewEmail()+"   ");
		registerpage.enterTextIntoTelephoneField("    "+prop.getProperty("telephone")+"    ");
		registerpage.enterTextIntoPasswordField("    "+prop.getProperty("validpassword")+"    ");
		registerpage.enterTextIntoPasswordConfirmField("    "+prop.getProperty("validpassword")+"    ");
		registerpage.selectNewsLetterFieldAsY();
		accountsuccesspage=registerpage.clickOnContinueButton();
		
		String Warningmessage="Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(registerpage.getprivacyWarningText(), Warningmessage);
				
		//Verify whether password field has hidden visibility--> it works only for password input type
		Assert.assertEquals(registerpage.getTypeAttributePasswordField(), "password");
		Assert.assertEquals(registerpage.getTypeAttributeConfirmPassField(), "password");
		
		
	}
	
}
