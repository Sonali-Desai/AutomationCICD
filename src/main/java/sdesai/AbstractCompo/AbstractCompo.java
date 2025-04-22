package sdesai.AbstractCompo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sdesai.pageobjects.CartPage;
import sdesai.pageobjects.OrderPage;

public class AbstractCompo {
	WebDriver driver;
	public AbstractCompo(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']") WebElement orderHeader;
	
	public CartPage goToCart()
	{
		cartHeader.click();
		return new CartPage(driver);
	}
	public OrderPage goToOrder()
	{
		orderHeader.click();
		return new OrderPage(driver);
	}
	
	
	public void waitForElementAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	public void waitForElementAppear(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForElementDisapp(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
