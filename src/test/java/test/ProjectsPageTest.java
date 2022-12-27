package test;

import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.CreateNewProjectService;
import service.LoginPageService;
import service.ProjectsPageService;

public class ProjectsPageTest extends BaseTest {

    private LoginPageService loginPageService;
    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private static final User user = new User();
    private static int prefixProjectNameLength = 5;
    private static int prefixProjectCodeLength = 3;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectsPageService = new ProjectsPageService();
        createNewProjectService = new CreateNewProjectService();
        loginPageService.loginValidCredentials(user);
    }

    @Test
    public void verifySuccessfulSearchExistingProjectTest() {
        String projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = createNewProjectService.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
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

    @Test(testName = "Verify project is no more shown on the Projects page after deleting")
    public void verifyProjectNotShownAfterDeleting() {
        String projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = createNewProjectService.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCreate(project);
        projectsPageService.openProjectsPage();
        boolean isProjectDisplaysInTable = projectsPageService.isProjectByNamePresentOnPage(projectName);
        Assert.assertTrue(isProjectDisplaysInTable, "Project is shown on the page: '" + projectName + "'");
    }
}
