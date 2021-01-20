package ddt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Logincheck {
	WebDriver driver = null;


	@BeforeMethod
	public void setup() {

		String projectPath = System.getProperty("user.dir");

		System.setProperty("webdriver.gecko.driver", projectPath+"\\driver\\geckodriver\\geckodriver.exe");

		driver = new FirefoxDriver();


	}

	@Test(dataProvider = "datalogin")
	public void logindata(String username,String password) throws InterruptedException
	{

		driver.get("https://opensource-demo.orangehrmlive.com/");

		driver.findElement(By.id("txtUsername")).sendKeys(username);

		driver.findElement(By.id("txtPassword")).sendKeys(password);

		driver.findElement(By.id("btnLogin")).click();

		Thread.sleep(200);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("https://opensource-demo.orangehrmlive.com/index.php/dashboard"),"User is not able to login Invalid Credentials ");

		System.out.println("Credentials verified User able to login");


	}
	
	
	@AfterMethod 
	public void tearDown() {

		driver.quit();

	}

	@DataProvider(name="datalogin")
	public Object[][] passData()
	{
		ExcelData obj = new ExcelData("C:\\JAVA Projects\\pilotproject\\Excelfile\\login.xlsx");
		int rows = obj.getRowCount(0);

		Object[][] data = new Object[rows][2];

		for(int i=0;i<rows;i++)
		{
			data[i][0]= obj.getData(0, i, 0);
			data[i][1]= obj.getData(0, i, 1);
		}
		return data;

	}

}


