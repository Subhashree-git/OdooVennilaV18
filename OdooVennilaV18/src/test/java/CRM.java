import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;


public class CRM {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		  WebDriver driver;  // Declare the driver once
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
	        driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
	        driver.get("https://ppts3.odoo.com/odoo");
	     // Print a message
	        System.out.println("Odoo Runbot v18 Opened");
	    
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      //signin
   //   WebElement login = driver.findElement(By.xpath("//*[@id=\"o_main_nav\"]/ul[2]/li[7]/a"));
    //  login.click();
    //  Thread.sleep(2000);
      //inputing credentials
      WebElement username = driver.findElement(By.xpath("//*[@id=\"login\"]"));
      username.sendKeys("subhakrish444@gmail.com");
      WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
      password.sendKeys("S@bh@123");
      WebElement loginbutton = driver.findElement(By.xpath("//*[@id=\"wrapwrap\"]/main/div/div/div/form/div[3]/button"));
      loginbutton.click();
      
      //Contact creation
      //CONTACT MODULE
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	    WebElement contactmodule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[./div[text()='Contacts']]"))); 
	    contactmodule.click(); 
	   // Thread.sleep(1000);
	    
      WebElement newContactButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"btn btn-primary o-kanban-button-new\"]")));
      newContactButton.click();
	    //Thread.sleep(1000);

      //creating individual contact
      WebElement ContactType = driver.findElement(By.xpath("//div[@role='radiogroup']//input[@data-index='0']")); //index value is 0 for individual & 1 for company
      if (!ContactType.isSelected()) {
    	  ContactType.click();
      }
      
      Thread.sleep(2000);
     
      //required fields in quotation
	  // Find all input fields inside divs with class 'o_required_modifier' (which indicates mandatory fields)
	     WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement mandatoryFields = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'o_required_modifier')]//input")));
	     List<WebElement> requiredFields = driver.findElements(By.xpath("//div[contains(@class, 'o_required_modifier')]//input"));
	               
	     System.out.println("Total required fields: " + requiredFields.size());
	   	 Thread.sleep(5000);
	   	 
	     for (WebElement field : requiredFields) {
	    	 
	    	 String fieldName = field.getAttribute("id");
	         String fieldValue = field.getAttribute("value");
	         

	         // Check if the field is filled or not
	         if (fieldValue == null || fieldValue.trim().isEmpty()) {
	             // If the field is empty, print the field name and send keys
	             System.out.println("Field " + fieldName + " is empty. Filling it with test data.");
	             
	             // Send keys to the field (you can modify this to send specific values)
	             field.sendKeys("Test Contact1");
	         } else {
	             // If the field is filled, print the field name and its current value
	             System.out.println("Field " + fieldName + " is already filled with value: " + fieldValue);
	         }                      
	     
         //fill the form fields if empty

	    	 
	   	  // Sample test data to be filled in the form fields if they are empty
		     String CompanyName = "Test Company";
		     String StreetName1 = "123 Sample St.";
		     String StreetName2 = "Apt 101";
		     String City = "Sample City";
		     String Pincode = "123456";
		     String Country = "Sample Country";
		     String PanNumber = "ABCDE1234F";
		     String JobPosition = "Software Engineer";
		     String Phone = "1234567890";
		     String Mobile = "9876543210";
		     String Email = "testcompany@example.com";
	    	 
	    	 Thread.sleep(1000);
	    	 
	         // Check and fill Company Name
	         WebElement companyNameField = driver.findElement(By.xpath("//*[@id='parent_id_0']"));
	         if (companyNameField.getText().isEmpty()) {
	             companyNameField.sendKeys(CompanyName);
	         }
	         Thread.sleep(1000);

	         // Check and fill Street Name 1
	         WebElement street1Field = driver.findElement(By.xpath("//*[@id='street_0']"));
	         if (street1Field.getText().isEmpty()) {
	             street1Field.sendKeys(StreetName1);
	         }
	         Thread.sleep(1000);

	         // Check and fill Street Name 2
	         WebElement street2Field = driver.findElement(By.xpath("//*[@id='street2_0']"));
	         if (street2Field.getText().isEmpty()) {
	             street2Field.sendKeys(StreetName2);
	         }
	         Thread.sleep(1000);

	         // Check and fill City
	         WebElement cityField = driver.findElement(By.xpath("//*[@id='city_0']"));
	         if (cityField.getText().isEmpty()) {
	             cityField.sendKeys(City);
	         }
	         Thread.sleep(1000);

	         // Check and fill Pincode
	         WebElement pincodeField = driver.findElement(By.xpath("//*[@id='zip_0']"));
	         if (pincodeField.getText().isEmpty()) {
	             pincodeField.sendKeys(Pincode);
	         }
	         Thread.sleep(1000);
	         
	         findAndSelectRecord(driver, 100); 

	         // Check and fill State
	/*         WebElement stateDropdown = driver.findElement(By.id("state_id_0"));
	         stateDropdown.click(); // This opens the dropdown
	         
	         WebElement searchmore = driver.findElement(By.xpath("//a[text()='Search More...']"));
	         searchmore.click();
	         Thread.sleep(1000);

	         // Step 2: Wait for the dropdown options to load (if necessary)
	         WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
	         wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='o_data_row']"))); // Assuming the options are under a listbox

	         // Step 3: Locate all the options in the dropdown (you can adjust the XPath based on how the options are structured)
	         List<WebElement> option1 = driver.findElements(By.xpath("//tr[@class='o_data_row']")); 

	         // Step 4: Loop through the options and select the one containing "Tamil Nadu"
	         for (WebElement option : option1) {
	             if (option.getText().contains("Beja")) {
	                 option.click(); // Select the option
	                 break; // Exit the loop once the option is selected
	             }
	         }

	         // Optional: Verify that the correct option is selected
	         WebElement selectedOption = driver.findElement(By.id("state_id_0"));
	         String selectedState = selectedOption.getAttribute("value");
	         System.out.println("Selected State: " + selectedState);
*/
	         // Check and fill Country
	         WebElement countryField = driver.findElement(By.xpath("//*[@id='country_id_0']"));
	         if (countryField.getText().isEmpty()) {
	             countryField.sendKeys(Country);
	         }

	         // Check and fill PAN Number
	         WebElement panNumberField = driver.findElement(By.xpath("//*[@id='l10n_in_pan_0']"));
	         if (panNumberField.getText().isEmpty()) {
	             panNumberField.sendKeys(PanNumber);
	         }

	         // Check and fill Job Position
	         WebElement jobPositionField = driver.findElement(By.xpath("//*[@id='function_0']"));
	         if (jobPositionField.getText().isEmpty()) {
	             jobPositionField.sendKeys(JobPosition);
	         }

	         // Check and fill Phone
	         WebElement phoneField = driver.findElement(By.xpath("//*[@id='phone_0']"));
	         if (phoneField.getText().isEmpty()) {
	             phoneField.sendKeys(Phone);
	         }

	         // Check and fill Mobile
	         WebElement mobileField = driver.findElement(By.xpath("//*[@id='mobile_0']"));
	         if (mobileField.getText().isEmpty()) {
	             mobileField.sendKeys(Mobile);
	         }

	         // Check and fill Email
	         WebElement emailField = driver.findElement(By.xpath("//*[@id='email_0']"));
	         if (emailField.getText().isEmpty()) {
	             emailField.sendKeys(Email);
	         }
	     }
	        
     /* 
      
      
      //crm module
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
      WebElement crmmodule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[./div[text()='CRM']]"))); 
      crmmodule.click(); 
	    Thread.sleep(1000);
      //new lead
      WebElement newlead = driver.findElement(By.xpath("//button[@class='btn btn-primary o-kanban-button-new']"));
      newlead.click();
    
   // Find and click the dropdown to open the menu
      WebElement selecting_contact = driver.findElement(By.xpath("//*[@id='partner_id_0']"));
      selecting_contact.click();

      // Wait for the dropdown menu to be visible (you can adjust the wait time as necessary)
      WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
      waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='menu']")));
      
      List<WebElement> contactRows = driver.findElements(By.xpath("//tr[@class='o_data_row']"));
      Thread.sleep(1000);
      // Loop through each row to interact with the contact
      for (WebElement row : contactRows) {
          // Extract the data-id attribute for each row (contact)
          String dataId = row.getAttribute("data-id");
          System.out.println("Found contact with data-id: " + dataId); 
          Thread.sleep(1000);
          // Example: Check if the data-id matches a certain pattern (datapoint_)
          if (dataId.contains("datapoint_"))  //or give any of the datapoint id here
          {
              // Interact with the row or any cell in that row
              WebElement contactNameCell = row.findElement(By.xpath(".//td[@name='complete_name']"));
              System.out.println("Contact Name: " + contactNameCell.getText());
              Thread.sleep(1000);
              // You can click on the row or perform other actions as needed
              contactNameCell.click(); // Example: Click on the contact's name
              break;  // Stop once you select a contact, or remove this if you want to select multiple contacts
          }
      }                                              
      
      
   // Find the row containing a specific text (e.g., "Gemini Furniture, Edwin Hansen")
      String searchText = "Test Contact";  // Text you're searching for in the row

      // Find the row containing the desired text in any of the td elements
      WebElement row = driver.findElement(By.xpath("//tr[td[contains(text(), \"Test Contact\")]]"));

      // Now, print the data-id or any other attribute for the row
     // String dataId = row.getAttribute("data-id");
     // System.out.println("Found contact with data-id: " + dataId);
      
      row.click();
      
      
      Thread.sleep(1000);
      
     WebElement add = driver.findElement(By.xpath("//div[contains(@class, 'd-flex')]/button[contains(@class, 'o_kanban_add')]"));
     add.click();
      
     WebElement leadForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article//span[text()=\"Joel Willis's opportunity\"]")));
     leadForm.click();

     // quotation creation in lead
     WebElement newQuotation = driver.findElement(By.xpath("//button[span/text()='New Quotation']"));
     newQuotation.click();
     
     //required fields in quotation
  // Find all input fields inside divs with class 'o_required_modifier' (which indicates mandatory fields)
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

   */
      
	}


// Helper method to scroll and find the 500th record (or any target)
public static void findAndSelectRecord(WebDriver driver, int targetRecordIndex) throws InterruptedException {
    int recordsPerPage = 70; // Adjust based on the page's records per page
    int targetIndexOnPage = (targetRecordIndex - 1) % recordsPerPage; // Target index on the page

    int currentPage = 1;
    boolean recordFound = false;

    // Loop until the target record is found
    while (!recordFound) {
      

        // Wait for new records to load after scrolling
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@class='o_data_row']")));

        // Locate all rows of records currently loaded on the page
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='o_data_row']"));

        // Check if the target record is visible
        if (rows.size() > targetIndexOnPage) {
            WebElement targetRow = rows.get(targetIndexOnPage);
            // Interact with the record (e.g., click the name cell)
            WebElement recordNameCell = targetRow.findElement(By.xpath(".//td[@name='name']"));
            recordNameCell.click(); // Perform desired action (e.g., click)
            recordFound = true;
        }

        // If we haven't found the record, scroll to the next page (or loop)
        currentPage++;
        if (currentPage > 100) {  // Limit search to a reasonable number of pages
            System.out.println("Reached maximum number of pages.");
            break;
        }
    }

    if (!recordFound) {
        System.out.println("Record not found.");
    }
}

}



