//Percept pixel
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class LaunchChrome {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://stage.percept.udaan.io/login");

            // Create a WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();

            // Click on "Sign in with Microsoft"
            WebElement signInWithMicrosoft = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Sign in with Microsoft']")));
            signInWithMicrosoft.click();

            // Store the original window handle
            String originalWindow = driver.getWindowHandle();

            // Wait for the new window to open and switch to it
            wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Wait for the new window
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
            if (emailInput.isDisplayed()) {
                emailInput.clear();
                emailInput.sendKeys("jitendra@perceptinsight.com");
            } else {
                System.out.println("Email input field is not visible.");
            }

            // Click on "Next"
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
            nextButton.click();

            // Wait for the password field and enter the password
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwd")));
            passwordField.clear();
            passwordField.sendKeys("Pearl@2030");

            // Click on "Sign in"
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))); // Ensure this targets the correct button
            signInButton.click();

            // Wait for the login process to complete and the original window to become active again

            Thread.sleep(10000);
            WebElement snInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))); // Ensure this targets the correct button
            snInButton.click();

            Thread.sleep(10000);
            // Switch back to the original window
            driver.switchTo().window(originalWindow);
            //home page
            WebElement element = driver.findElement(By.xpath("//a[contains(@class, 'rounded-md') and contains(@class, 'cursor-pointer') and contains(@class, 'outline-none') and contains(@class, 'space-x-2') and contains(@class, 'hover:bg-foreground-secondary') and contains(@class, 'text-foreground-selected') and contains(@class, 'flex') and contains(@class, 'items-center') and contains(@class, 'bg-foreground-secondary') and contains(@class, 'p-2')]//div[contains(@class, 'flex') and contains(@class, 'w-full') and contains(@class, 'items-center') and contains(@class, 'justify-between')]"));
            element.click();
            Thread.sleep(4000);

            // all reports
           WebElement pElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
            pElement.click();
            Thread.sleep(8000);

           //funnel dashboard
            WebElement funnelIcon = driver.findElement(By.xpath("//img[@class='w-8' and @alt='FUNNEL icon']"));
            funnelIcon.click();
            Thread.sleep(10000);

            WebElement InsightIcon = driver.findElement(By.xpath("//div[@title='Insight']"));
            InsightIcon.click();
            Thread.sleep(10000);

            WebElement flowIcon = driver.findElement(By.xpath("//div[@title='Flow']//*[name()='svg']"));
            flowIcon.click();
            Thread.sleep(10000);

            WebElement retention = driver.findElement(By.xpath("//div[@title='Retention']//*[name()='svg']"));
            retention.click();
            Thread.sleep(10000);

            WebElement targetElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]"));
            targetElement.click();

            Thread.sleep(1000);
            WebElement paragraphElement = driver.findElement(By.xpath("//p[text()='Retention Curve']"));
            paragraphElement.click();
            Thread.sleep(10000);

            //Home page
            WebElement svgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg.w-6.h-6[fill='none']")));
            svgElement.click();
            Thread.sleep(1000);

            //insight hub
            WebElement insightsHubElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Insights Hub']")));
            insightsHubElement.click();
            Thread.sleep(10000);

            //Create insight
            WebElement vgElement= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-sm font-medium' and text()='Create Reports']")));
            vgElement.click();

            WebElement insightsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-sm font-medium' and text()='Insights']")));
            insightsElement.click();
            Thread.sleep(1000);

            WebElement pathElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            pathElement.click();

            Thread.sleep(1000);
            WebElement appOpenElement = driver.findElement(By.xpath("//p[normalize-space()='APP_OPEN']"));
            appOpenElement.click();

            WebElement path1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            path1Element.click();

            Thread.sleep(1000);
            WebElement fileUploadedElement = driver.findElement(By.xpath("//span[contains(text(), 'FILE UPLOADED')]"));
            fileUploadedElement.click();

            Thread.sleep(10000);
            WebElement trgetElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]"));
            trgetElement.click();

            WebElement barChartElement = driver.findElement(By.xpath("//p[normalize-space()='Bar Chart']"));
            barChartElement.click();

            Thread.sleep(10000);
            WebElement spanElement = driver.findElement(By.xpath("//span[normalize-space()='14D']"));
            spanElement.click();

            //Arrow
            Thread.sleep(10000);
            WebElement ArElement = driver.findElement(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']"));
            ArElement.click();

            WebElement sgElement = driver.findElement(By.xpath("//p[normalize-space()='Engage']"));
            sgElement.click();

            WebElement cElement = driver.findElement(By.xpath("//p[normalize-space()='Campaigns']"));
            cElement.click();

            Thread.sleep(10000);
            WebElement gElement = driver.findElement(By.xpath("//p[normalize-space()='Engage']"));
            gElement.click();

            WebElement fElement = driver.findElement(By.xpath("//p[normalize-space()='Flexicast']"));
            fElement.click();

            Thread.sleep(10000);
            WebElement sElement = driver.findElement(By.xpath("//p[normalize-space()='Engage']"));
            sElement.click();

            WebElement tElement = driver.findElement(By.xpath("//p[normalize-space()='Templates']"));
            tElement.click();

            Thread.sleep(10000);
            WebElement eeElement = driver.findElement(By.xpath("//p[normalize-space()='Experiments']"));
            eeElement.click();

            WebElement eElement = driver.findElement(By.xpath("//p[normalize-space()='Experiment']"));
            eElement.click();
            Thread.sleep(10000);

            WebElement esElement = driver.findElement(By.xpath("//p[normalize-space()='Experiments']"));
            esElement.click();

            WebElement feElement = driver.findElement(By.xpath("//p[normalize-space()='Feature Flags']"));
            feElement.click();
            Thread.sleep(10000);

            WebElement hElement = driver.findElement(By.xpath("//p[normalize-space()='Heatmaps']"));
            hElement.click();
            Thread.sleep(10000);

            WebElement uElement = driver.findElement(By.xpath("//p[normalize-space()='Users']"));
            uElement.click();

            WebElement coElement = driver.findElement(By.xpath("//p[normalize-space()='Cohorts']"));
            coElement.click();
            Thread.sleep(10000);

            WebElement usElement = driver.findElement(By.xpath("//p[normalize-space()='Users']"));
            usElement.click();

            WebElement peElement = driver.findElement(By.xpath("//p[normalize-space()='Profile']"));
            peElement.click();
            Thread.sleep(10000);

            WebElement lElement = driver.findElement(By.xpath("//p[normalize-space()='Events']"));
            lElement.click();

            WebElement lvElement = driver.findElement(By.xpath("//p[normalize-space()='Live Events']"));
            lvElement.click();
            Thread.sleep(10000);

            WebElement liElement = driver.findElement(By.xpath("/html/body/div/main/div/div/div/div[2]/button[5]/div/*[name()='svg']"));
            liElement.click();

            WebElement lcElement = driver.findElement(By.xpath("//p[normalize-space()='Custom Events']"));
            lcElement.click();
            Thread.sleep(10000);

            WebElement aElement = driver.findElement(By.xpath("//p[normalize-space()='Alerts']"));
            aElement.click();
            Thread.sleep(10000);

            WebElement sdkElement = driver.findElement(By.xpath("//p[normalize-space()='Sdk Integrations']"));
            sdkElement.click();
            Thread.sleep(10000);

            WebElement wElement = driver.findElement(By.xpath("//p[normalize-space()='Percept Pixel']"));
            wElement.click();

            WebElement udElement = driver.findElement(By.xpath("//p[normalize-space()='Udaan Prod']"));
            udElement.click();
            Thread.sleep(10000);

            WebElement homeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Home']"))); // Update with actual home button locator
            homeButton.click();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
