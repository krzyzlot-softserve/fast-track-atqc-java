import io.qameta.allure.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
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
        excelDataExtractor = new ExcelDataExtractor("C:\\Users\\kzlot\\IdeaProjects\\fast-track-atqc-java\\task3\\src\\test\\resources\\testData.xlsx", "Sheet1", "Test name");
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
    @Feature("Changing language")
    @Description("Changing language to english with upper-right dropdown")
    @Owner("Kzlot")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "Website", url = "https://oos.dmytrominochkin.cloud/#/")
    public void languageChangeTest() throws IOException {
        driver.get("https://oos.dmytrominochkin.cloud/#/");

        mainPage.changeLanguage();
        Allure.step("Changed language to english with dropdown");

        Assert.assertEquals(excelDataExtractor.getData("languageChangeTest").getFirst(), mainPage.getDropdownLanguageSelectionText());
        Allure.step("Verified language change");
    }

    @Test
    @Feature("Changing language")
    @Description("Changing language to english with upper-right dropdown, modified version")
    @Owner("Kzlot")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "Website", url = "https://oos.dmytrominochkin.cloud/#/")
    public void languageChangeTestWithFirstHeader() throws IOException {
        driver.get("https://oos.dmytrominochkin.cloud/#/");

        mainPage.changeLanguage();
        Allure.step("Changed language to english with dropdown");
        mainPage.selectLocation();
        Allure.step("Selected location");

        Assert.assertEquals(excelDataExtractor.getData("languageChangeTestWithFirstHeader").getFirst(), mainPage.getSectionText());
        Allure.step("Verified language change");
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }
}
