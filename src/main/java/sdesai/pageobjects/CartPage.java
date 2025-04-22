package sdesai.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import sdesai.AbstractCompo.AbstractCompo;

public class CartPage extends AbstractCompo{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".cartSection h3") List<WebElement> cartProducts;
	@FindBy(css=".totalRow button") WebElement checkOut;
	
	public boolean verifyProductDisplay(String productName)
	{
		Boolean cart=cartProducts.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		return cart;
	}
	public CheckOutPage goTocheckOut()
	{
		checkOut.click();
		return new CheckOutPage(driver);
	}
}	


