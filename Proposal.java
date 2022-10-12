package week5.assignment;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Proposal extends BaseClass {

	@BeforeTest @BeforeClass
	public void setFileName()
	{
		filename="proposalExcel";
	}

	@Test (dataProvider="excelResult")

	public void newProposal(String descriptionValue, String inputValue) throws InterruptedException {
		WebElement filter=dom.findElementByXPath("//input[@id='filter']");
		filter.click();
		filter.sendKeys("Proposal");
		//filter.sendKeys(Keys.ENTER);
		dom.findElementByXPath("//span[@class='item-icon']//mark[text()='Proposal']").click();
		// switch to frame
		WebElement frameSwitch = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frameSwitch);

		// Click on New button
		driver.findElement(By.xpath("//button[text()='New']")).click();
		Thread.sleep(2);
		//Template description
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(descriptionValue);
		//Go to change request values 
		driver.findElement(By.xpath("//span[text()='Change Request values']")).click();
		Thread.sleep(10);
		// choose value from drop down
		driver.findElement(By.xpath("//select[@class='filerTableSelect select2 form-control filter_type select2-offscreen']/preceding-sibling::div")).click();;
		//Select shortDescOption=new Select(shortDesc);
		Actions builder=new Actions(driver);
		WebElement selectMoveEle = driver.findElement(By.xpath("//div[text()='Service']"));
		builder.moveToElement(selectMoveEle).pause(Duration.ofSeconds(2)).perform();
		selectMoveEle.click();
		// give input values
		Thread.sleep(10);
		driver.findElement(By.xpath("//input[@class='pull-left form-control filter-reference-input']")).sendKeys(inputValue);
		//click on submit
		Thread.sleep(5);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		//verify the proposal creation
		List<WebElement>  description= driver.findElements(By.xpath("//tbody[@class='list2_body']//td[4]"));
		boolean flag=false;
		for (WebElement descList : description) {
			String listName = descList.getText();
			if (listName.contains(descriptionValue))
			{
				flag=true;
				break;
			}
		}
		if(flag==true) {
			System.out.println("proposal is created");
		}else {
			System.out.println("proposal is not created");
		}
	}
}

