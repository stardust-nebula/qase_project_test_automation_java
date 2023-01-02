package io.qase.tests.ui;

import io.qase.model.TestCase;
import io.qase.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qase.service.CreateTestCasePageService;
import io.qase.service.LoginPageService;
import io.qase.service.ProjectRepositoryPageService;
import io.qase.service.ProjectsPageService;
import io.qase.util.Retry;

public class CreateTestCasePageTest extends BaseTest {

    private static final String DEMO_PROJECT_NAME = "Demo Project";
    private ProjectRepositoryPageService projectRepositoryPageService;
    private CreateTestCasePageService createTestCasePageService;

    @BeforeClass
    public void setUp() {
        new LoginPageService().loginValidCredentials(new User());
        new ProjectsPageService().clickOnProjectNameInTable(DEMO_PROJECT_NAME);
        projectRepositoryPageService = new ProjectRepositoryPageService();
        createTestCasePageService = new CreateTestCasePageService();
    }

    @Test(testName = "Alert on successful test case creation",
            description = "Verify that alert on successful test case creation",
            retryAnalyzer = Retry.class)
    public void verifyAlertOnSuccessfulCreationOfTestCaseTest() {
        String expectedSuccessMessage = "Test case was created successfully!";
        String testCaseTitle = "Login with valid credentials";
        TestCase testCase = TestCase.builder()
                .title(testCaseTitle)
                .build();
        projectRepositoryPageService.clickOnAddCaseButton();
        createTestCasePageService
                .fillTestCaseDataAndSave(testCase);
        boolean isAlertDisplays = projectRepositoryPageService.isAlertOnTestCaseCreatedSuccessfullyAppears();
        String actualText = projectRepositoryPageService.getTextFromCreateTestCaseSuccessAlert();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isAlertDisplays);
        softAssert.assertEquals(actualText, expectedSuccessMessage);
        softAssert.assertAll();
    }
}
