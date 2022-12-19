package page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.AllureUtils;

@Log4j2
public class ProjectsPage extends BasePage {

    private String projectNameInTheListPath = "//a[@class='defect-title' and contains(text(),'%s')]";
    private String projectsContextMenuIconPath = "//a[@class='defect-title' and contains(text(),'%s')]/ancestor::tr//a[@data-bs-toggle='dropdown']";

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
    public void clickOnCreateNewProject(){
        log.info("Click on the 'Create new project' button");
        waitVisibilityOfElement(createNewProjectButton).click();
        AllureUtils.takeScreenshot(driver);
    }
}
