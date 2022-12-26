package test;

import model.Project;
import model.TestCase;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.CreateTestCasePageService;
import service.LoginPageService;
import service.ProjectRepositoryPageService;
import service.ProjectsPageService;

public class CreateTestCasePageTest extends BaseTest {

    private static final String demoProjectName = "Demo Project";
    private static final String demoProjectCode = "DEMO";
    private static Project project;
    private ProjectRepositoryPageService projectRepositoryPageService;
    private CreateTestCasePageService createTestCasePageService;


    @BeforeClass
    public void setUp() {
        new LoginPageService().loginValidCredentials(new User());
        new ProjectsPageService().clickOnProjectNameInTable(demoProjectName);
        projectRepositoryPageService = new ProjectRepositoryPageService();
        createTestCasePageService = new CreateTestCasePageService();
        project = Project.builder().projectName(demoProjectName).projectCode(demoProjectCode).build();
    }

    @Test
    public void verifyAlertOnSuccessfulCreationOfTestCase(){
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
