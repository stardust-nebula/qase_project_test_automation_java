package page;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static util.WaitTimeout.EXPLICIT_WAIT_TIMEOUT_SECONDS;

public abstract class BasePage {
    protected WebDriver driver = DriverSingleton.getDriver();

    protected BasePage() {
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitVisibilityOfElement(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitElementToBeClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected By getElementLocatorByPathAndOneParam(String path, String paramText) {
        return By.xpath(String.format(path, paramText));
    }
}
