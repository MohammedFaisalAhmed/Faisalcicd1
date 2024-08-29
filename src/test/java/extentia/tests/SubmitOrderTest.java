package extentia.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import extentia.TestComponents.BaseTest;
import extentia.pageObjects.CartPage;
import extentia.pageObjects.CheckoutPage;
import extentia.pageObjects.ConfirmationPage;
import extentia.pageObjects.LandingPage;
import extentia.pageObjects.OrderPage;
import extentia.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
@Test(dataProvider="getData",groups= {"Purchase"})
public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
{
	
		
		
		ProductCatalogue productcatalogue= landingpage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products =productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		
		
		CartPage cartpage=productcatalogue.goToCartPage();
		
		Boolean match=cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutPage.submitOrder();
		
		String confirmMessage =confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

@Test(dependsOnMethods= {"submitOrder"})
public void orderHistoryTest()
{
	
	ProductCatalogue productcatalogue= landingpage.loginApplication("Faisal1@gmail.com", "Gmail123456@");
	OrderPage orderpage =productcatalogue.goToOrderPage();
	Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));

}



@DataProvider
public Object[][] getData() throws IOException
{
	/*HashMap<String,String> map = new HashMap<String,String> ();
	map.put("email", "Faisal1@gmail.com");
	map.put("password", "Gmail123456@");
	map.put("product", "ZARA COAT 3");
	
	HashMap<String,String> map1 = new HashMap<String,String> ();
	map1.put("email", "Faisal1@gmail.com");
	map1.put("password", "Gmail123456@");
	map1.put("product", "ADDIDAS ORIGINAL");*/
	
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\extentia\\data\\PurchaseOrder.json");
	return new Object[][] { {data.get(0)},{data.get(1)}};
	
	

	
}



}
