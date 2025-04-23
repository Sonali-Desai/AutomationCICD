package SDesai.tests;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SDesai.TestComponents.BaseTest;
import SDesai.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;
import sdesai.pageobjects.CartPage;
import sdesai.pageobjects.CheckOutPage;
import sdesai.pageobjects.ConfirmPage;
import sdesai.pageobjects.LandingPage;
import sdesai.pageobjects.ProductCatlog;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"errorhandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {


		String productName="ZARA COAT 3";
		login.loginApp("sb@abc.com", "Abc@1274");
		Assert.assertEquals("Incorrect email or password.",login.getErrorMsg());

	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {


		String productName="ZARA COAT 3";
		ProductCatlog productCat=login.loginApp("sd@abc.com", "Abc@1234");
		List<WebElement>products=productCat.getProductList();
		productCat.addProductToCart(productName);
		CartPage cartPage=productCat.goToCart();

		boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}

}
