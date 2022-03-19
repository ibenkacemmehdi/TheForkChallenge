package files;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class base {
	
	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException
	{
		//initialize data
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/files/data.properties");
		
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		System.out.println("Runing Tests on Browser : "+browserName);
		
		if(browserName.equals("chrome"))
		{
			//execute in chrome driver
			System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chrome\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			//execute in firefox driver
			
		}
		else if(browserName.equals("IE"))
		{
			//execute in IE driver
			
		}
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
}
