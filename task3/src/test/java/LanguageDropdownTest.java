import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LanguageDropdownTest {
    private ChromeDriver driver;

    @BeforeMethod
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kzlot\\IdeaProjects\\fast-track-atqc-java\\task3\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void dropdownTest() {
        driver.get("https://oos.dmytrominochkin.cloud/#/");

        WebElement dropdownButton = driver.findElement(By.cssSelector("button.lang"));
        dropdownButton.click();
        WebElement dropdownEnglish = driver.findElement(By.cssSelector("mat-option[value='en']"));
        dropdownEnglish.click();
        WebElement dropdownSelection = driver.findElement(By.cssSelector("span.mat-select-min-line"));

        Assert.assertEquals("EN", dropdownSelection.getText());
    }

    @Test
    public void dropdownTestModified() {
        slowNetworkSetup();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://oos.dmytrominochkin.cloud/#/");

        WebElement dropdownButton = driver.findElement(By.cssSelector("button.lang"));
        dropdownButton.click();
        WebElement dropdownEnglishOption = driver.findElement(By.cssSelector("mat-option[value='en']"));
        dropdownEnglishOption.click();
        WebElement dropdownSelection = driver.findElement(By.cssSelector("span.mat-select-min-line"));
        WebElement locationDropdown = driver.findElement(By.xpath("//*[@id='mat-input-0']"));
        locationDropdown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mat-option-2']/span")));
        WebElement locationSelection = driver.findElement(By.xpath("//*[@id='mat-option-2']/span"));
        locationSelection.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Top categories']")));
        WebElement firstH3Element = driver.findElement(By.tagName("h3"));

        Assert.assertEquals("Top categories", firstH3Element.getText());
        Assert.assertEquals("EN", dropdownSelection.getText());
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

    private void slowNetworkSetup() {
        var networkOptions = new ChromiumNetworkConditions();
        networkOptions.setOffline(false);
        networkOptions.setLatency(Duration.ofMillis(150));
        networkOptions.setDownloadThroughput(100 * 1024); // in kb/seconds
        networkOptions.setUploadThroughput(100 * 1024); // in kb/seconds
        driver.setNetworkConditions(networkOptions);
    }

}
