package io.qase.tests.ui;

import io.qase.util.GenerateNames;
import io.qase.model.Project;
import io.qase.model.Suite;
import io.qase.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qase.service.CreateNewProjectService;
import io.qase.service.CreateNewSuiteDialogService;
import io.qase.service.LoginPageService;
import io.qase.service.ProjectRepositoryPageService;
import io.qase.util.Retry;

public class CreateNewSuiteDialogTest extends BaseTest {

    private static final int PREFIX_PROJECT_NAME_LENGTH = 5;
    private static final int PREFIX_PROJECT_CODE_LENGTH = 3;
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
        String projectCode = GenerateNames.generateRandomString(PREFIX_PROJECT_CODE_LENGTH).toUpperCase();
        String projectName = GenerateNames.generateTestProjectNameWithCurrentDate(PREFIX_PROJECT_NAME_LENGTH);
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
