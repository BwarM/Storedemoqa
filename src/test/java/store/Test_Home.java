package store;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_Home {
	static WebDriver driver;
	

	@BeforeClass
	public static void goToPage(){
		System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
		driver = new ChromeDriver(); 
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
	}
	//------------- Uppgift 1 -------------
	@Test
	public void VerifyBuyNowGoesToProduct(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		String product = start.getProductName();
		start.clickBuyNow();
		start.verifyProduct(product);
	}
	@Test
	public void VerifyImg(){
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		String product = start.getProductName();
		start.clickSlide();
		start.verifyProduct(product);
	}

	@Test
	public void VerifyMoreInfoGoesToProduct(){
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		String product = start.getProductName();
		start.clickMoreInfo();
		start.verifyProduct(product);
	}

	@Test
	public void VerifySlideFunction() throws InterruptedException{
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		start.findSlideAndLoop();
	}

	@Test
	public void verifyManualSlide() throws InterruptedException{
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		start.verifyManualSlide();
	}

	@Test
	public void latestBlogPost(){
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		start.latestBlogPost();
	}

	@Test
	public void verifyProductTitle(){
		PO_Startpage start= new PO_Startpage(driver);
		start.verifyProductTitle();
	}

	@Test
	public void verifyProductImg(){
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		start.verifyProductImg();
	}
	
	@Test
	public void verifyProductMoreDetailjs(){
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		start.verifyProductMoreDetailjs();
	}

	//------------- Uppgift 2 -------------

	@Test 
	public void verifyProdukt(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.clickOnMagicMouse();
		start.verifyProduct();
	}

	@Test 
	public void proText(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.clickOnMagicMouse();
		start.proText();
	}
	@Test 
	public void proPrice(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.clickOnMagicMouse(); //Vart ska testet vara? Homepage? Startsidan alltså? eller?
		start.proPrice();
	}
	@Test 
	public void carterCounter() throws InterruptedException{
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.clickOnMagicMouse();
		start.carterCounter();
	}
	
	
	//------------- Uppgift 3 -------------
	@Test 
	public void DropDownList(){
		PO_Startpage start= new PO_Startpage(driver);
		start.GoToPage();
		start.DropDownList();
	}
	//------------- Uppgift 4 -------------
	@Test
	public void searchFunction(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.searchFunction("Imac");
	}
	//------------- Uppgift 5 -------------
	@Test
	public void verifyProductTitleInPopup(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.clickBuyNow();
		String product = start.getProductNameProductPage();
		start.clickAddToCart();
		start.verifyPopupCheckoutText(product);
	}

	@Test
	public void verifyGoToCheckoutButton(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.clickBuyNow();
		String product = start.getProductNameProductPage();
		start.clickAddToCart();
		start.verifyPopupCheckoutText(product);
		start.clickGoToCheckout();
		start.verifyGoneToCheckout();
	}
	
	@Test
	public void verifyContinueShoppingButton(){
		PO_Startpage start = new PO_Startpage(driver);
		start.GoToPage();
		start.clickBuyNow();
		String product = start.getProductNameProductPage();
		start.clickAddToCart();
		start.verifyPopupCheckoutText(product);
		start.clickContinueShopping();
		start.verifyContinueShopping();
	}

	@AfterClass
	public static void closeDriver(){
		PO_Startpage start= new PO_Startpage(driver);
		//start.closeDriver();
	}
}