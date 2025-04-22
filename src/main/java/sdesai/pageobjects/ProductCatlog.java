package sdesai.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sdesai.AbstractCompo.AbstractCompo;

public class ProductCatlog extends AbstractCompo {
	WebDriver driver;
	public ProductCatlog(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css=".mb-3")List <WebElement> products;
	@FindBy(css=".ng-animating")WebElement spinner;
	By prodBy=By.cssSelector(".mb-3");
	By addToCart=By.xpath("//button[text()=' Add To Cart']");
	By toast=By.id("toast-container");
	public List<WebElement> getProductList()
	{
		waitForElementAppear(prodBy);
		return products;
	}
	public WebElement getProductName(String productName)
	{
		WebElement prod=getProductList().stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductName(productName);
		prod.findElement(addToCart).click();
		waitForElementAppear(toast);
		waitForElementDisapp(spinner);
	}
}
