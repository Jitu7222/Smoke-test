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
            // Navigate to the website
            driver.get("https://app.perceptinsight.com");

            // Verify the page title
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);

            // Find the "Sign in with Microsoft" element
            WebElement signInWithMicrosoft = driver.findElement(By.xpath("//p[text()='Sign in with Microsoft']"));

            // Check if the element is null before interacting with it
            if (signInWithMicrosoft != null) {
                Assert.assertTrue(signInWithMicrosoft.isDisplayed(), "Sign in with Microsoft button is not visible.");
                signInWithMicrosoft.click();
            } else {
                System.out.println("Element not found: Sign in with Microsoft");
                Assert.fail("Sign in with Microsoft button was not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
