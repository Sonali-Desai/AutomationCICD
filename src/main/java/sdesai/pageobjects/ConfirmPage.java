package sdesai.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sdesai.AbstractCompo.AbstractCompo;

public class ConfirmPage extends AbstractCompo {
	WebDriver driver;
	public ConfirmPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary") WebElement confirmMsg;
	public String verifyConfrimMsg()
	{
		return confirmMsg.getText();
	}
}
