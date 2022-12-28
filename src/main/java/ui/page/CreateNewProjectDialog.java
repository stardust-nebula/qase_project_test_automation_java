package ui.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.util.AllureUtils;

@Log4j2
public class CreateNewProjectDialog extends BasePage {

    private static String projectAccessTypeRadioButtonPath = "//input[@type='radio' and @value='%s']/ancestor::label";

    @FindBy(xpath = "//input[@id='project-name']")
    private WebElement projectNameField;

    @FindBy(xpath = "//input[@id='project-code']")
    private WebElement projectCodeField;

    @FindBy(xpath = "//textarea[@id='description-area']")
    private WebElement descriptionField;

    @FindBy(xpath = "//input[@type='radio' and @value='private']/ancestor::label")
    private WebElement privateRadioButton;

    @FindBy(xpath = "//label[contains(text(),'Member access')]/ancestor::div[2]")
    private WebElement memberAccessComponent;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createProjectButton;

    @FindBy(xpath = "//span[contains(text(),'Cancel')]")
    private WebElement cancelButton;

    @Step("Enter Project name")
    public CreateNewProjectDialog enterProjectName(String name) {
        log.info("Enter project name: " + name);
        waitVisibilityOfElement(projectNameField).clear();
        projectNameField.sendKeys(name);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Enter Project code")
    public CreateNewProjectDialog enterProjectCode(String code) {
        log.info("Enter project code: " + code);
        if (code != null) {
            waitVisibilityOfElement(projectCodeField).clear();
            projectCodeField.sendKeys(code);
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Enter Description")
    public CreateNewProjectDialog enterDescription(String description) {
        log.info("Enter description");
        waitVisibilityOfElement(descriptionField).clear();
        if (description != null) {
            descriptionField.sendKeys(description);
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Choose Project access type")
    public CreateNewProjectDialog chooseProjectAccessType(String accessType) {
        if (accessType != null) {
            log.info("Choose project access type");
            By accessTypeRadioButton = getElementLocatorByPathAndOneParam(projectAccessTypeRadioButtonPath, accessType.toLowerCase());
            waitElementToBeClickable(driver.findElement(accessTypeRadioButton)).click();
        } else {
            log.info("Project access type left default");
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Click on the 'Create project' button")
    public void clickCreateProjectButton() {
        log.info("Click on the 'Create project' button");
        waitElementToBeClickable(createProjectButton).click();
        AllureUtils.takeScreenshot(driver);
    }

    @Step("Click on the 'Cancel' button")
    public void clickCancelButton() {
        log.info("Click on the 'Cancel' button");
        waitElementToBeClickable(cancelButton).click();
        AllureUtils.takeScreenshot(driver);
    }

    public boolean isMemberAccessComponentVisible() {
        log.info("Check 'Member access' component visibility");
        AllureUtils.takeScreenshot(driver);
        boolean isComponentPresent = true;
        try {
            waitVisibilityOfElement(memberAccessComponent);
        } catch (NoSuchElementException | TimeoutException e) {
            isComponentPresent = false;
        }
        return isComponentPresent;
    }
}
