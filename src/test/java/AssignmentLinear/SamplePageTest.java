package AssignmentLinear;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SamplePageTest {
	
   WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");	
	}
	//Test1
	@Test
	public void RadioBtntest() {
		WebElement radioBtn = driver.findElement(By.xpath("//input[@value='radio2']"));
		radioBtn.click();
		Assert.assertEquals("Radio button doesn't get clicked", true,radioBtn.isSelected());
	}
	//Test2
	@Test
	public void dropDownSelect() {
		WebElement Optionbtn = driver.findElement(By.id("dropdown-class-example"));
		Optionbtn.click();
		Select optionSelect = new Select(Optionbtn);
		optionSelect.selectByVisibleText("Option3");
	}
	//Test3
	@Test
	public void checkboxSelection() {
		WebElement option1Checkbox = driver.findElement(By.id("checkBoxOption1"));
		option1Checkbox.click();
		Assert.assertEquals("Option1 checkbox button doesn't get clicked", true, option1Checkbox.isSelected());
	}
	//Test4
	@Test
	public void newWindowHandle() {
		WebElement openWindowBtn = driver.findElement(By.id("openwindow"));
		openWindowBtn.click();
		System.out.println("Current window title is : " + driver.getTitle());
		
		ArrayList<String> handles = new ArrayList<String> (driver.getWindowHandles());
		String parentWindowId = handles.get(0);
		String childWindowId = handles.get(1);
		driver.switchTo().window(childWindowId);
		
		System.out.println("child window title is : " + driver.getTitle());
		String ExpUrlofChildWindow = "http://www.qaclickacademy.com/";
		String ActUrlOfChildWindow = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(parentWindowId);
		
		Assert.assertEquals("Child window Url doesn't match actual URL", ExpUrlofChildWindow, ActUrlOfChildWindow);
	}
	//Test5
	@Test
	public void alertHandle() throws InterruptedException {
		WebElement EnterNameTextBox = driver.findElement(By.id("name"));
		EnterNameTextBox.sendKeys("Hi");
		WebElement AlertButton = driver.findElement(By.id("alertbtn"));
		AlertButton.click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		System.out.println("Text of alert is: " + alert.getText());
		Assert.assertEquals("Alert text is not matching","Hello Hi, share this practice page and share your knowledge", alert.getText());
		alert.accept();
	}
	
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
}
