package io.qase.service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import io.qase.model.Project;
import io.qase.page.CreateNewProjectDialog;
import io.qase.page.ProjectRepositoryPage;
import io.qase.page.ProjectsPage;

@Log4j2
public class CreateNewProjectService {
    private CreateNewProjectDialog createNewProjectDialog = new CreateNewProjectDialog();
    private ProjectsPage projectsPage = new ProjectsPage();
// Comment 1 local
    @Step("Open 'Create new project dialog")
    public CreateNewProjectDialog openCreateNewProjectDialog() {
        log.info("Open 'Create new project dialog");
        projectsPage.clickOnCreateNewProject();
        return new CreateNewProjectDialog();
    }

    @Step("Fill in information about the project and Cancel creation")
    public ProjectsPage fillInProjectInfoCancelCreation(Project project) {
        log.info("Fill in information about the project and Cancel creation");
        createNewProjectDialog
                .enterProjectName(project.getProjectName())
                .enterProjectCode(project.getProjectCode())
                .enterDescription(project.getDescription())
                .chooseProjectAccessType(project.getAccessType())
                .clickCancelButton();
        return new ProjectsPage();
    }

    @Step("Fill in information about the project and Create a project")
    public ProjectRepositoryPage fillInProjectInfoCreate(Project project) {
        log.info("Fill in information about the project and Create a project");
        createNewProjectDialog
                .enterProjectName(project.getProjectName())
                .enterProjectCode(project.getProjectCode())
                .enterDescription(project.getDescription())
                .chooseProjectAccessType(project.getAccessType())
                .clickCreateProjectButton();
        return new ProjectRepositoryPage();
    }

    public boolean isMemberAccessComponentVisible() {
        return createNewProjectDialog.isMemberAccessComponentVisible();
    }

}
