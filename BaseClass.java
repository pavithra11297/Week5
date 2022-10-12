package week5.assignment;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class BaseClass {
	public ChromeDriver driver;
	public Shadow dom;
	String filename;

	@Parameters({"url","username","password"})
	@BeforeMethod
	public void precondition(String url,String username,String password) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("disable-notifications");
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Go to Url
		driver.get(url);
		dom=new Shadow(driver); 
		driver.manage().window().maximize();
		//Login
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(4000);
		dom.setImplicitWait(25);
		//Click on ALL menu
		WebElement allMenu = dom.findElementByXPath("//div[text()='All']");
		allMenu.click();
	}

	@AfterMethod(alwaysRun=true)
	public void postCondition()
	{
		//Close the all browser
		driver.close();
	}
	//read the excel file
	@DataProvider(name="excelResult")
	public String[][] fetchdata() throws IOException{
		String[][] data;
		data=ReadExcelData.readSheetData(filename);
		return data;
	}
}