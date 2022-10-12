package week5.assignment;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Caller extends BaseClass {

	@BeforeTest @BeforeClass
	public void setFileName()
	{
		filename="CallerExcel";
	}
	@Test (dataProvider="excelResult")	
	public void Callers(String firstName,String lastName, String BussnessNumber) throws InterruptedException {
		WebElement filter=dom.findElementByXPath("//input[@id='filter']");
		filter.click();
		filter.sendKeys("Callers");
		//filter.sendKeys(Keys.ENTER);
		dom.findElementByXPath("//span[@class='item-icon']//mark[text()='Callers']").click();
		// switch to frame
		WebElement frameSwitch = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frameSwitch);
		// Click on New button	
		Thread.sleep(5);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		Thread.sleep(2);
		//enter first name
		driver.findElement(By.xpath("//input[@id='sys_user.first_name']")).sendKeys(firstName);
		//enter Last name
		driver.findElement(By.xpath("//input[@id='sys_user.last_name']")).sendKeys(lastName);
		//enter Business number
		driver.findElement(By.xpath("//input[@id='sys_user.phone']")).sendKeys(BussnessNumber);
		//Click submit
		Thread.sleep(5);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		//choose the column name of Business number
		Thread.sleep(10);
		WebElement dropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select bussnessNO=new Select(dropDown);
		bussnessNO.selectByVisibleText("Business phone");
		//enter the number for search box
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(BussnessNumber, Keys.ENTER);

		//verify the after search the number
		Thread.sleep(5);
		List<WebElement>  bussMbleNo= driver.findElements(By.xpath("//tbody[@class='list2_body']//td[5]"));
		boolean flag=false;
		for (WebElement mbleNoList : bussMbleNo) {
			String listName = mbleNoList.getText();
			if (listName.contains(BussnessNumber))
			{
				flag=true;
				break;
			}
		}
		if(flag==true)
		{
			System.out.println("callers is created");
		}
		else
		{
			System.out.println("callers is not created");

		}
	}
}



