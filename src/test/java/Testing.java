import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Scanner;
import java.util.Set;

public class Testing {
    private WebDriver driver;
    private WebDriverWait wait;
    private String originalWindow;

    @BeforeClass
    public void setUp() {
         WebDriverManager.chromedriver().setup();
        // Specify the path to your Chrome user profile
        String chromeProfilePath = "/Users/singh.jitendra/Library/Application Support/Google/Chrome"; // macOS

        // Create ChromeOptions to use the specified profile
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + chromeProfilePath); // Set the user data directory
        options.addArguments("profile-directory=Profile 2"); // Specify the profile (e.g., Default, Profile 1)

        // Initialize the WebDriver with the specified options (using the custom profile)
        WebDriver driver = new ChromeDriver(options);

        // Initialize WebDriverWait for waiting elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maximize the window
        driver.manage().window().maximize();

        // Open a website (e.g., Google)
        driver.get("https://www.google.com");
    }

    @Test
    public void testNavigateToLoginPage() throws InterruptedException {
        // Navigate to the login page
        driver.get("https://app.perceptinsight.com");
        Thread.sleep(2000);

        // Wait until the "Sign in with Google" button is clickable
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Sign in with Google')]")));
        signInButton.click();
    }



    @Test(dependsOnMethods = "testNavigateToLoginPage", alwaysRun = true)
    public void testEmailInput() throws InterruptedException {
        // Store the original window handle
        originalWindow = driver.getWindowHandle();
        System.out.println("Original window handle: " + originalWindow);

        // Wait for the new window to open and switch to it
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));  // Wait until 2 windows are open
        Set<String> allWindows = driver.getWindowHandles();

        // Log the window handles for debugging
        System.out.println("All open windows: " + allWindows);

        // Switch to the new window
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                System.out.println("Switching to new window: " + window);
                driver.switchTo().window(window);
                break;
            }
        }

        // Wait for the email input field to be visible and interact with it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the input field using XPath and wait for its visibility and clickability
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email' and @name='identifier']")));

        // Optional: Click the input field to focus
        emailInput.click();

        // Clear the field and send keys to the email input
        emailInput.clear();
        emailInput.sendKeys("psp20932@gmail.com");
    }


    @Test(dependsOnMethods = "testEmailInput", alwaysRun = true)
    public void testNextButton() throws InterruptedException {
        // Click on "Next"
        WebElement nextButton = driver.findElement(By.xpath("//span[normalize-space()='Next']"));
        Assert.assertTrue(nextButton.isDisplayed(), "Next button is not visible.");
        nextButton.click();
        //Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testNextButton", alwaysRun = true)
    public void testPasswordInput() throws InterruptedException {
        // Wait for the password field and enter the password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwd")));
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not visible.");
        passwordField.clear();
        passwordField.sendKeys("Dutchman@2020");
        //Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testPasswordInput", alwaysRun = true)
    public void testSignInButton() throws InterruptedException {
        // Click on "Sign in"
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is not visible.");
        signInButton.click();
        Thread.sleep(2000); // Sleep for 2 seconds
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
