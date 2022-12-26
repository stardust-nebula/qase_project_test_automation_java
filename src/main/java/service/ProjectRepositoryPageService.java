package service;

import lombok.extern.log4j.Log4j2;
import page.ProjectRepositoryPage;

@Log4j2
public class ProjectRepositoryPageService {

    private ProjectRepositoryPage projectRepositoryPage = new ProjectRepositoryPage();

    public String getProjectsRepositoryPageTitle() {
        return projectRepositoryPage.getProjectsRepositoryPageTitle();
    }

    public void clickOnAddSuiteButton() {
        projectRepositoryPage.clickOnAddSuiteButton();
    }

    public void clickOnAddCaseButton() {
        projectRepositoryPage.clickOnAddCaseButton();
    }

    public boolean isSuiteByNamePresent(String suiteName) {
        return projectRepositoryPage.isSuiteByNamePresent(suiteName);
    }

    public void clickOnSuiteNameInTheList(String suiteName){
        projectRepositoryPage.clickOnSuiteNameInList(suiteName);
    }

    public boolean isAlertOnTestCaseCreatedSuccessfullyAppears(){
        return projectRepositoryPage.isAlertOnTestCaseCreatedSuccessfullyAppears();
    }

    public String getTextFromCreateTestCaseSuccessAlert(){
        return projectRepositoryPage.getTextFromCreateTestCaseSuccessAlert();
    }
}
