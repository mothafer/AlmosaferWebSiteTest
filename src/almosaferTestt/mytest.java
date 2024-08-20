package almosaferTestt;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class mytest {
	WebDriver driver = new ChromeDriver();
	String mywebsite  = "https://www.almosafer.com/en?ncr=1";
	String ExpectedDefaultLanage = "en";
	Random rand = new Random();
	
	@BeforeTest
	public void Mysetup() {
		driver.get(mywebsite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
			
	}
	
	
	@Test(priority = 1)
	public void DefaultLange() {
		String ActualangugeIsEnglish = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualangugeIsEnglish, ExpectedDefaultLanage);
		
	}
	
	@Test(priority = 2)
	public void CheckdefaultCurrency() {
		String ExpectedCurrency = "SAR";
		String ActualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
		
	}
	
	@Test(priority = 3)
	public void CheckContactNumber() {
		String ExpectedNumber = "+966554400000";
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		Assert.assertEquals(ActualNumber, ExpectedNumber);
		
		
	}
	
	
	@Test(priority = 4)
	public void CheckQitaflogo() {
		boolean ExpectedLogo = true;
		WebElement footers = driver.findElement(By.tagName("footer"));
		WebElement logo = footers.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		boolean ActualLogo = logo.isDisplayed();
		System.out.println(ActualLogo);
		Assert.assertEquals(ActualLogo, ExpectedLogo);
		
		
		
	}
	
	
	@Test(priority = 5)
	public void CheckHotelIsNotSelected() {
		String ActualSelected = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		String ExpectedSelected = "false";
		Assert.assertEquals(ActualSelected, ExpectedSelected);
		
		
	}
	
	@Test(priority = 6)
	public void CheckDepatureDate() {
		LocalDate todaydate = LocalDate.now();
		int Today = todaydate.getDayOfMonth();
		int Tomorrow = todaydate.plusDays(1).getDayOfMonth();
		int AfterTomorrow = todaydate.plusDays(2).getDayOfMonth();
		
		List<WebElement> DepatureAndArrivalDate =driver.findElements(By.className("LiroG"));
		String ActualDepatureDate = DepatureAndArrivalDate.get(0).getText();
		String ActualArrivalDate = DepatureAndArrivalDate.get(1).getText();
		
		int ActualDepatureDateInt = Integer.parseInt(ActualDepatureDate);
		int ActualArrivalDateInt = Integer.parseInt(ActualArrivalDate);
		
		Assert.assertEquals(ActualDepatureDateInt, Tomorrow);
		Assert.assertEquals(ActualArrivalDateInt, AfterTomorrow);
		
		
		
	}
	@Test(priority = 7)
	public void Randomlang() {
		String[] URLS = {"https://www.almosafer.com/en?ncr=1 , https://www.almosafer.com/ar?ncr=1,"};
		int RandomIndex = rand.nextInt(URLS.length);
		driver.get(URLS[RandomIndex]);
		
		
		
	}
}
