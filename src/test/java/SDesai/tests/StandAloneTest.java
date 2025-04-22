package SDesai.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productName="ZARA COAT 3";
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("sd@abc.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abc@1234");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> items=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=items.stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartpro=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean cart=cartpro.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(cart);
		driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'action__submit')]")));
		WebElement btn=driver.findElement(By.xpath("//a[contains(@class,'action__submit')]"));
		a.moveToElement(btn).click().perform();
		
		String fa=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(fa.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}

}
