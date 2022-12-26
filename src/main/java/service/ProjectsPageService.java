package service;

import lombok.extern.log4j.Log4j2;
import page.ProjectsPage;

import static util.StringConstant.PROJECTS_PAGE_URL;

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

    public void clickOnProjectNameInTable(String projectName) {
        projectsPage.clickOnProjectNameInTable(projectName);
    }


}
