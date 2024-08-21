package almosaferTestt;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
			
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
	
	@Test(priority = 8)
	public void FillHotelTap () {
		
		String[] Englishcity = {"Dubbai","jeddah","riyadh"};
		int randomENcity = rand.nextInt(Englishcity.length);
		
		String[] Arabiccity = {"دبي","جده"};
		int randomARcity = rand.nextInt(Arabiccity.length);

		
		
		
		WebElement hotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTap.click();
		
		WebElement searchHotel = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));
		
		String webSiteURLs = driver.getCurrentUrl();
		
		if(webSiteURLs.contains("ar")) {
			
			searchHotel.sendKeys(Englishcity[randomENcity]);
		}else {
			searchHotel.sendKeys(Arabiccity[randomARcity]);
		}
		WebElement ListtofLocation = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		WebElement firstResult = ListtofLocation.findElements(By.tagName("li")).get(2);
		firstResult.click();
	}
	
	@Test(priority = 9)
	public void RandomlyselectTheNumberOfvistor() {
		WebElement selectoro = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select select = new Select(selectoro);
		 int randomindx = rand.nextInt(2);
		 select.selectByIndex(randomindx);
		 
		 WebElement buttonsearch = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		 buttonsearch.click();
		
		
		
	}
	
	@Test(priority = 10)
	public void CheckThePageloaded() throws InterruptedException {
		Thread.sleep(10000);
		String result = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
	boolean actual	= result.contains("found")||result.contains("وجدنا ");
	boolean expected = true;
	Assert.assertEquals(actual, expected);
		
		
	}
	
	@Test(priority = 11)
	public void SortItem() {
		WebElement LowestpriceButton= driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestpriceButton.click();
		
		WebElement pricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> allprices = pricesContainer.findElements(By.className("Price__Value"));
		
		String lowestprices = allprices.get(0).getText();
		String Highestprices = allprices.get(allprices.size()-1).getText();
		
		
		int lowestpricesINT = Integer.parseInt(lowestprices);
		int HighestpricesINT =Integer.parseInt(Highestprices);
		
		boolean actual = lowestpricesINT < HighestpricesINT;
		 boolean expected =true;
		 
		 Assert.assertEquals(actual, expected);
		 
		
		
		
	}
		
	}

