package testCase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
//import java.io.IOException;
//
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.base;

import POM.*;
//import io.github.bonigarcia.wdm.WebDriverManager;
import Utilities.excel_utility;

public class TestCase extends base{
	
	public home_page homepage;
	public  search_result_page search_result;
	giftcard_page  gift_carrd;
	public excel_utility excel= new excel_utility("C:\\Users\\2303634\\eclipse-workspace\\Hackathon_Project-1\\src\\test\\java\\Utilities\\Input.xlsx");

	

	@Test(priority=0, groups = { "smoke" })
	public void search_functionality() throws FileNotFoundException, IOException {
		
		homepage= new home_page(driver);
		homepage.searchbox();
		String input=excel.getCellData("Sheet1",0,1);
		homepage.searchbox().sendKeys(input);
		homepage.clicksearchbtn();
		
		search_result = new search_result_page(driver);
		
		search_result.close_popup();
		boolean result = search_result.header_check();
		Assert.assertTrue(result);
		
		screenShot("Search");
		
		
	}
	
	@Test(priority=1,groups = { "smoke" })
	public void filter_functionality() throws InterruptedException, FileNotFoundException, IOException {
		
		search_result.category_hover(); 
		search_result.kids_filters();
		search_result.instock_filters();
		String result=search_result.first_result();
		Assert.assertTrue(result.contains(excel.getCellData("Sheet1",1,1)));
		
		screenShot("filter");
		
	
		
	}
	
	
	@Test(priority=2,groups = { "smoke" })
	public void check_sortfuction() throws IOException {
		

		search_result.sort();
		
		List<WebElement> prices=search_result.prices();
		for(int i=1;i<5;i++) {
			
			String price=prices.get(i).getAttribute("content");
			String nprice=prices.get(i).getAttribute("content");
			int  pricee= Integer.parseInt(price);
			int  nextprice = Integer.parseInt(nprice);
			Assert.assertTrue(nextprice<=pricee);
			
			
			
			
		}
	 screenShot("check");
		
	}
	
	
	@Test(priority=3,groups = { "smoke" })
	public void check_and_print() throws FileNotFoundException, IOException {
	
		List<WebElement> Details = search_result.check_print_results();
		int r=1;
		for(int i=0;i<3;i++) {
			String detail = Details.get(i).getText();
			Assert.assertTrue(detail.contains(excel.getCellData("Sheet1",1,1)));
			excel.setCellData("Sheet2", r ,0, detail);
			r++;
			System.out.println(detail);
			
			
		}
		screenShot("print");
		
		
		
		
		}
//	 @Test(priority=4,groups = { "smoke" })
//	 public void submenu_printcheck() throws InterruptedException, IOException {
//		
//		 search_result.scroll_down();
//		 search_result.nhome_page();
//		 homepage = new home_page(driver);
//		 
//		 homepage.Hover();
//		 List<WebElement> submenu = homepage.Living_Options();
//		 int menu_count=0;
//		 for(int i=0;i<submenu.size();i++) {
//		 String sub_menu = submenu.get(i).getText();
//		 excel.setCellData("Sheet3", menu_count, 0 ,sub_menu);
//		 menu_count++;
//		 
//		 }
//		 Assert.assertEquals(9, menu_count);
//		 
//		 
//		screenShot("Submenu");
//		 
//		 
//	 }
//	 @Test(priority=5,groups = { "smoke" })
// 	public void choose_giftcard_validate() throws FileNotFoundException, IOException, InterruptedException{
//				 gift_carrd= new giftcard_page(driver);
// 		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
// 	gift_carrd.homeButtonGiftCard_click().click();
// 	String search =excel.getCellData("Sheet1", 2, 1);
// 	gift_carrd. homeButtonGiftCard_click().sendKeys(search);
// 	gift_carrd.search_btn().click();
// 	
// 	gift_carrd.gift();
// 	
// 	
//     String amt = excel.getCellData("Sheet4", 0, 1);
//     gift_carrd.searchbox_amt().sendKeys(amt);
// 	
// 	gift_carrd.nxt_button();
// 	
// 	String rname = excel.getCellData("Sheet4", 1, 1);
// 	gift_carrd.Name_receiver().sendKeys(rname);
// 	
// 	String remail = excel.getCellData("Sheet4", 2, 1);   
// 	gift_carrd.receiver_Email().sendKeys(remail);
// 	
// 	String rnumber = excel.getCellData("Sheet4", 3, 1);
// 	gift_carrd.receiver_Number().sendKeys(rnumber);
// 	
// 	String sname = excel.getCellData("Sheet4", 4, 1);
// 	gift_carrd.Name_sender().sendKeys(sname);
// 	
// 	
// 	
// 	String semail = excel.getCellData("Sheet4", 5, 1);
// 	gift_carrd.sender_Email().sendKeys(semail);
// 	
// 	String saddress = excel.getCellData("Sheet4", 6, 1);
// 	gift_carrd.Sender_Address().sendKeys(saddress);
// 
// 	
// 	
// 	
// 	
// 	String pincode = excel.getCellData("Sheet4", 7, 1);
// 	gift_carrd.Sender_pincode().sendKeys(pincode);
// 	
// 	String snumber = excel.getCellData("Sheet4", 8, 1);
// 	
// 	gift_carrd.Sender_Number().sendKeys(snumber);
// 	
// 	
// 		String message=excel.getCellData("Sheet4", 9, 1);
// 		gift_carrd.Send_message().sendKeys(message);
// 	
//
// 	gift_carrd.Confirm_button().click();
// 	Thread.sleep(3000);
// 	
// 	String AlertMessage= gift_carrd.AlertMessage();
// 	System.out.println(AlertMessage);
// 	excel.setCellData("Sheet3", 8, 0, AlertMessage);
// 	
// 	
// 	
//   screenShot("giftcard");
// 	
//	 }
//	 @Test(priority=6,groups = { "smoke" })
// 	public void checkout_validation() throws FileNotFoundException, IOException {
//		
//		 String rremail = excel.getCellData("Sheet4", 10, 1);
//		 gift_carrd.receiver_Email().clear();
//		 gift_carrd.receiver_Email().sendKeys(rremail);
//		 
//		 gift_carrd.Confirm_button().click();
//		 
//		 
//		 String examt = excel.getCellData("Sheet5", 0, 0);
//		 String acamount=gift_carrd.cnfrm_amt_validation().getText();
//		 Assert.assertEquals(acamount, examt);
//		 
//		 String exReceiverName = excel.getCellData("Sheet5", 1, 0);
//		 String acReceiverName=gift_carrd.cnfrm_rname_validation().getText();
//		 Assert.assertEquals(exReceiverName, acReceiverName);
//		 
//		 String exSendererEmail = excel.getCellData("Sheet5", 2, 0);
//		 String acSendererEmail=gift_carrd.cnfrm_semail_validation().getText();
//		 Assert.assertEquals(exSendererEmail, acSendererEmail);
//		 
//		 String exMessage = excel.getCellData("Sheet5", 3, 0);
//		 String acMessage=gift_carrd.cnfrm_message_validation().getText();
//		 Assert.assertEquals(exMessage, acMessage);
//		 
//		 screenShot("out");
//		 
//		 
//	 }
	
	
	
	
	
}