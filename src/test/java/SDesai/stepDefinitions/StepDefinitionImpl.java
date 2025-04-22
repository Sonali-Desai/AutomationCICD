package SDesai.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SDesai.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sdesai.pageobjects.CartPage;
import sdesai.pageobjects.CheckOutPage;
import sdesai.pageobjects.ConfirmPage;
import sdesai.pageobjects.LandingPage;
import sdesai.pageobjects.ProductCatlog;
public class StepDefinitionImpl extends BaseTest{
	ConfirmPage confirm;
	public LandingPage login;
	ProductCatlog productCat;
	@Given("I landed on ecommerce page")
	public void I_landend_on_WebPage() throws IOException
	{
		login=launchApp();
	}
	@Given("^I logged in with username (.*) and password (.*)$")
	public void looged_in_username_and_password(String username,String pass)
	{
		productCat=login.loginApp(username,pass);
	}
	@When("^I add product (.+) to cart$")
	public void I_add_product(String productName) throws InterruptedException
	{
		List<WebElement>products=productCat.getProductList(); 
		productCat.addProductToCart(productName);
	}
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_Submit_order(String productName)
	{
		CartPage cartPage=productCat.goToCart();
		boolean match=cartPage.verifyProductDisplay(productName);		
		Assert.assertTrue(match);
		CheckOutPage checkOut=cartPage.goTocheckOut();
		checkOut.selectCountry("india");
		confirm=checkOut.submitOrder();
	}
	@Then("{string} message is displayed on confirmation page")
	public void confirmation_msg(String string)
	{
		String msg= confirm.verifyConfrimMsg();
		Assert.assertTrue(msg.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} is displayed.")
	public void msg_displayed(String string)
	{
		Assert.assertEquals(string,login.getErrorMsg());
		driver.close();
	}
}
