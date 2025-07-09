package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verify_LoginDDT(String email,String pwd,String exp) {
		
		logger.info("***TC003_LoginDDT Started***");
		
		try {
		
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountExist();
		
		/*if Data is valid & login successful--> Test Passed
		 * if Data is valid & login unsuccessful--> Test failed
		 * if Data is invalid & login successful--> Test failed
		 * if Data is invalid & login unsuccessful--> Test Passed
		*/
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetpage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetpage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				
				Assert.assertTrue(true);
			}
		}
		
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***TC003_LoginDDT Finished***");
		
	}

}
