import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.asynchttpclient.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Set;

public class Testing {
    private WebDriver driver;
    private WebDriverWait wait;
    private String originalWindow;

    @BeforeClass
    public void setUp() {
            // Set up ChromeDriver using WebDriverManager
            WebDriverManager.chromedriver().setup();

            // Set Chrome options to run in headless mode
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");            // Enable headless mode
            options.addArguments("--disable-gpu");        // Disable GPU hardware acceleration (optional)
            options.addArguments("--window-size=1920x1080"); // Set the window size (optional but recommended for headless)

            // Initialize the ChromeDriver with the options
            driver = new ChromeDriver(options);

            // Initialize WebDriverWait
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Maximize window (optional, as headless mode ignores this)
            driver.manage().window().maximize(); // This line is redundant in headless mode, but can be kept for non-headless fallback

            // Perform any other setup steps needed
        }
    @Test
    public void testNavigateToLoginPage() throws InterruptedException {
        // Navigate to the login page
        driver.get("https://app.perceptinsight.com");
        Thread.sleep(2000); // Sleep for 2 seconds

        // Click on "Sign in with Microsoft"
        WebElement signInWithMicrosoft = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Sign in with Microsoft']")));
        Assert.assertTrue(signInWithMicrosoft.isDisplayed(), "Sign in with Microsoft button is not visible.");
        signInWithMicrosoft.click();
        //Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testNavigateToLoginPage",alwaysRun = true)
    public void testEmailInput() throws InterruptedException {
        // Store the original window handle
        originalWindow = driver.getWindowHandle();

        // Wait for the new window to open and switch to it
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();

        // Switch to the new window
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }


        // Wait for the email input field to be visible
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email'][name='loginfmt'][id='i0116']")));
        Assert.assertTrue(emailInput.isDisplayed(), "Email input field is not visible.");
        emailInput.clear();
        emailInput.sendKeys("jitendra@perceptinsight.com");
    }

    @Test(dependsOnMethods = "testEmailInput",alwaysRun = true)
    public void testNextButton() throws InterruptedException {
        // Click on "Next"
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        Assert.assertTrue(nextButton.isDisplayed(), "Next button is not visible.");
        nextButton.click();
        //Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testNextButton",alwaysRun = true)
    public void testPasswordInput() throws InterruptedException {
        // Wait for the password field and enter the password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwd")));
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not visible.");
        passwordField.clear();
        passwordField.sendKeys("Pearl@2030");
        //Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testPasswordInput",alwaysRun = true)
    public void testSignInButton() throws InterruptedException {
        // Click on "Sign in"
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is not visible.");
        signInButton.click();
        Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testSignInButton",alwaysRun = true)
    public void testSwitchBackToOriginalWindow() throws InterruptedException {
        // Wait for the login process to complete
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))); // Adjust this based on the next expected element after login
        signInButton.click();
        Thread.sleep(2000); // Sleep for 2 seconds

        // Switch back to the original window
        driver.switchTo().window(originalWindow);
        Thread.sleep(2000); // Sleep for 2 seconds
    }



    @Test(dependsOnMethods = "testSignInButton",alwaysRun = true)
    public void testAllReports() throws InterruptedException {
        // All reports
        WebElement pElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
        Assert.assertTrue(pElement.isDisplayed(), "All Reports element is not visible.");
        pElement.click();
        Thread.sleep(2000); // Sleep for 2 seconds
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Ensure driver is closed in the end
        }
    }
}
