import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Testing {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run Chrome in headless mode
        options.addArguments("--no-sandbox");  // Required for CI environments
        options.addArguments("--disable-dev-shm-usage");  // Avoid issues with shared memory
        options.addArguments("--disable-gpu");  // Disable GPU acceleration for headless mode
        options.addArguments("--disable-software-rasterizer");

        // Set system property for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver
        driver = new ChromeDriver(options);
    }

    @Test
    public void testGooglePage() {
        try {
            // Navigate to Google
            driver.get("https://www.google.com");

            // Verify the page title
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);

            // Assert that the title contains "Google"
            Assert.assertTrue(pageTitle.contains("Google"), "Google homepage did not load successfully.");
            System.out.println("Google homepage loaded successfully.");
        } catch (Exception e) {
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
