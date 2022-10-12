package week5.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class MandatoryFields extends BaseClass{

	@Test
	public void artical() throws InterruptedException {
		
		WebElement filter=dom.findElementByXPath("//input[@id='filter']");
		filter.click();
		filter.sendKeys("Knowledge");
		dom.findElementByXPath("//span[@class='item-icon']//mark[text()='Knowledge']").click();
		WebElement frameSwitch = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frameSwitch);
		Thread.sleep(20);
		//Switch to frame

		//click on create new artical button
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		//Choose IT
		Thread.sleep(10);
		driver.findElement(By.xpath("//div[@class='input-group ref-container']//input")).sendKeys("IT",Keys.ENTER);
		//Choose Java
		Thread.sleep(5);
		driver.findElement(By.xpath("(//div[@class='input-group ref-container']//input)[2]")).sendKeys("Java",Keys.ENTER);
		//Short Description
		driver.findElement(By.xpath("//input[@id='kb_knowledge.short_description']")).sendKeys("testleaf");
		//click on Attachment link checkbox
		driver.findElement(By.xpath("(//div[@class='col-xs-10 col-sm-9 col-md-6 col-lg-5 form-field input_controls'])[9]")).click();
		//click on submit
		dom.findElementByXPath("//button[text()='Submit']").click();
		System.out.println("Artical is created");
	}
}

