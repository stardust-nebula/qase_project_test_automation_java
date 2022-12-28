package ui.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.util.AllureUtils;

@Log4j2
public class ProjectsPage extends BasePage {

    private String projectNameInTheListPath = "//a[@class='defect-title' and contains(text(),'%s')]";
    private String projectsContextMenuIconPath = "//a[@class='defect-title' and contains(text(),'%s')]/ancestor::tr//a[@data-bs-toggle='dropdown']";
    private String settingsOptionInContextMenuPath = "//a[@class='defect-title' and contains(text(),'%s')]/ancestor::tr//div[@class='dropdown-item']/a[contains(text(),'Settings')]";

    @FindBy(xpath = "//h1[contains(text(),'Projects')]")
    private WebElement projectsPageTitle;

    @FindBy(xpath = "//button[@id='createButton']")
    private WebElement createNewProjectButton;

    @FindBy(xpath = "//input[contains(@class,'search-input')]")
    private WebElement searchInputField;

    public String getPageTitleText() {
        log.info("Take page title of the 'Projects' page");
        AllureUtils.takeScreenshot(driver);
        return projectsPageTitle.getText();
    }

    @Step("Click on the 'Create new project' button")
    public void clickOnCreateNewProject() {
        log.info("Click on the 'Create new project' button");
        waitElementToBeClickable(createNewProjectButton).click();
        AllureUtils.takeScreenshot(driver);
    }

    public boolean isProjectByNamePresentOnPage(String projectName) {
        log.info("Check project by name is shown on the Projects page");
        AllureUtils.takeScreenshot(driver);
        try {
            driver.findElement(getElementLocatorByPathAndOneParam(projectNameInTheListPath, projectName));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Open 'Projects' page")
    public ProjectsPage openProjectsPage(String url) {
        log.info("Open Projects page");
        driver.get(url);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public ProjectsPage enterSearchCriteriaInSearchField(String searchCriteria) {
        log.info("Search project by search criteria: " + searchCriteria);
        waitVisibilityOfElement(searchInputField).sendKeys(searchCriteria);
        return this;
    }

    @Step("Click on the project name in the table to open repository page")
    public void clickOnProjectNameInTable(String projectName) {
        log.info("Click on the project name in the table to open repository page");
        driver.findElement(getElementLocatorByPathAndOneParam(projectNameInTheListPath, projectName)).click();
        AllureUtils.takeScreenshot(driver);
    }

    @Step("Click on the meatballs icon for the selected project")
    public ProjectsPage clickOnMeatballsIconForProjectByName(String projectName) {
        log.info("Click on the meatballs icon for the selected project");
        driver.findElement(getElementLocatorByPathAndOneParam(projectsContextMenuIconPath, projectName)).click();
        return new ProjectsPage();
    }

    @Step("Click on the 'Settings' option in the context menu for a specific Project")
    public void clickOnSettingsOptionByProject(String projectName) {
        driver.findElement(getElementLocatorByPathAndOneParam(settingsOptionInContextMenuPath, projectName)).click();
    }
}
