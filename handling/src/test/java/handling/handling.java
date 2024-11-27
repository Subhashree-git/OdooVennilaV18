package handling;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class handling {

	public static void main(String[] args) throws InterruptedException 
		{
			// TODO Auto-generated method stub
			        WebDriver driver;  // Declare the driver once
			        ChromeOptions options = new ChromeOptions();
			        options.addArguments("--disable-notifications"); // Add any desired options to Chrome

			        // Initialize ChromeDriver with the options
			        driver = new ChromeDriver(options);

			        // Open a website (for example, demoqa)
			        driver.get("https://demoqa.com/browser-windows");

			        // Print a message
			        System.out.println("Browser opened and navigated to the demoqa page.");
			        
			        driver.manage().window().maximize();
			        // Javascript executor is used to trigger the click directly, bypassing any visibility or clickability issues    
			        JavascriptExecutor js = (JavascriptExecutor) driver;
			        
			        // Open a new tab, window, and message window
			        WebElement tabButtonSearch = driver.findElement(By.id("tabButton"));
			        js.executeScript("arguments[0].click();", tabButtonSearch);
			        System.out.println("New tab opened");

			        WebElement windowButtonSearch = driver.findElement(By.id("windowButton"));
			        js.executeScript("arguments[0].click();", windowButtonSearch);
			        System.out.println("New window opened");

		/*	        WebElement messageWindow = driver.findElement(By.id("messageWindowButton"));  // This third window is not properly captured or handled during the execution. 
			        js.executeScript("arguments[0].click();", messageWindow);
			        System.out.println("New message window opened");      
	    */		        

			        // Get the handle of the main window
			        String mainWindow = driver.getWindowHandle();
			        System.out.println("Parent Window Handle: " + mainWindow);

			        driver.switchTo().window(mainWindow); // Switch back to main window
			        System.out.println("Main Window active");
			        
			       

			        // Get all window handles
			        Set<String> windows = driver.getWindowHandles();

			        // List to dynamically store child window handles
			        List<String> childWindows = new ArrayList<>();

			        // Loop through the window handles to find the child windows
			        for (String handle : windows) {
			            if (!handle.equals(mainWindow)) {
			                childWindows.add(handle);  // Add child window handles to the list
			            }
			        }

			        // Switch to each child window and print its title
			        for (int i = 0; i < childWindows.size(); i++) {
			            driver.switchTo().window(childWindows.get(i));
			            System.out.println("Switched to Child Window " + (i + 1) + ": " + driver.getTitle());
			        
			        
			      
					// Close Child Window 1 (index 0)
			        if (i == 0) {  // The first child window has index 0
			            driver.close();
			            System.out.println("Child Window 1 closed.");
			        }
			        
			        // Close Child Window 2 (index 1)
			        if (i == 1) {  // The second child window has index 1
			            driver.close();
			            System.out.println("Child Window 2 closed.");
			        }
			        
			        }
			       
			        // Optionally, switch back to the main window
			        driver.switchTo().window(mainWindow);
			        System.out.println("Switched back to Parent Window: " + driver.getTitle());

			        //Finally, close the main window (if needed)
			        driver.close();  // Uncomment if you want to close the main window as well
			        System.out.println("Main Window closed");                     
			        
			        
			   	   
			      
			        
			        
			        
			    }
		
		
		

	}


