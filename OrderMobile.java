package week5.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class OrderMobile extends  BaseClass{
	
	@Test
	public  void mobileOrder() throws InterruptedException {
		WebElement filter=dom.findElementByXPath("//input[@id='filter']");
		filter.click();
		filter.sendKeys("service Catalog", Keys.ENTER);
		dom.findElementByXPath("//span[@class='item-icon']//mark[text()='Service Catalog']").click();
		Thread.sleep(2);
		//Switch to frame
		WebElement frameSwitch = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frameSwitch);
		//click on mobile
		driver.findElement(By.xpath("(((//table[@id='homepage_grid']//tbody//tr)[2]//td)[30]//div[@class='drag_section'])[4]")).click();
		//select iapple
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 13 pro']")).click();
		//  Is this a replacement for a lost or broken iPhone? choose NO
		driver.findElement(By.xpath("//label[text()='No']")).click();
		// Monthly data allowance
		WebElement allowanceSelectBox = driver.findElement(By.xpath("//div[@class='form-group is-required sc-row sc-row-6']//select"));
		Select allowSelect=new Select(allowanceSelectBox);
		allowSelect.selectByIndex(2);
		// Choose the colour
		driver.findElement(By.xpath("//label[text()='Gold']")).click();
		
		Thread.sleep(5);
		//select order now option
		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
		Thread.sleep(5);
		//verify order placed
		String orderPlacedMsg = driver.findElement(By.xpath("//div[@class='notification notification-success']//span")).getText();
		if(!orderPlacedMsg.equals(""))
		{
			System.out.println("Order is Placed");
		}
		else
		{
			System.out.println("Order is not placed");
		}
		//Copy Request number
		String requestNumber = driver.findElement(By.id("requesturl")).getText();
		System.out.println("Mobile is Ordred and Request Number is: "+requestNumber);
	}
}


