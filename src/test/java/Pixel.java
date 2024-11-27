import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Pixel {
    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://app.perceptinsight.com");

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

            /*WebElement siElement = driver.findElement(By.xpath("//p[normalize-space()='Screen View 1_7sept']"));
            siElement.click();
            Thread.sleep(10000);


            WebElement ArElement = driver.findElement(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']"));
            ArElement.click();
            Thread.sleep(1000);

            WebElement p1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
            p1Element.click();
            Thread.sleep(5000);

            WebElement si1Element = driver.findElement(By.xpath("//p[normalize-space()='Pixel_insight']"));
            si1Element.click();
            Thread.sleep(10000);

            WebElement Ar4Element = driver.findElement(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']"));
            Ar4Element.click();
            Thread.sleep(1000);

            WebElement p4Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
            p4Element.click();
            Thread.sleep(5000);


            WebElement fsElement = driver.findElement(By.xpath("//p[normalize-space()='Funnel_10_step']"));
            fsElement.click();
            Thread.sleep(10000);

            WebElement Ar1Element = driver.findElement(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']"));
            Ar1Element.click();
            Thread.sleep(1000);

            WebElement p2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
            p2Element.click();
            Thread.sleep(5000);


            WebElement flElement = driver.findElement(By.xpath("//p[normalize-space()='10thstep flow']"));
            flElement.click();
            Thread.sleep(10000);

            WebElement Ar2Element = driver.findElement(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']"));
            Ar2Element.click();
            Thread.sleep(1000);

            WebElement p3Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
            p3Element.click();
            Thread.sleep(5000);

            WebElement reElement = driver.findElement(By.xpath("//p[normalize-space()='Retention_Curve']"));
            reElement.click();
            Thread.sleep(10000);

            WebElement Ar6Element = driver.findElement(By.xpath("//*[name()='svg'][@class='lucide lucide-arrow-right-to-line']"));
            Ar6Element.click();
            Thread.sleep(1000);

            WebElement p6Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='All Reports']")));
            p6Element.click();
            Thread.sleep(5000);


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

            //Home page
            WebElement svgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg.w-6.h-6[fill='none']")));
            svgElement.click();
            Thread.sleep(1000);

            //insight hub
            WebElement insightsHubElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Insights Hub']")));
            insightsHubElement.click();
            Thread.sleep(10000);

            //Dashboard
            WebElement Dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Your Boards']")));
            Dashboard.click();
            Thread.sleep(100);

            WebElement Pixel_Dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Pixel_Dashboard')]")));
            Pixel_Dashboard.click();
            Thread.sleep(10000);*/


            //Create insight
            WebElement vgElement= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-sm font-medium' and text()='Create Reports']")));
            vgElement.click();

            WebElement insightsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-sm font-medium' and text()='Insights']")));
            insightsElement.click();
            Thread.sleep(1000);

           WebElement path1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            path1Element.click();
            Thread.sleep(1000);

            WebElement inputBox = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox.sendKeys("All Events");
            inputBox.sendKeys(Keys.RETURN);

            Thread.sleep(1000);

            WebElement path1Element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            path1Element2.click();

            WebElement inputBox1 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox1.sendKeys("$click");
            inputBox1.sendKeys(Keys.RETURN);
            Thread.sleep(1000);

            WebElement path1Element3= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            path1Element3.click();

            WebElement inputBox2 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox2.sendKeys("$pageView");
            inputBox2.sendKeys(Keys.RETURN);
            Thread.sleep(1000);

            WebElement path1Element4= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            path1Element4.click();

            WebElement inputBox4 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox4.sendKeys("$scroll");
            inputBox4.sendKeys(Keys.RETURN);

            WebElement path1Element5= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            path1Element5.click();

            WebElement inputBox5 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox5.sendKeys("$webVitals");
            inputBox5.sendKeys(Keys.RETURN);

            Thread.sleep(1000);
            WebElement path1Element6= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex-1 overflow-y-scroll pb-12 no-scrollbar']//div[1]//div[1]//div[1]//div[1]//button[1]")));
            path1Element6.click();

            WebElement inputBox6 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox6.sendKeys("$fetch");
            inputBox6.sendKeys(Keys.RETURN);

            Thread.sleep(1000);

            WebElement svgElement = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]"));
            svgElement.click();

            WebElement paragraph = driver.findElement(By.xpath("//p[normalize-space(text())='All Properties']"));
            paragraph.click();
            Thread.sleep(1000);

            WebElement inputBox7 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox7.sendKeys("pi_city");
            inputBox7.sendKeys(Keys.RETURN);

            Thread.sleep(1000);
            WebElement inputBox8 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input"));
            inputBox8.sendKeys("bengaluru");
            Thread.sleep(1000);
            inputBox8.sendKeys(Keys.RETURN);
            Thread.sleep(1000);

            WebElement Breakdownelement = driver.findElement(By.xpath("//p[normalize-space(text())='Breakdown']"));
            Breakdownelement.click();
            Thread.sleep(1000);

            WebElement inputBox9 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input"));
            Thread.sleep(1000);
            inputBox9.sendKeys("pi_device_manufacturer");
            Thread.sleep(1000);
            inputBox9.sendKeys(Keys.RETURN);
            Thread.sleep(1000);

            WebElement spanElement = driver.findElement(By.xpath("//span[text()='14D']"));
            spanElement.click();
            Thread.sleep(10000);

            String year = "2024";
            String month = "October";

            // Open the date picker (replace with correct XPath)
            WebElement customElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'text-xs font-semibold') and text()='Custom']")));
            customElement.click();

            // Use while loop to navigate through months until the target month and year are found
            while (true) {

                // Get the current month and year displayed in the calendar
                WebElement monthYearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='text-sm font-medium' and @aria-live='polite' and @id='react-day-picker-1']")));
                String MonthYear = monthYearElement.getText();

                // Split the current displayed month and year into separate variables
                String[] arr = MonthYear.split(" ");
                String currentMonth = arr[0];
                String currentYear = arr[1];

                // Check if the current month and year match the target month and year
                if (currentMonth.equalsIgnoreCase(month) && currentYear.equals(year)) {
                    System.out.println("Found the target month and year: " + MonthYear);
                    break; // Break the loop once the target month/year is found
                } else {
                    // If not, click the 'next-month' button to go to the next month
                    WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@name='previous-month']//*[name()='svg']")));
                    nextMonthButton.click();
                }

                // Optional: wait for calendar to update (if needed)
                Thread.sleep(1000); // Adjust this if needed or use explicit waits
            }
            WebElement dayButton = driver.findElement(By.xpath("//button[normalize-space()='2' and @name='day' and @role='gridcell']"));
            dayButton.click();

            WebElement dayButton1 = driver.findElement(By.xpath("//button[normalize-space()='9' and @name='day' and @role='gridcell']"));
            dayButton1.click();

            Thread.sleep(1000);
            WebElement applyButton = driver.findElement(By.xpath("//button[normalize-space()='Apply']"));
            applyButton.click();

            Thread.sleep(1000);

            WebElement spanElementByXPath = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]"));
            spanElementByXPath.click();

            WebElement barChartElement = driver.findElement(By.xpath("//p[normalize-space()='Bar Chart']"));
            barChartElement.click();
            Thread.sleep(10000);

            WebElement spanElementByXPath1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]"));
            spanElementByXPath1.click();

            WebElement MetricChartElement = driver.findElement(By.xpath("//p[normalize-space()='Metric Chart']"));
            MetricChartElement.click();

            Thread.sleep(10000);
            WebElement spanElementByXPath2 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]"));
            spanElementByXPath2.click();

            WebElement PieChartElement = driver.findElement(By.xpath("//p[normalize-space()='Pie Chart']"));
            PieChartElement.click();

            Thread.sleep(10000);
            WebElement spanElementByXPath3 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]"));
            spanElementByXPath3.click();

            WebElement TableChartElement = driver.findElement(By.xpath("//p[normalize-space()='Table Chart']"));
            TableChartElement.click();

            Thread.sleep(10000);
            WebElement FunnelElement = driver.findElement(By.xpath("//div[@title='Funnel']//*[name()='svg']"));
            FunnelElement.click();

            Thread.sleep(10000);
            WebElement FlowElement = driver.findElement(By.xpath("//div[@title='Flow']//*[name()='svg']"));
            FlowElement.click();

            Thread.sleep(10000);
            WebElement RetentionElement = driver.findElement(By.xpath("//div[@title='Retention']//*[name()='svg']"));
            RetentionElement.click();

            Thread.sleep(10000);
            WebElement RetentionElement1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]"));
            RetentionElement1.click();

            Thread.sleep(1000);
            WebElement RetentionElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Retention Curve']")));
            RetentionElement2.click();

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

            //SDK
            WebElement sdkElement = driver.findElement(By.xpath("//p[normalize-space()='Sdk Integrations']"));
            sdkElement.click();
            Thread.sleep(10000);

            //setting
            WebElement settingElement = driver.findElement(By.xpath("//p[normalize-space()='Settings']"));
            settingElement.click();
            Thread.sleep(1000);

            WebElement buttonElement = driver.findElement(By.xpath("//button[@id='dark-mode']"));
            buttonElement.click();
            Thread.sleep(1000);

            WebElement workspaceElement = driver.findElement(By.xpath("//p[@class='text-sm font-medium'][normalize-space()='Workspace Settings']"));
            workspaceElement.click();
            Thread.sleep(10000);

            WebElement settingElement1 = driver.findElement(By.xpath("//p[normalize-space()='Settings']"));
            settingElement1.click();
            Thread.sleep(1000);

            WebElement Engage_settings = driver.findElement(By.xpath("//p[normalize-space()='Engage Settings']"));
            Engage_settings.click();
            Thread.sleep(10000);

            WebElement settingElement2 = driver.findElement(By.xpath("//p[normalize-space()='Settings']"));
            settingElement2.click();
            Thread.sleep(1000);

            WebElement Data_Governance = driver.findElement(By.xpath("//p[normalize-space()='Data Governance']"));
            Data_Governance.click();
            Thread.sleep(10000);

            WebElement settingElement3 = driver.findElement(By.xpath("//p[normalize-space()='Settings']"));
            settingElement3.click();
            Thread.sleep(1000);


            WebElement Users = driver.findElement(By.xpath("//p[normalize-space()='Users and Teams']"));
            Users.click();
            Thread.sleep(10000);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
