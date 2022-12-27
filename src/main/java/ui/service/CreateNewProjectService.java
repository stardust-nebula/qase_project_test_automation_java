package ui.service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.Project;
import org.apache.commons.lang3.RandomStringUtils;
import ui.page.CreateNewProjectDialog;
import ui.page.ProjectRepositoryPage;
import ui.page.ProjectsPage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class CreateNewProjectService {
    private CreateNewProjectDialog createNewProjectDialog = new CreateNewProjectDialog();
    private ProjectsPage projectsPage = new ProjectsPage();

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

    @Step("Choose Project access type by name")
    public CreateNewProjectDialog chooseProjectAccessType(String projectAccessType) {
        log.info("Choose Project access type by name: " + projectAccessType);
        createNewProjectDialog.chooseProjectAccessType(projectAccessType);
        return new CreateNewProjectDialog();
    }

    public boolean isMemberAccessComponentVisible() {
        return createNewProjectDialog.isMemberAccessComponentVisible();
    }

    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public String generateTestProjectNameWithCurrentDate(int prefixProjectNameLength) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date date = new Date(System.currentTimeMillis());
        String dateForName = simpleDateFormat.format(date);
        String prefixProjectName = generateRandomString(prefixProjectNameLength);
        return prefixProjectName + " current date" + dateForName;
    }
}
