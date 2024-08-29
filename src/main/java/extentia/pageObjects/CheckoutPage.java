package extentia.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import extentia.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	

		WebDriver driver;
		public CheckoutPage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		
		}
		
		@FindBy(css="div[class='actions'] a i")
		WebElement submit;
		
		@FindBy(css="[placeholder='Select Country']")
		WebElement country;
	
		@FindBy(xpath="(//button[contains(@class,'ta-item list-group-item ng-star-inserted')])[2]")
		WebElement selectCountry;
		
		By results = By.cssSelector(".ta-results");
		
		public void selectCountry(String countryName)
		{
			Actions a = new Actions(driver);
			a.sendKeys(country, countryName).build().perform();
			waitForElementToAppear(results);
            selectCountry.click();
	
		}
		
		public ConfirmationPage submitOrder()
		{
			Actions a = new Actions(driver);
			a.sendKeys(submit).build().perform();
		    return new ConfirmationPage(driver);
			
		}
		
	

}
