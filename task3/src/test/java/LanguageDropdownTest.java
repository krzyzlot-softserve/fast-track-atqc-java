import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumNetworkConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LanguageDropdownTest {
    private ChromeDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kzlot\\IdeaProjects\\fast-track-atqc-java\\task3\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
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
        driver.get("https://oos.dmytrominochkin.cloud/#/");

        mainPage.changeLanguage();
        mainPage.selectLocation();

        Assert.assertEquals("Top categories", mainPage.getSectionText());
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

    private void slowNetworkSetup() {
        var networkOptions = new ChromiumNetworkConditions();
        networkOptions.setOffline(false);
        networkOptions.setLatency(Duration.ofMillis(150));
        networkOptions.setDownloadThroughput(150 * 1024); // in kb/seconds
        networkOptions.setUploadThroughput(150 * 1024); // in kb/seconds
        driver.setNetworkConditions(networkOptions);
    }

}
