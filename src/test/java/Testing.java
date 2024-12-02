import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Testing {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Use WebDriverManager to automatically download and set up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run Chrome in headless mode
        options.addArguments("--no-sandbox");  // Required for CI environments
        options.addArguments("--disable-dev-shm-usage");  // Avoid issues with shared memory
        options.addArguments("--disable-gpu");  // Disable GPU acceleration for headless mode
        options.addArguments("--disable-software-rasterizer");

        // Initialize WebDriver with the options
        driver = new ChromeDriver(options);

    }

    @Test
    public void testGooglePage() {
        try {
            // Navigate to the page
            driver.get("https://app.perceptinsight.com");

            // Verify the page title
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);
            Assert.assertTrue(pageTitle.contains("Percept Insight"), "Page title is incorrect.");

            // Wait until the "Sign in with Microsoft" button is clickable
            WebElement signInWithMicrosoft = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Sign in with Microsoft']")));

            // Assert that the button is displayed
            Assert.assertTrue(signInWithMicrosoft.isDisplayed(), "Sign in with Microsoft button is not visible.");

            // Click the "Sign in with Microsoft" button
            signInWithMicrosoft.click();
            System.out.println("Clicked on the 'Sign in with Microsoft' button.");

        } catch (Exception e) {
            // Catching exception and printing stack trace
            System.err.println("Exception occurred during test execution: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Quit the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
