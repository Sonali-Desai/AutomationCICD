package sdesai.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sdesai.AbstractCompo.AbstractCompo;

public class LandingPage extends AbstractCompo{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id="userEmail")WebElement email;
	@FindBy(id="userPassword")WebElement password;
	@FindBy(id="login")WebElement login;
	@FindBy(css="[class*='flyInOut']") WebElement errorMsg;
	public ProductCatlog loginApp(String uname,String pass)
	{
		email.sendKeys(uname);
		password.sendKeys(pass);
		login.click();
		return new ProductCatlog(driver);
	}
	public String getErrorMsg() 
	{
		waitForElementAppear(errorMsg);
		return errorMsg.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
