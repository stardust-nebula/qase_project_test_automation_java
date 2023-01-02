package io.qase.tests.ui;

import io.qase.util.GenerateNames;
import io.qase.model.Project;
import io.qase.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qase.service.CreateNewProjectService;
import io.qase.service.LoginPageService;
import io.qase.service.ProjectsPageService;
import io.qase.util.Retry;

public class ProjectsPageTest extends BaseTest {

    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private static final int PREFIX_PROJECT_NAME_LENGTH = 5;
    private static final int PREFIX_PROJECT_CODE_LENGTH = 3;

    @BeforeClass
    public void setUp() {
        new LoginPageService().loginValidCredentials(new User());
        projectsPageService = new ProjectsPageService();
        createNewProjectService = new CreateNewProjectService();
    }

    @Test(testName = "Successful searching for existing project",
            description = "Verify successful searching for existing project",
            retryAnalyzer = Retry.class)
    public void verifySuccessfulSearchExistingProjectTest() {
        String projectCode = GenerateNames.generateRandomString(PREFIX_PROJECT_CODE_LENGTH).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(PREFIX_PROJECT_NAME_LENGTH);
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCreate(project);
        projectsPageService.openProjectsPage();
        projectsPageService.enterSearchCriteriaInSearchField(projectName);
        boolean isProjectLocated = projectsPageService.isProjectByNamePresentOnPage(projectName);
        Assert.assertTrue(isProjectLocated);
    }

    @Test(testName = "Project is no more shown on the Projects page after deleting",
            description = "Verify project is no more shown on the Projects page after deleting",
            retryAnalyzer = Retry.class)
    public void verifyProjectNotShownAfterDeletingTest() {
        String projectCode = GenerateNames.generateRandomString(PREFIX_PROJECT_CODE_LENGTH).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(PREFIX_PROJECT_NAME_LENGTH);
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCreate(project);
        projectsPageService.openProjectsPage();
        boolean isProjectDisplaysInTable = projectsPageService.isProjectByNamePresentOnPage(projectName);
        Assert.assertFalse(isProjectDisplaysInTable, "Project is shown on the page: '" + projectName + "'");
    }
}
