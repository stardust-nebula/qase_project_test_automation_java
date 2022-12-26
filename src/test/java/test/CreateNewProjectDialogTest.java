package test;

import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.CreateNewProjectService;
import service.LoginPageService;
import service.ProjectRepositoryPageService;
import service.ProjectsPageService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewProjectDialogTest extends BaseTest {
    private static int prefixProjectNameLength = 5;
    private static int prefixProjectCodeLength = 3;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    private LoginPageService loginPageService;
    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private ProjectRepositoryPageService projectRepositoryPageService;
    private static User user = new User();

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
        Date date = new Date(System.currentTimeMillis());
        String dateForName = simpleDateFormat.format(date);
        String prefixProjectName = createNewProjectService.generateRandomString(prefixProjectNameLength);
        String projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = prefixProjectName + " current date" + dateForName;
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
        Date date = new Date(System.currentTimeMillis());
        String dateForName = simpleDateFormat.format(date);
        String prefixProjectName = createNewProjectService.generateRandomString(prefixProjectNameLength);
        String projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = prefixProjectName + " current date" + dateForName;
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
