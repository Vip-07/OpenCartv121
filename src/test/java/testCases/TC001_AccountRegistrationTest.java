package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	
	@Test(groups= {"Regression","Master"})
	public void AccountRegistrationTest() {
		
		try {
		logger.info("*** TC001 AcoountRegistrationTest is Started***");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Click on MyAccount link..");
		hp.clickRegistration();
		logger.info("Click on Registration link..");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);	
		
		logger.info("Filling in customer details..");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumeric());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.confirmPassword(password);
		regpage.checkedPolicy();
		regpage.clickContinue();
		
		logger.info("Validation..");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!")){
		    Assert.assertTrue(true);
		    
		}
		
		else {
			logger.error("test failed..");
			logger.debug("debug logs..");
			Assert.assertTrue(false);
			
		}
		
		}
		
		catch(Exception e){
			
			Assert.fail(); 	
		}
		logger.info("***TC001 AccountRegistrationTest is Finished***");
	}
	
	
}
