package ui;

import api.util.GenerateNames;
import model.Project;
import model.Suite;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.service.CreateNewProjectService;
import ui.service.CreateNewSuiteDialogService;
import ui.service.LoginPageService;
import ui.service.ProjectRepositoryPageService;
import ui.util.Retry;

public class CreateNewSuiteDialogTest extends BaseTest {

    private static int prefixProjectNameLength = 5;
    private static int prefixProjectCodeLength = 3;
    private CreateNewProjectService createNewProjectService;
    private ProjectRepositoryPageService projectRepositoryPageService;
    private CreateNewSuiteDialogService createNewSuiteDialogService;

    @BeforeClass
    public void setUp() {
        User user = new User();
        new LoginPageService().loginValidCredentials(user);
        createNewProjectService = new CreateNewProjectService();
        projectRepositoryPageService = new ProjectRepositoryPageService();
        createNewSuiteDialogService = new CreateNewSuiteDialogService();
    }

    @Test(testName = "Successful creation of a New Suite",
            description = "Verify successful creation of a New Suite",
            retryAnalyzer = Retry.class)
    public void verifySuccessfulNewSuiteCreationTest() {
        String suiteName = "Regression";
        Suite suite = Suite.builder()
                .suiteName(suiteName)
                .build();
        String projectCode = GenerateNames.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCreate(project);
        projectRepositoryPageService.clickOnAddSuiteButton();
        createNewSuiteDialogService.fillSuiteDataCreate(suite);
        Assert.assertTrue(projectRepositoryPageService.isSuiteByNamePresent(suiteName));
    }
}
