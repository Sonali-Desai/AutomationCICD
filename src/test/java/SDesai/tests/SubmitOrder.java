package SDesai.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SDesai.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import sdesai.pageobjects.CartPage;
import sdesai.pageobjects.CheckOutPage;
import sdesai.pageobjects.ConfirmPage;
import sdesai.pageobjects.LandingPage;
import sdesai.pageobjects.OrderPage;
import sdesai.pageobjects.ProductCatlog;

public class SubmitOrder extends BaseTest {

	//ss@abc.com 
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups={"purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException {


		ProductCatlog productCat=login.loginApp(input.get("email"),input.get("password"));
		List<WebElement>products=productCat.getProductList(); 
		productCat.addProductToCart(input.get("product"));
		CartPage cartPage=productCat.goToCart();
		
		boolean match=cartPage.verifyProductDisplay(input.get("product"));		
		Assert.assertTrue(match);
		CheckOutPage checkOut=cartPage.goTocheckOut();
		checkOut.selectCountry("india");
		ConfirmPage confirm=checkOut.submitOrder();
		String msg= confirm.verifyConfrimMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));

	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatlog productCat=login.loginApp("sd@abc.com", "Abc@1234");
		OrderPage orderPage=productCat.goToOrder();
		boolean match=orderPage.verifyOrderDisplay(productName);		
		Assert.assertTrue(match);
	}

	//using json data converted to hashmap
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SDesai\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"sd@abc.com","Abc@1234","ZARA COAT 3"},{"ss@abc.com","Abc@1234","ZARA COAT 3"}};
	}
	*/
	/*
	//using hashmap
	@DataProvider
	public Object[][] getData() throws IOException
	{
		HashMap <String,String> map1=new HashMap<String,String>();
		map1.put("email", "sd@abc.com");
		map1.put("password", "Abc@1234");
		map1.put("product", "ZARA COAT 3");
		HashMap <String,String> map2=new HashMap<String,String>();
		map2.put("email", "ss@abc.com");
		map2.put("password", "Abc@1234");
		map2.put("product", "ZARA COAT 3");
		return new Object[][] {{map1},{map2};
	}
	 */
	
}
