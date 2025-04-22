package sdesai.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sdesai.AbstractCompo.AbstractCompo;

public class CheckOutPage extends AbstractCompo{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")WebElement country;
	@FindBy(css=".ta-item:nth-of-type(2)")WebElement selectIndia;
	@FindBy(xpath="//a[contains(@class,'action__submit')]")WebElement submit;
	By results=By.cssSelector(".ta-results");
	public void selectCountry(String CountryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,CountryName).build().perform();
		waitForElementAppear(results);
		selectIndia.click();
	}
	public ConfirmPage submitOrder()
	{
		Actions a=new Actions(driver);
		a.moveToElement(submit).click().perform();
		return new ConfirmPage(driver);
	}


}
