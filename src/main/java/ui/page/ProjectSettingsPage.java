package ui.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.util.AllureUtils;

@Log4j2
public class ProjectSettingsPage extends BasePage {

    private String alertPopupPath = "//span[contains(text(),'%s')]/ancestor::div[@role='alert']";
    private String projectSettingsUpdatedSuccessfullyText = "Project settings were successfully updated!";

    @FindBy(xpath = "//textarea[@id='description-area']")
    private WebElement descriptionField;

    @FindBy(xpath = "//h1[contains(text(),'Project settings')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement updateSettingsButton;

    @Step("Enter new description")
    public ProjectSettingsPage enterNewDescription(String newDescription) {
        log.info("Enter new description");
        waitVisibilityOfElement(descriptionField).clear();
        descriptionField.sendKeys(newDescription);
        AllureUtils.takeScreenshot(driver);
        return new ProjectSettingsPage();
    }

    @Step("Click on the 'Update settings' button")
    public void clickOnUpdateSettingsButton() {
        log.info("Click on the 'Update settings' button");
        waitElementToBeClickable(updateSettingsButton).click();
        AllureUtils.takeScreenshot(driver);
    }

    public boolean isAlertOnSuccessfulProjectUpdateAppears() {
        log.info("Check if Alert pop-up appears on successful updating project settings");
        By alertElement = getElementLocatorByPathAndOneParam(alertPopupPath, projectSettingsUpdatedSuccessfullyText);
        boolean isAlertAppears = driver.findElement(alertElement).isDisplayed();
        AllureUtils.takeScreenshot(driver);
        return isAlertAppears;
    }

    public String getTextFromUpdateProjectSettingsSuccessAlert() {
        By alertElement = getElementLocatorByPathAndOneParam(alertPopupPath, projectSettingsUpdatedSuccessfullyText);
        return driver.findElement(alertElement).getText();
    }
}
