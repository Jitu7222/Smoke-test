import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Testing {

    private WebDriver driver;

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
    public void testExploreLink() {
        driver.get("https://www.ebay.com");

        // Wait for the "Explore (New!)" link to be visible and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Explore (New!)']")));

        // Click on the link
        exploreLink.click();

        // Optional: Add assertion to verify if the correct page loaded
        // Example: Assert the current URL or any other condition
        Assert.assertTrue(driver.getCurrentUrl().contains("explore"));
    }

    // Cleanup method to close the browser after the test
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
