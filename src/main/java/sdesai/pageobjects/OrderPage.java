package sdesai.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sdesai.AbstractCompo.AbstractCompo;

public class OrderPage extends AbstractCompo{
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="tr td:nth-child(3)") List<WebElement> products;

	public boolean verifyOrderDisplay(String productName)
	{
		Boolean cart=products.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		return cart;
	}

}


