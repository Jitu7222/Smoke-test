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

public class Insight{
    private WebDriver driver;
    private WebDriverWait wait;
    private String originalWindow;

    @BeforeClass
    public void setUp()  {
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
    public void testNavigateToLoginPage() throws InterruptedException {
        // Navigate to the login page
        driver.get("https://app.perceptinsight.com/login");
        //Thread.sleep(2000); // Sleep for 2 seconds

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

    @Test(dependsOnMethods = "testSwitchBackToOriginalWindow",alwaysRun = true)
    public void testHomePageActions() throws InterruptedException {
        // Home page actions
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'rounded-md') and contains(@class, 'cursor-pointer') and contains(@class, 'outline-none') and contains(@class, 'space-x-2') and contains(@class, 'hover:bg-foreground-secondary') and contains(@class, 'text-foreground-selected') and contains(@class, 'flex') and contains(@class, 'items-center') and contains(@class, 'bg-foreground-secondary') and contains(@class, 'p-2')]//div[contains(@class, 'flex') and contains(@class, 'w-full') and contains(@class, 'items-center') and contains(@class, 'justify-between')]")));
        Assert.assertTrue(element.isDisplayed(), "Home page element is not visible.");
        element.click();
        Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testHomePageActions",alwaysRun = true)
    public void testAllReports() throws InterruptedException {
        // All reports
        WebElement pElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
        Assert.assertTrue(pElement.isDisplayed(), "All Reports element is not visible.");
        pElement.click();
        Thread.sleep(2000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testAllReports",alwaysRun = true)
    public void testScreenView() throws InterruptedException {
        // Screen View
        WebElement siElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Screen View 1_7sept']")));
        Assert.assertTrue(siElement.isDisplayed(), "Screen View 1_7sept element is not visible.");
        siElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testScreenView",alwaysRun = true)
    public void testArrowElement() throws InterruptedException {
        // Arrow element
        WebElement ArElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']")));
        Assert.assertTrue(ArElement.isDisplayed(), "Arrow element is not visible.");
        ArElement.click();
        Thread.sleep(1000); // Sleep for 2 seconds
    }

    @Test(dependsOnMethods = "testArrowElement",alwaysRun = true)
    public void testClickAllReportsEle() throws InterruptedException {
        // Ensure "All Reports" element is visible and clickable
        WebElement allReportsElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='All Reports']")));
        Assert.assertTrue(allReportsElement.isDisplayed(), "All Reports element is not visible.");
        allReportsElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickAllReportsEle",alwaysRun = true)
    public void testClickPixelInsight() throws InterruptedException {
        // Ensure "Pixel Insight" element is visible and clickable
        WebElement pixelInsightElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Test_insight']")));
        Assert.assertTrue(pixelInsightElement.isDisplayed(), "Pixel Insight element is not visible.sds");
        pixelInsightElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickPixelInsight",alwaysRun = true)
    public void testClickArrowAndAllReports() throws InterruptedException {
        // Click on the Arrow Element
        WebElement arrowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']")));
        Assert.assertTrue(arrowElement.isDisplayed(), "Arrow element is not visible.");
        arrowElement.click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "testClickArrowAndAllReports",alwaysRun = true)
    public void testClickArrowElement() throws InterruptedException {
        // Click on "All Reports" element
        WebElement allReportsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
        Assert.assertTrue(allReportsElement.isDisplayed(), "All Reports element is not visible.");
        allReportsElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickArrowElement",alwaysRun = true)
    public void testClickFunnelStep() throws InterruptedException {
        // Click on "Funnel_10_step" element
        WebElement fsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Test_Funnel']")));
        Assert.assertTrue(fsElement.isDisplayed(), "Funnel_10_step element is not visible.");
        fsElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickFunnelStep",alwaysRun = true)
    public void Funnel_10_step() throws InterruptedException {
        // Click on the Arrow Element1
        WebElement ar1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']")));
        Assert.assertTrue(ar1Element.isDisplayed(), "Arrow element is not visible.");
        ar1Element.click();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "Funnel_10_step",alwaysRun = true)
    public void testClickArrowElem() throws InterruptedException {
        // Click on "All Reports" element
        WebElement p2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
        Assert.assertTrue(p2Element.isDisplayed(), "All Reports element is not visible.");
        p2Element.click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "testClickArrowElem",alwaysRun = true)
    public void testClick10thStepFlow() throws InterruptedException {
        // Click on "10thstep flow" element
        WebElement flElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='10thstep flow']")));
        Assert.assertTrue(flElement.isDisplayed(), "10thstep flow element is not visible.");
        flElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClick10thStepFlow",alwaysRun = true)
    public void testClickArrow2() throws InterruptedException {
        // Click on the second arrow element
        WebElement Ar2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']")));
        Assert.assertTrue(Ar2Element.isDisplayed(), "Arrow element is not visible.");
        Ar2Element.click();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "testClickArrow2",alwaysRun = true)
    public void testClickAllReportsAgain() throws InterruptedException {
        // Click on the "All Reports" element
        WebElement p3Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
        Assert.assertTrue(p3Element.isDisplayed(), "All Reports element is not visible.");
        p3Element.click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "testClickAllReportsAgain",alwaysRun = true)
    public void testClickRetentionCurve() throws InterruptedException {
        // Click on the "Retention_Curve" element
        WebElement reElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Retention_Curve']")));
        Assert.assertTrue(reElement.isDisplayed(), "Retention_Curve element is not visible.");
        reElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickRetentionCurve",alwaysRun = true)
    public void testClickArrow() throws InterruptedException {
        // Click on the arrow element
        WebElement Ar6Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']")));
        Assert.assertTrue(Ar6Element.isDisplayed(), "Arrow element is not visible.");
        Ar6Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods = "testClickArrow",alwaysRun = true)
    public void testClickAllReports() throws InterruptedException {
        // Click on "All Reports" element
        WebElement p6Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
        Assert.assertTrue(p6Element.isDisplayed(), "All Reports element is not visible.");
        p6Element.click();
        Thread.sleep(5000);
    }
    @Test(dependsOnMethods = "testClickAllReports",alwaysRun = true)
    public void testClickFunnelIcon() throws InterruptedException {
        // Click on the funnel icon
        WebElement funnelIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='w-8' and @alt='FUNNEL icon']")));
        Assert.assertTrue(funnelIcon.isDisplayed(), "Funnel icon is not visible.");
        funnelIcon.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickFunnelIcon",alwaysRun = true)
    public void testClickInsightIcon() throws InterruptedException {
        // Click on the Insight icon
        WebElement insightIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Insight']")));
        Assert.assertTrue(insightIcon.isDisplayed(), "Insight icon is not visible.");
        insightIcon.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods = "testClickInsightIcon",alwaysRun = true)
    public void testClickFlowIcon() throws InterruptedException {
        // Click on the Flow icon
        WebElement flowIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Flow']//*[name()='svg']")));
        Assert.assertTrue(flowIcon.isDisplayed(), "Flow icon is not visible.");
        flowIcon.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods = "testClickFlowIcon",alwaysRun = true)
    public void testClickRetentionIcon() throws InterruptedException {
        // Wait for the retention icon to be visible and click it
        WebElement retention = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Retention']//*[name()='svg']")));
        Assert.assertTrue(retention.isDisplayed(), "Retention icon is not visible.");
        retention.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickRetentionIcon",alwaysRun = true)
    public void testClickSvgElement() throws InterruptedException {
        // Wait for the SVG element to be visible
        WebElement svgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg.w-6.h-6[fill='none']")));
        Assert.assertTrue(svgElement.isDisplayed(), "SVG element is not visible.");
        svgElement.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods = "testClickSvgElement",alwaysRun = true)
    public void testClickInsightsHub() throws InterruptedException {
        // Wait for the Insights Hub element to be visible
        WebElement insightsHubElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Insights Hub']")));
        Assert.assertTrue(insightsHubElement.isDisplayed(), "Insights Hub element is not visible.");
        insightsHubElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickInsightsHub",alwaysRun = true)
    public void testClickDashboard() throws InterruptedException {
        // Wait for the Dashboard element to be visible
        WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Your Boards']")));
        Assert.assertTrue(dashboardElement.isDisplayed(), "Dashboard element is not visible.");
        dashboardElement.click();
        Thread.sleep(100);
    }

    @Test(dependsOnMethods = "testClickDashboard",alwaysRun = true)
    public void testClickPixelDashboard() throws InterruptedException {
        WebElement pixelDashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Pixel_Dashboard')]")));
        Assert.assertTrue(pixelDashboardElement.isDisplayed(), "Pixel Dashboard element is not visible.");
        pixelDashboardElement.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods = "testClickPixelDashboard",alwaysRun = true)
    public void testClickCreateReport() throws InterruptedException {
        WebElement vgElement= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-sm font-medium' and text()='Create Reports']")));
        Assert.assertTrue(vgElement.isDisplayed(),"Create Report element is not visible.");
        vgElement.click();
    }
    @Test(dependsOnMethods ="testClickCreateReport",alwaysRun = true)
    public void testClickInsights() throws InterruptedException {
        WebElement insightsElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-sm font-medium' and text()='Insights']")));
        Assert.assertTrue(insightsElement.isDisplayed(), "'Insights' element is not visible.");
        insightsElement.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickInsights",alwaysRun = true)
    public void testClickPath1Element() throws InterruptedException {
        WebElement path1Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
        Assert.assertTrue(path1Element.isDisplayed(), "'Path1 Element' is not visible.");
        path1Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath1Element",alwaysRun = true)
    public void testSendKeysAndPressEnter() throws InterruptedException{
        WebElement inputBox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input")));
        inputBox1.sendKeys("All Events");
        inputBox1.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'All Events')]")));
        //Assert.assertTrue(resultElement.isDisplayed(), "'All Events' result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testSendKeysAndPressEnter",alwaysRun = true)
    public void testClickPath2Element() throws InterruptedException {
        WebElement path2Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
        Assert.assertTrue(path2Element.isDisplayed(), "'Path2 Element' is not visible.");
        path2Element.click();
    }

    @Test(dependsOnMethods ="testClickPath2Element",alwaysRun = true)
    public void testSendKeysEnter1() throws InterruptedException {
        WebElement inputBox2 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
        inputBox2.sendKeys("$click");
        inputBox2.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox2.isDisplayed(), "click result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods ="testSendKeysEnter1",alwaysRun = true)
    public void testClickPath3Element() throws InterruptedException {
        WebElement path3Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
        Assert.assertTrue(path3Element.isDisplayed(), "'Path3 Element' is not visible.");
        path3Element.click();
    }

    @Test(dependsOnMethods ="testClickPath3Element",alwaysRun = true)
    public void testSendKeysEnter2() throws InterruptedException {
        WebElement inputBox3 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
        inputBox3.sendKeys("$pageView");
        inputBox3.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox3.isDisplayed(), "pageView result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods ="testSendKeysEnter2",alwaysRun = true)
    public void testClickPath4Element() throws InterruptedException {
        WebElement path4Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
        Assert.assertTrue(path4Element.isDisplayed(), "'Path4 Element' is not visible.");
        path4Element.click();
    }
    @Test(dependsOnMethods ="testClickPath4Element",alwaysRun = true)
    public void testSendKeysEnter3() throws InterruptedException {
        WebElement inputBox4 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
        inputBox4.sendKeys("$scroll");
        inputBox4.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox4.isDisplayed(), "scroll result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testSendKeysEnter3",alwaysRun = true)
    public void testClickPath5Element() throws InterruptedException {
        WebElement path5Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
        Assert.assertTrue(path5Element.isDisplayed(), "'Path5 Element' is not visible.");
        path5Element.click();
    }
    @Test(dependsOnMethods ="testClickPath5Element",alwaysRun = true)
    public void testSendKeysEnter4() throws InterruptedException {
        WebElement inputBox5 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
        inputBox5.sendKeys("$webVitals");
        inputBox5.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox5.isDisplayed(), "webVitals result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testSendKeysEnter4",alwaysRun = true)
    public void testClickPath6Element() throws InterruptedException {
        WebElement path6Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
        Assert.assertTrue(path6Element.isDisplayed(), "'Path6 Element' is not visible.");
        path6Element.click();
    }
    @Test(dependsOnMethods ="testClickPath6Element",alwaysRun = true)
    public void testSendKeysEnter5() throws InterruptedException {
        WebElement inputBox6 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
        inputBox6.sendKeys("$fetch");
        inputBox6.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox6.isDisplayed(), "fetch result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testSendKeysEnter5",alwaysRun = true)
    public void testClickPath7Element() throws InterruptedException {
        WebElement path7Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")));
        Assert.assertTrue(path7Element.isDisplayed(), "'Path7 Element' is not visible.");
        path7Element.click();
    }

    @Test(dependsOnMethods ="testClickPath7Element",alwaysRun = true)
    public void testClickPath8Element() throws InterruptedException {
        WebElement path8Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space(text())='All Properties']")));
        Assert.assertTrue(path8Element.isDisplayed(), "'Path7 Element' is not visible.");
        path8Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath8Element",alwaysRun = true)
    public void testSendKeysEnter7() throws InterruptedException {
        WebElement inputBox7 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
        inputBox7.sendKeys("pi_city");
        inputBox7.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox6.isDisplayed(), "fetch result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testSendKeysEnter7",alwaysRun = true)
    public void testSendKeysEnter8() throws InterruptedException {
        WebElement inputBox8 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input"));
        inputBox8.sendKeys("bengaluru");
        Thread.sleep(1000);
        inputBox8.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox6.isDisplayed(), "fetch result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testSendKeysEnter8",alwaysRun = true)
    public void testClickPath9Element() throws InterruptedException {
        WebElement path9Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space(text())='Breakdown']")));
        Assert.assertTrue(path9Element.isDisplayed(), "'Path9 Element' is not visible.");
        path9Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath9Element",alwaysRun = true)
    public void testSendKeysEnter9() throws InterruptedException {
        WebElement inputBox9 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
        Thread.sleep(1000);
        inputBox9.sendKeys("pi_device_manufacturer");
        Thread.sleep(1000);
        inputBox9.sendKeys(Keys.RETURN);
        //WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'click')]")));
        //Assert.assertTrue(inputBox6.isDisplayed(), "fetch result is not displayed after pressing Enter.");
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testSendKeysEnter9",alwaysRun = true)
    public void testNavigateToTargetMonth() throws InterruptedException {
        String year = "2024";
        String month = "October";

        WebElement customElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'text-xs font-semibold') and text()='Custom']")));
        customElement.click();

        boolean targetFound = false;
        while (!targetFound) {
            // Get the current month and year displayed in the calendar
            WebElement monthYearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-sm font-medium' and @aria-live='polite' and @id='react-day-picker-1']")));
            String MonthYear = monthYearElement.getText();

            // Split the current displayed month and year into separate variables
            String[] arr = MonthYear.split(" ");
            String currentMonth = arr[0];
            String currentYear = arr[1];

            // Check if the current month and year match the target month and year
            if (currentMonth.equalsIgnoreCase(month) && currentYear.equals(year)) {
                System.out.println("Found the target month and year: " + MonthYear);
                targetFound = true; // Break the loop once the target month/year is found
            } else {
                // If not, click the 'previous-month' button to go to the next month
                WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='previous-month']//*[name()='svg']")));
                nextMonthButton.click();
            }
            Thread.sleep(1000); // Adjust this if needed or use explicit waits
        }

        // Assert that the target month/year was found
        WebElement monthYearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='text-sm font-medium' and @aria-live='polite' and @id='react-day-picker-1']")));
        String displayedMonthYear = monthYearElement.getText();
        Assert.assertTrue(displayedMonthYear.contains(year), "Year does not match the expected year.");
        Assert.assertTrue(displayedMonthYear.contains(month), "Month does not match the expected month.");
    }
    @Test(dependsOnMethods ="testNavigateToTargetMonth",alwaysRun = true)
    public void testClickPath10Element() throws InterruptedException {
        WebElement path10Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='2' and @name='day' and @role='gridcell']")));
        Assert.assertTrue(path10Element.isDisplayed(), "'Path10 Element' is not visible.");
        path10Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath10Element",alwaysRun = true)
    public void testClickPath11Element() throws InterruptedException {
        WebElement path11Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='9' and @name='day' and @role='gridcell']")));
        Assert.assertTrue(path11Element.isDisplayed(), "'Path11 Element' is not visible.");
        path11Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath11Element",alwaysRun = true)
    public void testClickPath12Element() throws InterruptedException {
        WebElement path12Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Apply']")));
        Assert.assertTrue(path12Element.isDisplayed(), "'Path12 Element' is not visible.");
        path12Element.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods ="testClickPath12Element",alwaysRun = true)
    public void testClickPath13Element() throws InterruptedException {
        WebElement path13Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]")));
        Assert.assertTrue(path13Element.isDisplayed(), "'Path13 Element' is not visible.");
        path13Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath13Element",alwaysRun = true)
    public void testClickPath14Element() throws InterruptedException {
        WebElement path14Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Bar Chart']")));
        Assert.assertTrue(path14Element.isDisplayed(), "'Path14 Element' is not visible.");
        path14Element.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods ="testClickPath14Element",alwaysRun = true)
    public void testClickPath15Element() throws InterruptedException {
        WebElement path15Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]")));
        Assert.assertTrue(path15Element.isDisplayed(), "'Path15 Element' is not visible.");
        path15Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath15Element",alwaysRun = true)
    public void testClickPath16Element() throws InterruptedException {
        WebElement path16Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Metric Chart']")));
        Assert.assertTrue(path16Element.isDisplayed(), "'Path16 Element' is not visible.");
        path16Element.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods ="testClickPath16Element",alwaysRun = true)
    public void testClickPath17Element() throws InterruptedException {
        WebElement path17Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]")));
        Assert.assertTrue(path17Element.isDisplayed(), "'Path17 Element' is not visible.");
        path17Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath17Element",alwaysRun = true)
    public void testClickPath18Element() throws InterruptedException {
        WebElement path18Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Pie Chart']")));
        Assert.assertTrue(path18Element.isDisplayed(), "'Path18 Element' is not visible.");
        path18Element.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods ="testClickPath18Element",alwaysRun = true)
    public void testClickPath19Element() throws InterruptedException {
        WebElement path19Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]")));
        Assert.assertTrue(path19Element.isDisplayed(), "'Path19 Element' is not visible.");
        path19Element.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods ="testClickPath19Element",alwaysRun = true)
    public void testClickPath20Element() throws InterruptedException {
        WebElement path20Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Table Chart']")));
        Assert.assertTrue(path20Element.isDisplayed(), "'Path20 Element' is not visible.");
        path20Element.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods = "testClickPath20Element",alwaysRun = true)
    public void testClickArrow1() throws InterruptedException {
        // Click on the Arrow Element
        WebElement arrowElement12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']")));
        Assert.assertTrue(arrowElement12.isDisplayed(), "Arrow element is not visible.");
        arrowElement12.click();
        Thread.sleep(5000);
    }
    @Test(dependsOnMethods = "testClickArrow1",alwaysRun = true)
    public void testClickEngage1() throws InterruptedException {
        WebElement engageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Engage']")));
        Assert.assertTrue(engageElement.isDisplayed(), "Engage element is not visible.");
        engageElement.click();
    }

    @Test(dependsOnMethods = "testClickEngage1",alwaysRun = true)
    public void testClickCampaigns() throws InterruptedException {
        WebElement campaignsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Campaigns']")));
        Assert.assertTrue(campaignsElement.isDisplayed(), "Campaigns element is not visible.");
        campaignsElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickCampaigns",alwaysRun = true)
    public void testClickEngage2() throws InterruptedException {
        WebElement engageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Engage']")));
        Assert.assertTrue(engageElement.isDisplayed(), "Engage element is not visible.");
        engageElement.click();
    }

    @Test(dependsOnMethods = "testClickEngage2",alwaysRun = true)
    public void testClickFlexicast() throws InterruptedException {
        WebElement flexicastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Flexicast']")));
        Assert.assertTrue(flexicastElement.isDisplayed(), "Flexicast element is not visible.");
        flexicastElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickFlexicast",alwaysRun = true)
    public void testClickEngage3() throws InterruptedException {
        WebElement engageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Engage']")));
        Assert.assertTrue(engageElement.isDisplayed(), "Engage element is not visible.");
        engageElement.click();
    }
    @Test(dependsOnMethods = "testClickEngage3",alwaysRun = true)
    public void testClickTemplates() throws InterruptedException {
        WebElement templatesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Templates']")));
        Assert.assertTrue(templatesElement.isDisplayed(), "Templates element is not visible.");
        templatesElement.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods = "testClickTemplates",alwaysRun = true)
    public void testClickExperiments() throws InterruptedException {
        WebElement experimentsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Experiments']")));
        Assert.assertTrue(experimentsElement.isDisplayed(), "Experiments element is not visible.");
        experimentsElement.click();
    }

    @Test(dependsOnMethods = "testClickExperiments",alwaysRun = true)
    public void testClickExperiment() throws InterruptedException {
        WebElement experimentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Experiment']")));
        Assert.assertTrue(experimentElement.isDisplayed(), "Experiment element is not visible.");
        experimentElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickExperiment",alwaysRun = true)
    public void testClickExperimentsAgain() throws InterruptedException {
        WebElement experimentsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Experiments']")));
        Assert.assertTrue(experimentsElement.isDisplayed(), "Experiments element is not visible.");
        experimentsElement.click();
    }
    @Test(dependsOnMethods = "testClickExperimentsAgain",alwaysRun = true)
    public void testClickFeatureFlags() throws InterruptedException {
        WebElement featureFlagsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Feature Flags']")));
        Assert.assertTrue(featureFlagsElement.isDisplayed(), "Feature Flags element is not visible.");
        featureFlagsElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickFeatureFlags",alwaysRun = true)
    public void testClickHeatmaps() throws InterruptedException {
        WebElement heatmapsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Heatmaps']")));
        Assert.assertTrue(heatmapsElement.isDisplayed(), "Heatmaps element is not visible.");
        heatmapsElement.click();
        Thread.sleep(10000);
    }
    @Test(dependsOnMethods = "testClickHeatmaps",alwaysRun = true)
    public void testClickUsersFirst() throws InterruptedException {
        WebElement usersElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Users']")));
        Assert.assertTrue(usersElement.isDisplayed(), "Users element is not visible.");
        usersElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickUsersFirst",alwaysRun = true)
    public void testClickCohorts() throws InterruptedException {
        WebElement cohortsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Cohorts']")));
        Assert.assertTrue(cohortsElement.isDisplayed(), "Cohorts element is not visible.");
        cohortsElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickCohorts",alwaysRun = true)
    public void testClickUsersSecond() throws InterruptedException {
        WebElement usersElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Users']")));
        Assert.assertTrue(usersElement.isDisplayed(), "Users element is not visible.");
        usersElement.click();
    }

    @Test(dependsOnMethods = "testClickUsersSecond",alwaysRun = true)
    public void testClickProfile() throws InterruptedException {
        WebElement profileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Profile']")));
        Assert.assertTrue(profileElement.isDisplayed(), "Profile element is not visible.");
        profileElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickProfile",alwaysRun = true)
    public void testClickEvents() throws InterruptedException {
        WebElement eventsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Events']")));
        Assert.assertTrue(eventsElement.isDisplayed(), "Events element is not visible.");
        eventsElement.click();
    }

    @Test(dependsOnMethods = "testClickEvents",alwaysRun = true)
    public void testClickLiveEvents() throws InterruptedException {
        WebElement lvElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Live Events']")));
        Assert.assertTrue(lvElement.isDisplayed(), "Live Events element is not visible.");
        lvElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickLiveEvents",alwaysRun = true)
    public void testClickSvgElement1() throws InterruptedException {
        WebElement liElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div/div/div[2]/button[5]/div/*[name()='svg']")));
        Assert.assertTrue(liElement.isDisplayed(), "SVG element is not visible.");
        liElement.click();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods = "testClickSvgElement1",alwaysRun = true)
    public void testClickCustomEvents() throws InterruptedException {
        WebElement lcElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Custom Events']")));
        Assert.assertTrue(lcElement.isDisplayed(), "Custom Events element is not visible.");
        lcElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickCustomEvents",alwaysRun = true) // Adjust as needed
    public void testClickAlerts() throws InterruptedException {
        WebElement aElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Alerts']")));
        Assert.assertTrue(aElement.isDisplayed(), "Alerts element is not visible.");
        aElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickAlerts",alwaysRun = true)
    public void testClickSdkIntegrations() throws InterruptedException {
        // Wait for the Sdk Integrations element to be visible
        WebElement sdkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Sdk Integrations']")));
        Assert.assertTrue(sdkElement.isDisplayed(), "Sdk Integrations element is not visible.");
        sdkElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickSdkIntegrations",alwaysRun = true)
    public void testClickSettings() throws InterruptedException {
        // Wait for the Settings element to be visible
        WebElement settingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Settings']")));
        Assert.assertTrue(settingElement.isDisplayed(), "Settings element is not visible.");
        settingElement.click();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "testClickSettings",alwaysRun = true)
    public void testClickDarkModeButton() throws InterruptedException {
        // Wait for the dark mode button to be clickable
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='dark-mode']")));
        Assert.assertTrue(buttonElement.isDisplayed(), "Dark mode button is not visible.");
        buttonElement.click();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "testClickDarkModeButton",alwaysRun = true)
    public void testClickWorkspaceSettings() throws InterruptedException {
        // Wait for the Workspace Settings element to be visible
        WebElement workspaceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-sm font-medium'][normalize-space()='Workspace Settings']")));
        Assert.assertTrue(workspaceElement.isDisplayed(), "Workspace Settings element is not visible.");
        workspaceElement.click();
        Thread.sleep(10000);
    }

    @Test(dependsOnMethods = "testClickWorkspaceSettings",alwaysRun = true)
    public void testClickSettings1() throws InterruptedException {
        // Locate the "Settings" element
        WebElement settingElement1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Settings']")));
        Assert.assertTrue(settingElement1.isDisplayed(), "'Settings' element is not visible.");
        settingElement1.click();
        Thread.sleep(1000);
    }

    @Test (dependsOnMethods = "testClickSettings1",alwaysRun = true)
    public void testEngageAndSettings() throws InterruptedException {
        // Locate and click the "Engage Settings" element
        WebElement engageSettings = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Engage Settings']")));
        Assert.assertTrue(engageSettings.isDisplayed(), "'Engage Settings' element is not visible.");
        engageSettings.click();
        Thread.sleep(10000);
    }

    @Test (dependsOnMethods = "testEngageAndSettings",alwaysRun = true)
    public void testClickSettings2() throws InterruptedException {
        // Locate and click the "Engage Settings" element
        WebElement engageSettings2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Settings']")));
        Assert.assertTrue(engageSettings2.isDisplayed(), "'Engage Settings' element is not visible.");
        engageSettings2.click();
        Thread.sleep(10000);
    }
    @Test (dependsOnMethods = "testClickSettings2",alwaysRun = true)
    public void testClickDataGovernance() throws InterruptedException {
        // Locate and click the "Data Governance" element
        WebElement dataGovernance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Data Governance']")));
        Assert.assertTrue(dataGovernance.isDisplayed(), "'Data Governance' element is not visible.");
        dataGovernance.click();
        Thread.sleep(10000);
    }
    @Test (dependsOnMethods = "testClickDataGovernance",alwaysRun = true)
    public void testClickSettings3() throws InterruptedException {
        // Locate and click the "Settings" element
        WebElement settingElement3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Settings']")));
        Assert.assertTrue(settingElement3.isDisplayed(), "'Settings' element is not visible.");
        settingElement3.click();
        Thread.sleep(1000);
    }
    @Test (dependsOnMethods = "testClickSettings3",alwaysRun = true)
    public void testClickUsersAndTeams() throws InterruptedException {
        // Locate and click the "Users and Teams" element
        WebElement usersElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Users and Teams']")));
        Assert.assertTrue(usersElement.isDisplayed(), "'Users and Teams' element is not visible.");
        usersElement.click();
        Thread.sleep(10000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Ensure driver is closed in the end
        }
    }
}
