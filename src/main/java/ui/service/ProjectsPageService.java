package ui.service;

import lombok.extern.log4j.Log4j2;
import ui.page.ProjectsPage;

import static ui.util.StringConstant.PROJECTS_PAGE_URL;

@Log4j2
public class ProjectsPageService {

    private ProjectsPage projectsPage = new ProjectsPage();

    public boolean isProjectByNamePresentOnPage(String projectName) {
        return projectsPage.isProjectByNamePresentOnPage(projectName);
    }

    public String getPageTitle() {
        return projectsPage.getPageTitleText();
    }

    public ProjectsPage openProjectsPage() {
        return projectsPage.openProjectsPage(PROJECTS_PAGE_URL);
    }

    public ProjectsPage enterSearchCriteriaInSearchField(String searchCriteria) {
        return projectsPage.enterSearchCriteriaInSearchField(searchCriteria);
    }

    public ProjectsPage clickOnMeatballsIconForProjectByName(String projectName) {
        return projectsPage.clickOnMeatballsIconForProjectByName(projectName);
    }

    public void clickOnProjectNameInTable(String projectName) {
        projectsPage.clickOnProjectNameInTable(projectName);
    }

    public void clickOnSettingsOptionByProject(String projectName) {
        projectsPage.clickOnSettingsOptionByProject(projectName);
    }




}
