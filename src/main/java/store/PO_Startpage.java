package store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class PO_Startpage {
	WebDriver driver;

	public PO_Startpage(WebDriver d) {
		driver = d;
	}

	public void GoToPage(){
		driver.get("http://store.demoqa.com//");
	}

	public void verifyProduct(String name){
		String productToBuy = driver.findElement(By.cssSelector(".prodtitle")).getText(); 
		Assert.assertEquals(name, productToBuy);
	}
	public void clickBuyNow(){
		driver.findElement(By.cssSelector(".buynow")).click();
	}

	public void clickSlide(){
		driver.findElement(By.cssSelector(".slide")).click();
	}

	public String getProductName(){
		return driver.findElement(By.cssSelector(".product_description>h2")).getText();
	}
	public String getProductNameProductPage(){
		return driver.findElement(By.cssSelector("h1.prodtitle")).getText();
		
	}
	public void clickMoreInfo(){
		driver.findElement(By.linkText("More Info >")).click();
	}

	public void findSlideAndLoop() throws InterruptedException{
		WebElement element = driver.findElement(By.xpath("//*[@id='slides']"));
		ArrayList<String> listOfSlides = new ArrayList<String>();
		String currentSlide;
		for(int i=0;i<5;i++) {
			currentSlide = driver.findElement(By.cssSelector(".product_description>h2")).getText();
			if( false == listOfSlides.contains(currentSlide) ) {
				listOfSlides.add(currentSlide);

				if( listOfSlides.size() == 3 ) {
					break;
				}
			}
			Thread.sleep(5000);
		}
		Assert.assertEquals("Error unexpected number of slides", 3, listOfSlides.size());
	}

	public void verifyManualSlide() throws InterruptedException{
		driver.findElement(By.xpath("//*[@id='slide_menu']/a[2]")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='slides']/div[1]/div[1]/h2")).getText(),"iPhone 5");
		driver.findElement(By.xpath("//*[@id='slide_menu']/a[3]")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='slides']/div[1]/div[1]/h2")).getText(),"iPod Nano Blue");
		driver.findElement(By.xpath("//*[@id='slide_menu']/a[1]")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='slides']/div[1]/div[1]/h2")).getText(),"Magic Mouse");

	}

	public void latestBlogPost(){
		List<WebElement> list = driver.findElements(By.xpath("//*[@id='footer']/section[2]/ul/li"));
		Assert.assertEquals("Error unexpected number of items", 4, list.size());

	}



	public void verifyProductTitle(){
		for(int i = 1; i <= 4; i++) {
			WebElement productTitle = driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[" + i + "]/a[1]"));
			String nameOfProduct = productTitle.getText();
			System.out.println(nameOfProduct);
			nameOfProduct = nameOfProduct.substring(0,8);
			productTitle.click();
			String productToBuy = driver.findElement(By.cssSelector(".prodtitle")).getText().substring(0, 8); 
			System.out.println("Name of product, and product to buy: "+nameOfProduct+ productToBuy);
			Assert.assertEquals(nameOfProduct, productToBuy);
		}
	}

	public void verifyProductImg(){
		for(int i = 1; i <= 4; i++) {
			WebElement productTitle = driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[" + i + "]/a[2]/img"));
			String nameOfProduct = productTitle.getAttribute("alt");
			System.out.println("Name of product:" +nameOfProduct);
			nameOfProduct = productTitle.getAttribute("alt").substring(0, 8);
			productTitle.click();
			String productToBuy = driver.findElement(By.cssSelector(".prodtitle")).getText().substring(0, 8); 
			System.out.println("Name of product:" +nameOfProduct+", The other one:"+productToBuy);
			Assert.assertEquals(nameOfProduct, productToBuy);
		}
	}

	public void verifyProductMoreDetailjs(){
		for(int i = 1; i <= 4; i++) {
			String productTitle = driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[" + i + "]/a[1]")).getAttribute("title").substring(0, 7);
			driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[" + i + "]/a[3]")).click();
			String productToBuy = driver.findElement(By.cssSelector(".prodtitle")).getText().substring(0, 7); 
			Assert.assertEquals(productTitle, productToBuy);
		}
	}

	//uppgift2

	public void clickOnMagicMouse(){
		waitForElement(By.xpath("//*[@id='slides']/div[1]/div[2]/a/img"));
		driver.findElement(By.xpath("//*[@id='slides']/div[1]/div[2]/a/img")).click();
	}

	public void verifyProduct(){
		driver.getTitle();
		Assert.assertEquals("Error unexpected title", "Magic Mouse | ONLINE STORE", driver.getTitle()); 
	}
	public void proText(){
		String ProText = "Splashing Pixels' WordPress e-commerce themes offer the best e-commerce solution for online "
				+ "stores. Our highly customizable WordPress e-commerce theme design and technology is unlike anything "
				+ "else on the market. We put an amazing amount of care and detail into the design and functionality to "
				+ "ensure that we're creating the best user experience for your customers.";
		String Text = driver.findElement(By.className("product_description")).getText();
		Assert.assertEquals("Error unexpected description", ProText, Text);
	}
	public void proPrice(){
		String ProPrise = driver.findElement(By.className("currentprice")).getText();
		Assert.assertEquals("Error unexpected price", "$150.00", ProPrise);  
	}
	public void carterCounter(){
		String count = driver.findElement(By.className("count")).getText();
		driver.findElement(By.className("wpsc_buy_button")).click();
		waitForElement(By.xpath("//em[.='1']")); //Waits until there is 1 item in it
		String newCount = driver.findElement(By.xpath("//em[.='1']")).getText();
		int x = Integer.parseInt(count);
		int y = Integer.parseInt(newCount);
		x++;
		Assert.assertEquals(x,y);
	}
	public void clickAddToCart(){
		driver.findElement(By.cssSelector(".wpsc_buy_button")).click();
	}
	
	public void waitForElementVisible(By element){
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	public void goToMenu(By by){
		Actions actions = new Actions(driver);
		WebElement Drop = driver.findElement(By.id("menu-item-33"));
		actions.moveToElement(Drop).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to wait for menu");
		}
		driver.findElement(by).click();
	}
	public void waitForProductPage(String h1ToWaitFor){
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[.='"+h1ToWaitFor+"']")));
	}
	
	//fatar ej.. ska vi inte göra en class PO_för denna med?
	//PO = Varje sida.
	//Egentligen borde du ha en för varje sida som.. 
	
	//uppgift3
	public void DropDownList(){
		//driver.findElement(By.xpath("//*[.='iMacs']")).click();
		By accessoriesBy = By.xpath("//*[@id='menu-item-34']/a");
		By iMacsBy = By.xpath("//*[@id='menu-item-35']/a");
		By iPadsBy = By.xpath("//*[@id='menu-item-36']/a");
		By iPhonesBy = By.xpath("//*[@id='menu-item-37']/a");
		By iPodsBy = By.xpath("//*[@id='menu-item-38']/a");
		By macBooksBy = By.xpath("//*[@id='menu-item-39']/a");
		
		goToMenu(accessoriesBy);
		waitForProductPage("Accessories");  
		Assert.assertEquals("Eroor","Accessories | ONLINE STORE",  driver.getTitle());
		
		goToMenu(iMacsBy);
		waitForProductPage("iMacs"); 
		Assert.assertEquals("Error","iMacs | ONLINE STORE",  driver.getTitle());

		goToMenu(iPadsBy); 
		waitForProductPage("iPads");
		Assert.assertEquals("Error","iPads | ONLINE STORE",  driver.getTitle());

		goToMenu(iPhonesBy);
		waitForProductPage("iPhones");
		Assert.assertEquals("Error","iPhones | ONLINE STORE",  driver.getTitle());
		
		goToMenu(iPodsBy);
		waitForProductPage("iPods");
		Assert.assertEquals("Error","iPods | ONLINE STORE",  driver.getTitle());
		
		goToMenu(macBooksBy);  
		waitForProductPage("MacBooks");
		Assert.assertEquals("Error","MacBooks | ONLINE STORE",  driver.getTitle());
	}

	
	//uppgift4
	public void searchFunction(String searchValue){
		driver.findElement(By.cssSelector("input.search")).sendKeys(searchValue);  
	}	
	
	//Uppgift 5
	 public void verifyPopupCheckoutText(String product){
		 waitForElement(By.cssSelector("#fancy_notification_content>span"));
		 String popupProduct = driver.findElement(By.cssSelector("#fancy_notification_content>span")).getText();
		 Assert.assertTrue("Verify that "+product+" is in the popup field.",popupProduct.contains(product));
	 }
	 public void clickGoToCheckout(){
		 driver.findElement(By.cssSelector(".go_to_checkout")).click();
	 }
	 public void verifyGoneToCheckout(){
		 Assert.assertEquals("Checkout | ONLINE STORE",driver.getTitle());
	 }
	 public void clickContinueShopping(){
		 driver.findElement(By.cssSelector(".continue_shopping")).click();
	 }
	 public void verifyContinueShopping(){
		 List<WebElement> awesome = driver.findElements(By.cssSelector(".continue_shopping"));
		 System.out.println(awesome.size());
	 }
	 
	//Close Driver
	public void closeDriver(){
		driver.quit();
	}
	public void waitForElement(By by){
		WebDriverWait wait = new WebDriverWait(driver,30); //Waits up to 30 seconds. 
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.pollingEvery(200, TimeUnit.MILLISECONDS);
	}
}