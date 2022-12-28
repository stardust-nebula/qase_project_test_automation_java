package ui;

import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.service.CreateNewProjectService;
import ui.service.LoginPageService;
import ui.service.ProjectRepositoryPageService;
import ui.service.ProjectsPageService;

public class CreateNewProjectDialogTest extends BaseTest {
    private static final int prefixProjectNameLength = 5;
    private static final int prefixProjectCodeLength = 3;
    private LoginPageService loginPageService;
    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private ProjectRepositoryPageService projectRepositoryPageService;
    private static final User user = new User();

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectsPageService = new ProjectsPageService();
        createNewProjectService = new CreateNewProjectService();
        projectRepositoryPageService = new ProjectRepositoryPageService();
        loginPageService.loginValidCredentials(user);
    }

    @Test
    public void verifyMemberAccessComponentNotShownWhenPublicProjectAccessType() {
        String projectAccessType = "Public";
        createNewProjectService
                .openCreateNewProjectDialog()
                .chooseProjectAccessType(projectAccessType);
        Assert.assertFalse(createNewProjectService.isMemberAccessComponentVisible(), "Member Access component is visible");
    }

    @Test
    public void verifySuccessfulCreatingNewProject() {
        String projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = createNewProjectService.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCreate(project);
        String actualPageTitle = projectRepositoryPageService.getProjectsRepositoryPageTitle();
        Assert.assertTrue(actualPageTitle.contains(projectCode));
    }

    @Test
    public void cancelCreationOfNewProject() {
        String expectedProjectsPageTitle = "Projects";
        String projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = createNewProjectService.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCancelCreation(project);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(projectsPageService.getPageTitle(), expectedProjectsPageTitle,
                "'Projects' title not found. Probably wrong page is opened");
        softAssert.assertFalse(projectsPageService.isProjectByNamePresentOnPage(projectName),
                "Project is shown on the page");
        softAssert.assertAll();
    }
}
