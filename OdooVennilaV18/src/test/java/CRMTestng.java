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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CRMTestng {

	    private WebDriver driver;

	// Use @BeforeClass to set up the browser once before all tests
	@BeforeClass
	public void setUp() throws InterruptedException {
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--disable-notifications");
	    driver = new ChromeDriver(options);
	    driver.manage().window().maximize();
	    driver.get("https://ppts3.odoo.com/odoo");
	    System.out.println("Odoo v18 Opened");

	   // Sign up process
	 //   WebElement login = driver.findElement(By.xpath("//*[@id=\"o_main_nav\"]/ul[2]/li[7]/a"));
	  //  login.click();
	 //   Thread.sleep(2000);
	    // Inputting credentials
	    WebElement username = driver.findElement(By.xpath("//*[@id='login']"));
	    username.sendKeys("subhakrish444@gmail.com");
	    WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
	    password.sendKeys("S@bh@123");
	    WebElement loginbutton = driver.findElement(By.xpath("//*[@id='wrapwrap']/main/div/div/div/form/div[3]/button"));
	    loginbutton.click();  
	    
	    // Wait until the user profile element is visible
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//span[contains(text(),'PPTS')]")));
	    
	    // Assert the user profile is displayed
	    Assert.assertTrue(userProfile.isDisplayed(), "User profile is not displayed"); 
	}

	@Test
	public void testCRMModuleAndCreateLead() throws InterruptedException {
	    // CRM Module login
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	    WebElement crmmodule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[./div[text()='CRM']]"))); 
	    crmmodule.click(); 
	    
	    System.out.println("xxx");
	    
	    // Assert that the CRM page is loaded
	    WebElement crmHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='o_menu_brand d-flex ms-3 pe-0' and text()='CRM']")));
	    Assert.assertTrue(crmHeader.isDisplayed(), "CRM module page is not loaded properly");
	   
	    
	    System.out.println("zzz");
	    Thread.sleep(2000);
	    // New Lead
	    WebElement newlead = driver.findElement(By.xpath("//button[@class='btn btn-primary o-kanban-button-new']"));
	    Assert.assertTrue(newlead.isDisplayed(), "'New Lead' button is not displayed");
	    newlead.click();
	    System.out.println("yyy");
	    Thread.sleep(1000);
	    // Select Contact
	    WebElement selecting_contact = driver.findElement(By.xpath("//*[@id='partner_id_0']"));
	    selecting_contact.click();
	    Thread.sleep(1000);
	    
	    // Wait for the dropdown menu to be visible
	    WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
	    waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='menu']")));
	    
	    WebElement searchMore = driver.findElement(By.xpath("//a[text()='Search More...']"));
	    searchMore.click();
	    Thread.sleep(1000);
	    
	    List<WebElement> contactRows = driver.findElements(By.xpath("//tr[@class='o_data_row']"));
	    Assert.assertTrue(contactRows.size() > 0, "No contacts found in the CRM.");
	    Thread.sleep(1000);
	    
	    // Loop through each row to interact with the contact
	    boolean contactSelected = false;
	         for (WebElement row : contactRows) {
	            // Extract the data-id attribute for each row (contact)
	            String dataId = row.getAttribute("data-id");
	            System.out.println("Found contact with data-id: " + dataId); 
	            Thread.sleep(1000);
	        
	            if (dataId.contains("datapoint_"))  //or give any of the datapoint id here
	            {
	                // Interact with the row or any cell in that row
	                WebElement contactNameCell = row.findElement(By.xpath(".//td[@name='complete_name']"));
	                System.out.println("Contact Name: " + contactNameCell.getText());
	                Thread.sleep(1000);
	                // You can click on the row or perform other actions as needed
	                contactNameCell.click(); // Example: Click on the contact's name
	                contactSelected = true;
	                break;  // Stop once you select a contact, or remove this if you want to select multiple contacts
	            }
	        }
	           
	    
	    // Assert that a contact was selected
	    Assert.assertTrue(contactSelected, "No contact was selected from the list.");
	    
	    // Add Lead
	    WebElement add = driver.findElement(By.xpath("//div[contains(@class, 'd-flex')]/button[contains(@class, 'o_kanban_add')]"));
	    Assert.assertTrue(add.isDisplayed(), "'Add' button is not displayed after selecting contact");
	    add.click();

	    // Optionally, verify that the new lead form or page appears (based on your app behavior)
	    WebElement leadForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article//span[text()=\"Deco Addict's opportunity\"]")));
	    Assert.assertTrue(leadForm.isDisplayed(), "Lead creation form was not displayed.");
	}

	//	 @AfterClass
	// public void tearDown() {
	//	       if (driver != null) {
	//	       driver.quit();
	//	        }
		//    }
}
