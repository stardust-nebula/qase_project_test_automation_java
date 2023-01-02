package io.qase.tests.ui;

import io.qase.util.GenerateNames;
import io.qase.model.Project;
import io.qase.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qase.service.CreateNewProjectService;
import io.qase.service.LoginPageService;
import io.qase.service.ProjectRepositoryPageService;
import io.qase.service.ProjectsPageService;
import io.qase.util.Retry;

public class CreateNewProjectDialogTest extends BaseTest {
    private static final int PREFIX_PROJECT_NAME_LENGTH = 5;
    private static final int PREFIX_PROJECT_CODE_LENGTH = 3;

    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private ProjectRepositoryPageService projectRepositoryPageService;

    @BeforeClass
    public void setUp() {
        User user = new User();
        new LoginPageService().loginValidCredentials(user);
        projectsPageService = new ProjectsPageService();
        createNewProjectService = new CreateNewProjectService();
        projectRepositoryPageService = new ProjectRepositoryPageService();
    }

    @BeforeMethod
    public void openProjectPage(){
        projectsPageService.openProjectsPage();
    }

    @Test(testName = "Member Access Component is not shown when Public Project Access Type is selected",
    description = "Verify than Member Access Component is not shown when Public Project Access Type is selected",
            retryAnalyzer = Retry.class)
    public void verifyMemberAccessComponentNotShownWhenPublicProjectAccessTypeTest() {
        String projectAccessType = "Public";
        createNewProjectService
                .openCreateNewProjectDialog()
                .chooseProjectAccessType(projectAccessType);
        Assert.assertFalse(createNewProjectService.isMemberAccessComponentVisible(), "Member Access component is visible");
    }

    @Test(testName = "Successful creation of New Project", description = "Verify successful creation of New Project",
            retryAnalyzer = Retry.class)
    public void verifySuccessfulCreatingNewProjectTest() {
        String projectCode = GenerateNames.generateRandomString(PREFIX_PROJECT_CODE_LENGTH).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(PREFIX_PROJECT_NAME_LENGTH);
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCreate(project);
        String actualPageTitle = projectRepositoryPageService.getProjectsRepositoryPageTitle();
        Assert.assertTrue(actualPageTitle.contains(projectCode));
    }

    @Test(testName = "Successful canceling on creating a New Project",
            description = "Verify successful canceling on creating a New Project",
            retryAnalyzer = Retry.class)
    public void verifySuccessfulCancelingOnCreationNewProjectTest() {
        String expectedProjectsPageTitle = "Projects";
        String projectCode = GenerateNames.generateRandomString(PREFIX_PROJECT_CODE_LENGTH).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(PREFIX_PROJECT_NAME_LENGTH);
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
