package TheFork.TheForkChallenge;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.base;

public class HomePage extends base{
	
	
	@BeforeTest
	public void homePageNavigation() throws IOException
	{
		//initialize data
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/files/data.properties");
		
		prop.load(fis);
		String url = prop.getProperty("url");
				
		//initialize driver
		driver=initializeDriver();
		
		driver.get(url); //Go to the TheFork Home Page
		driver.manage().window().maximize(); // Maximize the Window
	}
	
	@Test(priority=1)
	public void connexionStep() throws InterruptedException
	{
		//initialize data email & password
		String userEmail = prop.getProperty("userEmail");
		String password = prop.getProperty("password");
		
		//Click  accept cookies
		driver.findElement(By.xpath("//button[@id=\"_evidon-accept-button\" and @class=\"evidon-banner-acceptbutton\" and text()=\"Accepter\"]")).click();
		//Click Connexion Botton
		driver.findElement(By.xpath("//span[text()=\"Connexion\"]")).click();
		
		//Enter the email
		driver.findElement(By.id("identification_email")).sendKeys(userEmail);
		//click on CONTINUER
		driver.findElement(By.xpath("//span[text()=\"Continuer\"]")).click();
		//Enter Password
		driver.findElement(By.id("password")).sendKeys(password);
		//click on CONTINUER
		driver.findElement(By.xpath("//span[text()=\"Continuer\"]")).click();

	}
	
	@Test(priority=2)
	public void verificationOfPersonalInformations()
	{
		//click on Personal Informations
		driver.findElement(By.xpath("//button[@aria-controls=\"user-space-user-information\"]")).click();
		//Verification gender = M
		Assert.assertEquals(true,driver.findElement(By.cssSelector("input[value=\"M\"]")).isSelected());
		//Verification name = Mehdi
		Assert.assertEquals("Mehdi",driver.findElement(By.name("firstName")).getText());
		//Verification lastName = IBEN KACEM
		Assert.assertEquals("IBEN KACEM", driver.findElement(By.name("lastName")).getText());
		//Verification Phone Number
		Assert.assertEquals("755703437", driver.findElement(By.name("phoneNumber.nationalNumber")).getText());
		//Verification Birthdate : day = 8
		Assert.assertEquals("8", driver.findElement(By.name("birthDate.day")).getText());
		//Verification Birthdate : month = avril
		Assert.assertEquals("avril", driver.findElement(By.name("birthDate.month")).getText());
		//Verification Birthdate : Year = 1984
		Assert.assertEquals("1984", driver.findElement(By.name("birthDate.year")).getText());
		//Verification Email 
		Assert.assertEquals("mehdibenkacem@gmail.com", driver.findElement(By.name("email")).getText());
	}
	
	@AfterTest
	public void closeDriver()
	{
		driver.quit();	
	}

}
