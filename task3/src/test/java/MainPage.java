import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    @FindBy(css = "button.lang")
    WebElement dropdownButton;
    @FindBy(css = "mat-option[value='en']")
    WebElement dropdownEnglish;
    @FindBy(tagName = "h3")
    WebElement firstH3Element;
    @FindBy(css = "span.mat-select-min-line")
    WebElement dropdownLanguageSelection;
    @FindBy(xpath = "//*[@id='mat-input-0']")
    WebElement locationDropdown;
    @FindBy(xpath = "//*[@id='mat-option-2']/span")
    WebElement locationSelection;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void changeLanguage() {
        dropdownButton.click();
        dropdownEnglish.click();
    }

    public String getSectionText() {
        return firstH3Element.getText();
    }

    public void selectLocation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        locationDropdown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mat-option-2']/span")));
        locationSelection.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Top categories']")));
    }

    public String getDropdownLanguageSelectionText() {
        return dropdownLanguageSelection.getText();
    }
}




