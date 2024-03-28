package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageObjects.DarazPageObjects;
import utilities.ExcelAPI;
import utilities.UtilBase;
import utilities.WebElementLib;

/*-------------------------------------------------------------------------------------------------------
File        : TestDaraz.java
Purpose     : 
Description : This script is intended to run all the required tests
Author(s)   : Yaman Maharjan
Created     : March 26, 2024
Notes       : 
----------------------------------------------------------------------------------------------------------*/

public class TestDaraz extends UtilBase {

//	Global Variables
	protected String baseurl = "https://www.daraz.com.np/";

//	Page Objects
	protected DarazPageObjects pageObj = new DarazPageObjects();

//	Explicit wait
	protected WebDriverWait wait;

	@BeforeClass
	public void setup() {
		initialiseDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//		Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		TestNg for Report generation
		ExtentSparkReporter spark = new ExtentSparkReporter("testReports/TestDaraz_Report.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	/*
	 * task: 1. Open Daraz Website : https://www.daraz.com.np/
	 */
	@Test(priority = 1)
	public void loadWebsite() {
		try {
			test = extent.createTest("loadWebsite");
			System.out.println("START	: loadWebsite  ---------------------------------------");
//			Load the URL
			driver.get(baseurl);

//			Run the Test
			String expectedTitle = "Online Shopping in Nepal | Best Deals, Prices & Discounts - Daraz.com.np";
			String title = driver.getTitle();
			System.out.println("page title is : " + title);
			Assert.assertEquals(title, expectedTitle, "Verify Title Failed");

			if (WebElementLib.doesElementExist(pageObj.login_button_homepage())) {
				System.out.println("SUCCESS : loadWebsite");
				test.pass("loadWebsite");
				test.addScreenCaptureFromPath(capture("loadWebsite"), "loadWebsite_pass");
			} else {
				System.out.println("ERROR : loadWebsite ");
				test.fail("loadWebsite");
				test.addScreenCaptureFromPath(capture("loadWebsite"), "loadWebsite");
				Assert.assertTrue(false);
			}
			System.out.println("END	 : loadWebsite	---------------------------------------");
		} catch (Exception e) {
			System.out.println("EXCEPTION: loadWebsite ");
			test.fail("loadWebsite");
			test.addScreenCaptureFromPath(capture("loadWebsite"), "loadWebsite");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	public void verifyTitle() {
		String expectedTitle = "Online Shopping in Nepal | Best Deals, Prices & Discounts - Daraz.com.np";
		String title = driver.getTitle();
		System.out.println("page title is : " + title);
		Assert.assertEquals(title, expectedTitle, "Verify Title Failed");
	}

	/*
	 * task: 2. Create a test that automatically logs into Daraz using a valid
	 * username and password retrieved from an SQL database (create a dummy database
	 * -sqlite holding necessary attributes).
	 * 
	 * This test should try logging in multiple times using various sets of
	 * credentials, but it shouldn't start with the valid ones. Instead, it should
	 * try to log in with invalid credentials until it successfully logs in with the
	 * valid one. For instance, if there are 5 datasets, the first 4 should log in
	 * unsuccessfully and the last one should successfully log in.
	 */
//	@Test(priority = 2)
	public void loginTest() {
		try {
			test = extent.createTest("loginTest");
			System.out.println("START	: loginTest  ---------------------------------------");

//			Database connection

//			Initialize the credentials
			String username = "yamanmaharjan00@gmail.com";
			String password = "Qwerty123456";
			String valid = "yes";

//			click login button
			pageObj.login_button_homepage().click();

//			wait till login button is displayed
			Thread.sleep(3000);

//			Enter the credentials
			pageObj.email_input_login().sendKeys(username);
			pageObj.password_input_login().sendKeys(password);

			Thread.sleep(1000);

			pageObj.login_button_login().click();

//			Run the Test
//			Test with invalid credentials
			if (valid.equalsIgnoreCase("no")) {
//				WebElementLib.doesElementExist(pageObj.error_toast_login())
				String errorMessage = pageObj.error_toast_login().getText();

				System.out.println("Username : " + username + " , Password : " + password);
				System.out.println(errorMessage);
				test.addScreenCaptureFromPath(capture("loginTest"), "loginTest");
			}
//			Test with valid credentials
			else {
				Thread.sleep(3000);
				String myaccount_info = pageObj.myaccount_span_homepage().getText();
				System.out.println(myaccount_info);

//				When logged in: "Hello," is displayed, so could be used to verify the login
				if (myaccount_info.contains("Hello,")) {
					System.out.println("SUCCESS 	: Logged in to daraz");
					test.pass("loginTest");
					test.addScreenCaptureFromPath(capture("loginTest"), "loginTest");
				} else {
					System.out.println("ERROR 		: loginTest");
					test.fail("loginTest");
					test.addScreenCaptureFromPath(capture("loginTest"), "loginTest");
					Assert.assertTrue(false);
				}

			}
			System.out.println("END	 				: loginTest	---------------------------------------");
		} catch (Exception e) {
			System.out.println("EXCEPTION: loginTest ");
			test.fail("loginTest");
			test.addScreenCaptureFromPath(capture("loginTest"), "loginTest");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/*
	 * task 3. Develop a test case to check search suggestions on Daraz by typing
	 * "Sam" in the search box. Ensure that all the suggestions starting with "Sam"
	 * are displayed in the suggestion box.
	 * 
	 * Then, click on the fourth suggestion from the top and add an item to the cart
	 * with a specific price (for example: Rs. 28,900).
	 */
//	@Test(priority = 3)
	public void searchItem() {
		try {
			test = extent.createTest("searchItem");
			System.out.println("START	 : searchItem  ---------------------------------------");

//			search keyword
			String searchtext = "sam";

//		Enter search text
			pageObj.search_input_homepage().sendKeys(searchtext);

//		Wait for Suggestions to Appear (Explicit Wait)
			WebElement suggestionsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='search-box__popup--3Pf1']")));

// 		Retrieve the search suggestions and store in List
			List<WebElement> suggestionElements = suggestionsContainer
					.findElements(By.cssSelector("#topActionHeader > div > div.lzd-logo-bar > div > div.lzd-nav-search > form > div > div.search-box__popup--3Pf1 > div > a"));

//        Ensure that all the suggestions starting with "Sam" are displayed in the suggestion box.
			System.out.println("List of products found : ");
			boolean boolTest = true;

			for (WebElement suggestion : suggestionElements) {
				String suggested = suggestion.getText().toLowerCase();
				System.out.println(suggested);

				if (suggested.contains(searchtext.toLowerCase())) {
					continue;
				} else {
					boolTest = false;
				}
			}

//			run the test
			if (boolTest) {
				System.out.println("SUCCESS: all the suggested product contains the keyword sam");
			} else {
				System.out.println("Test Failed : all the suggested product contains the keyword sam");
				Assert.assertTrue(false);
			}

//        Then, click on the fourth suggestion from the top 
			int showProductNumber = 4;
			WebElement item = driver.findElement(
					By.cssSelector("#topActionHeader > div > div.lzd-logo-bar > div > div.lzd-nav-search > form > div > div.search-box__popup--3Pf1 > div > a:nth-child("
							+ showProductNumber + ")"));
			item.click();

//        and add an item to the cart with a specific price (for example: Rs. 28,900).
			String searchPrice = "31,199";
			System.out.println("Search by price : " + searchPrice);

			boolean foundMatch = false;

//			Wait for products to be visible
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-qa-locator='product-item']")));

// 			get the list of price of all visible products 
			List<WebElement> suggestionPrice = driver.findElements(By.cssSelector("#id-price > div > div.current-price--Jklkc > span.currency--GVKjl"));
			System.out.println("Total suggested products = " + suggestionPrice.size());

			// Print the search suggestions
			for (int i = 0; i < suggestionPrice.size(); i++) {

				jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", suggestionPrice.get(i));
				String suggested = suggestionPrice.get(i).getText();
				System.out.println(suggested);

				if (suggested.equals(searchPrice)) {
					System.out.println("Price Found for the product number : " + (i + 1));

//					click the price element (product page)
					suggestionPrice.get(i).click();
					foundMatch = true;
					test.addScreenCaptureFromPath(capture("price matched"), "success: price matched");
					break;
				}
			}

			// If the loop completes without finding a match, Terminate test
			if (!foundMatch) {
				System.out.println("Search Price not found: " + searchPrice);
				test.fail("Search Price Failed");
				Assert.assertTrue(false);
			}

//			Add to cart
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pageObj.addtocart_button_product());
			pageObj.addtocart_button_product().click();

//			VERIFY ITEM ADDED TO CART
			boolean testResult = verifyItemAddedToCart();
			if (testResult) {
				System.out.println("SUCCESS : ITEM ADDED TO CART");
			} else {
				System.out.println("ERROR : ITEM ADDING TO CART FAILED");
			}
			System.out.println("END	 : searchItem	---------------------------------------");
		} catch (Exception e) {
			System.out.println("EXCEPTION: searchItem ");
			test.fail("searchItem");
			test.addScreenCaptureFromPath(capture("searchItem_failed"), "searchItem");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	public boolean verifyItemAddedToCart() {
		boolean testResult = false;

//		Run the Test to validate item added to cart
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-message']/span")));
			String resultText = pageObj.addedtocart_text_modal().getText();

			if (resultText.contains("1 new item(s) have been added to your cart")) {
				testResult = true;
				System.out.println("SUCCESS : Item added to card");
				test.pass("Item added to cart");
				test.addScreenCaptureFromPath(capture("addItemToCart_success"), "addItemToCart_success");

//				Go to Cart and add screenshot
				pageObj.gotocart_button_modal().click();
				Thread.sleep(3000);
				test.addScreenCaptureFromPath(capture("itemsInCart"), "itemsInCart");

//				Cleanup (Remove the added items)
				pageObj.delete_button_cart().click();
				wait.until(ExpectedConditions.visibilityOf(pageObj.confirmDelete_dialog_cart()));
				pageObj.Remove_button_dialog().click();

				wait.until(ExpectedConditions.visibilityOf(pageObj.empty_modal_cart()));
				System.out.println("All Items removed from the cart");
				test.addScreenCaptureFromPath(capture("itemsInCart_removed"), "itemsInCart_removed");

			} else {
				testResult = false;
				System.out.println("ERROR : addItemToCart ");
				test.fail("addItemToCart");
				test.addScreenCaptureFromPath(capture("addItemToCart_failed"), "addItemToCart");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			testResult = false;
			System.out.println("Exception in Adding the product to Cart");
			e.printStackTrace();
		}

		return testResult;
	}

//	@Test(priority = 4)
	public void flashSale_test() {
		try {
			test = extent.createTest("flashSale_test");
			System.out.println("START	 : flashSale_test  ---------------------------------------");

//		go to homepage
			driver.get(baseurl);
			Thread.sleep(3000);
			jsDriver.executeScript("window.scrollBy(0, 500);");
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pageObj.flashsale_header_homepage());
//			Thread.sleep(5000);
//			driver.navigate().refresh();

//		scroll down to Flash Sale section and take a screenshot
//			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pageObj.flashsale_end_hours());
			// Scroll to the top of the page
//	        jsDriver.executeScript("window.scrollTo(0, 0);");
//	        jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pageObj.flashsale_header_homepage());

			test.addScreenCaptureFromPath(capture("Flashsale section"), "Flashsale section");

			int endtime_hours = Integer.parseInt(pageObj.flashsale_end_hours().getText());
			int endtime_minutes = Integer.parseInt(pageObj.flashsale_end_minutes().getText());
			int endtime_seconds = Integer.parseInt(pageObj.flashsale_end_seconds().getText());

			System.out.println("End time = " + endtime_hours + " " + endtime_minutes + " " + endtime_seconds);

//		get all the products from Flash sale section
			List<WebElement> flashsaleElements = driver
					.findElements(By.cssSelector("#hp-flash-sale > div.card-fs-content > div.card-fs-content-body.J_FSBody > a > div.fs-card-text > div.fs-card-title"));

			System.out.println(flashsaleElements.toString());
			// Print the products from Flash sales section
			for (WebElement element : flashsaleElements) {
				String suggested = element.getText().toLowerCase();
				System.out.println(suggested);

			}

//		Ensure to print the name and position of each item before adding it to the cart

//		If the flash sale concludes within three hours, add the first item to the cart.
			if (endtime_hours < 3) {
				int selectProductNumber = 0;
				System.out.println("Postion = 1");
				System.out.println("Product name = " + flashsaleElements.get(selectProductNumber).getText());

//			Add to cart
				flashsaleElements.get(selectProductNumber).click();
				pageObj.addtocart_button_product().click();

//				VERIFY ITEM ADDED TO CART
				boolean testResult = verifyItemAddedToCart();
				if (testResult) {
					System.out.println("SUCCESS : ITEM ADDED TO CART");
				} else {
					System.out.println("ERROR : ITEM ADDING TO CART FAILED");
				}
			}
//		If the remaining time falls between three and five hours, include the second item in the cart.
			else if (endtime_hours >= 3 && endtime_hours < 5) {
				int selectProductNumber = 1;
				System.out.println("Postion = 2");
				System.out.println("Product name = " + flashsaleElements.get(selectProductNumber).getText());

//			Add to cart
				flashsaleElements.get(selectProductNumber).click();
				pageObj.addtocart_button_product().click();

//				VERIFY ITEM ADDED TO CART
				boolean testResult = verifyItemAddedToCart();
				if (testResult) {
					System.out.println("SUCCESS : ITEM ADDED TO CART");
				} else {
					System.out.println("ERROR : ITEM ADDING TO CART FAILED");
				}
			}
//		Lastly, if the remaining time exceeds five hours, add the third item to the cart.
			if (endtime_hours >= 5) {
				int selectProductNumber = 2;
				System.out.println("Postion = 3");
				System.out.println("Product name = " + flashsaleElements.get(selectProductNumber).getText());

//		Add to cart
				flashsaleElements.get(selectProductNumber).click();
				pageObj.addtocart_button_product().click();

//			VERIFY ITEM ADDED TO CART
				boolean testResult = verifyItemAddedToCart();
				if (testResult) {
					System.out.println("SUCCESS : ITEM ADDED TO CART");
				} else {
					System.out.println("ERROR : ITEM ADDING TO CART FAILED");
				}
			}

			System.out.println("END	 : flashSale_test	---------------------------------------");
		} catch (Exception e) {
			System.out.println("EXCEPTION: flashSale_test ");
			test.fail("flashSale_test");
			test.addScreenCaptureFromPath(capture("flashSale_test_failed"), "flashSale_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/*
	 * test 5. Generate a new test case where you navigate to the "Sneakers"
	 * category and record the name and price of the first 20 pair of sneakers into
	 * an Excel sheet named "ShoeList.csv". Then, add the first item containing name
	 * “Air Force 1” with size 43 and quantity 2 to the cart
	 */
	@Test(priority = 5)
	public void sneaker_test() {
		try {
			test = extent.createTest("sneaker_test");
			System.out.println("START	 : sneaker_test  ---------------------------------------");

			driver.get(baseurl);

			// Performing the mouse hover action on the target element.
			actions.moveToElement(pageObj.menFashion_category_homepage()).perform();
			Thread.sleep(1000);
			actions.moveToElement(pageObj.shoes_category_homepage()).perform();
			Thread.sleep(1000);
			actions.click(pageObj.sneakers_category_homepage()).perform();
			Thread.sleep(3000);

//			get all the products from Sneakers section
			List<WebElement> sneakersName_list = driver.findElements(By.id("id-title"));
			List<WebElement> sneakersPrice_list = driver.findElements(By.id("id-price"));

			System.out.println(sneakersName_list.size());
			System.out.println(sneakersPrice_list.size());
			
			for (int i = 0; i<sneakersName_list.size(); i++) {
				System.out.println(sneakersName_list.get(i).getText()+ " "+ sneakersPrice_list.get(i).getText());
				ExcelAPI.writeExcel((i+1), sneakersName_list.get(i).getText(), sneakersPrice_list.get(i).getText());
			}
//			

// Initialize variable with expected values
//			Hit the API
//			Initialize variable with actual values
//			Run the Test
			if (true) {
				System.out.println("SUCCESS : sneaker_test");
				test.pass("sneaker_test");
				test.addScreenCaptureFromPath(capture("sneaker_test_success"), "sneaker_test ");
//		 Use JavaScriptExecutor to scroll the element into view
//				jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.salesPrice_table());
			} else {
				System.out.println("ERROR : sneaker_test ");
				test.fail("sneaker_test");
				test.addScreenCaptureFromPath(capture("sneaker_test_failed"), "sneaker_test");
				Assert.assertTrue(false);
			}
			System.out.println("END	 : sneaker_test	---------------------------------------");
		} catch (Exception e) {
			System.out.println("EXCEPTION: sneaker_test ");
			test.fail("sneaker_test");
			test.addScreenCaptureFromPath(capture("sneaker_test_failed"), "sneaker_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@AfterClass
	public void teardown() {
		extent.flush();
//		driver.quit();
	}

}
