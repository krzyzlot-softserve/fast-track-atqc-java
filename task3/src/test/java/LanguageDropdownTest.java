import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LanguageDropdownTest {
    protected WebDriver driver;
    private MainPage mainPage;
    private ExcelDataExtractor excelDataExtractor;

    @BeforeTest
    public void setExcelDataExtractorSetup() throws IOException {
        excelDataExtractor = new ExcelDataExtractor("C:\\Users\\kzlot\\Documents\\testData.xlsx", "Sheet1", "Test name");
    }

    @BeforeMethod
    public void driverSetup() throws URISyntaxException, IOException {
        var nodeUri = "http://localhost:4444/wd/hub";
        var capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver(new URI(nodeUri).toURL(), capabilities);
        mainPage = new MainPage(driver);
    }

    @Test
    public void dropdownTest() throws IOException {
        driver.get("https://oos.dmytrominochkin.cloud/#/");

        mainPage.changeLanguage();

        Assert.assertEquals(excelDataExtractor.getData("dropdownTest").getFirst(), mainPage.getDropdownLanguageSelectionText());
    }

    @Test
    public void dropdownTestModified() throws IOException {
        driver.get("https://oos.dmytrominochkin.cloud/#/");

        mainPage.changeLanguage();
        mainPage.selectLocation();

        Assert.assertEquals(excelDataExtractor.getData("dropdownTestModified").getFirst(), mainPage.getSectionText());
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }
}
