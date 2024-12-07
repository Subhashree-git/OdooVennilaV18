import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuotationFromLead {
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--disable-notifications");
	    driver = new ChromeDriver(options);
	    driver.manage().window().maximize();
	    driver.get("https://ppts3.odoo.com/odoo");
	    System.out.println("Odoo v18 Opened");

	   // Sign up process
	//    WebElement login = driver.findElement(By.xpath("//*[@id=\"o_main_nav\"]/ul[2]/li[7]/a"));
	//    login.click();
	//    Thread.sleep(2000);
	    // Inputting credentials
	    WebElement username = driver.findElement(By.xpath("//*[@id=\"login\"]"));
	    username.sendKeys("subhakrish444@gmail.com");
	    WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
	    password.sendKeys("S@bh@123");
	    WebElement loginbutton = driver.findElement(By.xpath("//*[@id='wrapwrap']/main/div/div/div/form/div[3]/button"));
	    loginbutton.click();  
	    
	    // Wait until the user profile element is visible
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//span[contains(text(),'PPTS')]")));
	    
	    // Assert the user profile is displayed
	    Assert.assertTrue(userProfile.isDisplayed(), "User profile is not displayed"); 
	    
	    // CRM Module login
	   
	    WebElement crmmodule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[./div[text()='CRM']]"))); 
	    crmmodule.click(); 
	  
	}
	@Test
	public void quotationCreation() throws InterruptedException {
		
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement leadForm = waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article//span[text()=\"Deco Addict's opportunity\"]")));
		 Assert.assertTrue(leadForm.isDisplayed(), "Lead creation form was not displayed.");
		 leadForm.click();
		
		 
		 // quotation creation in lead
	     WebElement newQuotation = waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[span/text()='New Quotation']")));
	     newQuotation.click();
	     
	     //required fields in quotation
	  // Find all input fields inside divs with class 'o_required_modifier' (which indicates mandatory fields)
	     WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement mandatoryFields = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'o_required_modifier')]//input")));
	     List<WebElement> requiredFields = driver.findElements(By.xpath("//div[contains(@class, 'o_required_modifier')]//input"));
	               
	     System.out.println("Total required fields: " + requiredFields.size());

	     for (WebElement field : requiredFields) {
	         String fieldName = field.getAttribute("id");
	         String fieldValue = field.getAttribute("value");

	         // Check if the field is filled or not
	         if (fieldValue == null || fieldValue.trim().isEmpty()) {
	             // If the field is empty, print the field name and send keys
	             System.out.println("Field " + fieldName + " is empty. Filling it with test data.");
	             
	             // Send keys to the field (you can modify this to send specific values)
	             field.sendKeys("Test Data for " + fieldName);
	         } else {
	             // If the field is filled, print the field name and its current value
	             System.out.println("Field " + fieldName + " is already filled with value: " + fieldValue);
	         }
	     }
		 //selecting products in quotation
	     
	     WebElement productLineItem = driver.findElement(By.xpath("//a[text()='Add a product']"));
	     productLineItem.click();
	     Thread.sleep(1000);
	     WebElement productList = driver.findElement(By.xpath("//input[@class='o-autocomplete--input o_input pe-3' and @placeholder='Search a product']"));
	     productList.click();
	     Thread.sleep(1000);
	     WebElement searchMoreProducts = driver.findElement(By.xpath("//*[@id=\"autocomplete_0_8\"and text()='Search More...']"));
	     searchMoreProducts.click();
	     Thread.sleep(1000);
	     
	     List<WebElement> productNames = driver.findElements(By.xpath("//tr[@class='o_data_row o_row_draggable']//td[@name='name']"));

	  // Ensure that there are products available
	  if (!productNames.isEmpty()) {
	      // Generate a random index to select a random product
	      Random rand = new Random();
	      int randomIndex = rand.nextInt(productNames.size());
	      
	      // Get the WebElement of the randomly selected product
	      WebElement randomProduct = productNames.get(randomIndex);
	      
	      // Print the selected product's name
	      System.out.println("Randomly selected product: " + randomProduct.getText());
	      
	      // Click on the selected product
	      randomProduct.click();
	  } else {
	      System.out.println("No products found.");
	  }
	     
	     
		 
		 
		 
		 
	}
	
	
	

}
