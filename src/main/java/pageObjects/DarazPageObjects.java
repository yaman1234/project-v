package pageObjects;

import org.openqa.selenium.WebElement;
import utilities.WebElementLib;

/*-------------------------------------------------------------------------------------------------------
File        : DarazPageObject.java
Purpose     : This class includes the functions to find the WebElements using the WebElementLib class and to return them
Author(s)   : Yaman Maharjan
Created     : March 26, 2024
Notes       : 
----------------------------------------------------------------------------------------------------------*/

public class DarazPageObjects {

	/*
	 * Homepage
	 */
	

	//div[@class='lzd-logo-content']/a
	public WebElement login_button_homepage() {
		return WebElementLib.findMyElement("xpath", "//li[@id='anonLogin']/a");
	}

	public WebElement myaccount_span_homepage() {
		return WebElementLib.findMyElement("xpath", "//span[@id='myAccountTrigger']");
	}

	public WebElement search_input_homepage() {
		return WebElementLib.findMyElement("xpath", "//input[@id='q']");
	}

	public WebElement search_suggestions_homepage() {
		return WebElementLib.findMyElement("xpath", "//div[@class='search-box__popup--3Pf1']");
	}
	
	public WebElement addtocart_button_product() {
		return WebElementLib.findMyElement("xpath", "//div[@id='module_add_to_cart']/div/button[2]");
	}
	
	public WebElement addedtocart_text_modal() {
		return WebElementLib.findMyElement("xpath", "//div[@class='cart-message']/span");
	}
	
	public WebElement gotocart_button_modal() {
		return WebElementLib.findMyElement("xpath", "//div[@class='cart-order-total checkout-order-total']/button");
	}
	
	
	/*
	 * 
	 */

	/*
	 * Login Page
	 */
	public WebElement email_input_login() {
		return WebElementLib.findMyElement("xpath", "//div[@class='mod-login']/div/div/input[@placeholder='Please enter your Phone Number or Email']");
	}

	public WebElement password_input_login() {
		return WebElementLib.findMyElement("xpath", "//div[@class='mod-login']/div/div/input[@placeholder='Please enter your password']");
	}

	public WebElement login_button_login() {
		return WebElementLib.findMyElement("xpath", "//div[@class='mod-login-btn']/button");
	}

	public WebElement error_toast_login() {
		return WebElementLib.findMyElement("xpath", "//div[@class='next-overlay-wrapper']/div");
	}
	/*
	 * 	
	 */
	
	/*
	 * CART
	 */
	public WebElement delete_button_cart() {
		return WebElementLib.findMyElement("xpath", "//div[@class='btn-wrap automation-btn-delete']");
	}

	public WebElement confirmDelete_dialog_cart() {
		return WebElementLib.findMyElement("xpath", "//div[@class='mod-dialog confirm mod-dialog-open']");
	}

	public WebElement Remove_button_dialog() {
		return WebElementLib.findMyElement("xpath", "//div[@class='mod-dialog confirm mod-dialog-open']/div/div/button[2]");
	}
	
	public WebElement empty_modal_cart() {
		return WebElementLib.findMyElement("xpath", "//div[@class='cart-empty-text']");
	}
	
	public WebElement homepage_link_navbar() {
		return WebElementLib.findMyElement("xpath", "//div[@class='lzd-logo-content']/a");
	}
	
	/*
	 * 
	 */
	
	/*
	 * Flash Sale
	 */
	
	public WebElement flashsale_header_homepage() {
		return WebElementLib.findMyElement("xpath", "//div[@class='card-fs-content-header J_FSHeader']");
	}
	
	public WebElement flashsale_end_hours() {
		return WebElementLib.findMyElement("xpath", "//*[@id='hours']");
	}
	
	public WebElement flashsale_end_minutes() {
		return WebElementLib.findMyElement("xpath", "//*[@id='minutes']");
	}
	public WebElement flashsale_end_seconds() {
		return WebElementLib.findMyElement("xpath", "//*[@id='seconds']");
	}
	
	
	/*
	 * Categories
	 */
	public WebElement menFashion_category_homepage() {
		return WebElementLib.findMyElement("xpath", "//*[@id='Level_1_Category_No9']/a/span[2]");
	}
	public WebElement shoes_category_homepage() {
		return WebElementLib.findMyElement("xpath", "//*[@id='J_8018372580']/div/ul/ul[3]/li[3]");
	}
	
	
	public WebElement sneakers_category_homepage() {
		return WebElementLib.findMyElement("xpath", "//*[@id='J_8018372580']/div/ul/ul[3]/li[3]/ul/ul/li[3]/a");
	}
	
	/*
	 * 
	 */
	
	/*
	 * Product 
	 */
	public WebElement name_product_search() {
		return WebElementLib.findMyElement("cssSelector", "#id-title");
	}
	public WebElement price_product_search() {
		return WebElementLib.findMyElement("cssSelector", "#id-title");
	}
	public WebElement size43_span_product() {
		return WebElementLib.findMyElement("xpath", "//span[@class='sku-variable-size' and @title='43']");
	}
	
	public WebElement quantity_input_product() {
		return WebElementLib.findMyElement("xpath", "//*[@id='module_quantity-input']/div/div/div/div/div[2]/span/input");
	}
	
	public WebElement addquantity_button_product() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"module_quantity-input\"]/div/div/div/div/div[1]/a[1]/span");
	}
	
	
}
