package almosaferTestt;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	String mywebsite  = "https://www.almosafer.com/en?ncr=1";
	String ExpectedDefaultLanage = "en";
	Random rand = new Random();
	
	
	String ExpectedCurrency = "SAR";
	String ExpectedNumber = "+966554400000";
	boolean ExpectedLogo = true;
	String ExpectedSelected = "false";

	LocalDate todaydate = LocalDate.now();
	int Today = todaydate.getDayOfMonth();
	int Tomorrow = todaydate.plusDays(1).getDayOfMonth();
	int AfterTomorrow = todaydate.plusDays(2).getDayOfMonth();
	
	
	
		String[] Englishcity = {"Dubbai","jeddah","riyadh"};
		int randomENcity = rand.nextInt(Englishcity.length);
		
		String[] Arabiccity = {"دبي","جده"};
		int randomARcity = rand.nextInt(Arabiccity.length);
	
		
		
		
	

	
	
	public void GeneralSetup() {
		driver.get(mywebsite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		
		
	}
	
}
