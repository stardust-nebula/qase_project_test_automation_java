package ui;

import api.util.GenerateNames;
import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.service.CreateNewProjectService;
import ui.service.LoginPageService;
import ui.service.ProjectsPageService;
import ui.util.Retry;

public class ProjectsPageTest extends BaseTest {

    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private static int prefixProjectNameLength = 5;
    private static int prefixProjectCodeLength = 3;

    @BeforeClass
    public void setUp() {
        User user = new User();
        new LoginPageService().loginValidCredentials(user);
        projectsPageService = new ProjectsPageService();
        createNewProjectService = new CreateNewProjectService();
    }

    @Test(testName = "Successful searching for existing project",
            description = "Verify successful searching for existing project",
            retryAnalyzer = Retry.class)
    public void verifySuccessfulSearchExistingProjectTest() {
        String projectCode = GenerateNames.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
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
        String projectCode = GenerateNames.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
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
