package api;

import api.service.ProjectApiService;
import api.service.SuiteApiService;
import api.service.TestCaseApiService;
import io.restassured.response.Response;
import model.Project;
import model.Suite;
import model.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.service.CreateNewProjectService;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class ApiTest {

    private CreateNewProjectService createNewProjectService;
    private static final int prefixProjectCodeLength = 3;
    private static final int prefixProjectNameLength = 5;

    private String projectCode;
    private String projectName;
    private Project project;

    @BeforeClass
    public void setUpData() {
        createNewProjectService = new CreateNewProjectService();
        projectName = "Automation QA API " + createNewProjectService.generateTestProjectNameWithCurrentDate(prefixProjectNameLength);
        projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
    }

    @Test(testName = "Verify Status Code = 200 on getting all projects")
    public void getAllProjectsTest() {
        int statusCode = new ProjectApiService().getAllProjects().statusCode();
        Assert.assertEquals(statusCode, HTTP_OK);
    }

    @Test(testName = "Verify project's code once project is created")
    public void createNewProjectTest() {
        String pathToCodeInResponse = "result.code";
        String actualCode = new ProjectApiService().createProject(project).body().path(pathToCodeInResponse);
        Assert.assertEquals(actualCode, projectCode);
    }

    @Test(testName = "Verify getting 'true' status on successful creating new suite")
    public void createNewSuiteTest() {
        String suiteName = "Regression";
        String pathToStatusInResponse = "status";
        Suite suite = Suite.builder()
                .suiteName(suiteName)
                .build();
        new ProjectApiService().createProject(project);
        boolean status = new SuiteApiService().createSuite(projectCode, suite).body().path(pathToStatusInResponse);
        Assert.assertTrue(status);
    }

    @Test
    public void verifySuccessfulUpdatingSuiteDescriptionTest() {
        String suiteName = "Smoke Test Suite";
        String newSuiteDescription = " New description for the suite";
        String pathToSuiteIdInResponse = "result.id";
        String pathToStatusInResponse = "status";
        Suite suite = Suite.builder()
                .suiteName(suiteName)
                .build();
        new ProjectApiService().createProject(project);
        int suiteId = new SuiteApiService().createSuite(projectCode, suite).body().path(pathToSuiteIdInResponse);
        Suite.builder()
                .description(newSuiteDescription)
                .build();
        boolean status = new SuiteApiService().updateSuite(projectCode, suiteId, suite).body().path(pathToStatusInResponse);
        Assert.assertTrue(status);
    }

    @Test(testName = "Verify Status code = 404 on second try deleting previously existed test case")
    public void verifyStatusCodeOnSecondDeletingTestCaseTes() {
        String testCaseIdPathInResponse = "result.id";
        String testCaseErrorMessagePathInResponse = "errorMessage";
        String testCaseName = "Test to check second deleting";
        String expectedErrorMessage = "TestCase not found";
        TestCase testCase = TestCase.builder()
                .title(testCaseName)
                .build();
        new ProjectApiService().createProject(project);
        int testCaseId = new TestCaseApiService().creteNewTestCase(projectCode, testCase).body().path(testCaseIdPathInResponse);
        new TestCaseApiService().deleteSpecificTestCase(projectCode, testCaseId);
        Response response = new TestCaseApiService().deleteSpecificTestCase(projectCode, testCaseId);
        int actualStatusCode = response.statusCode();
        String actualErrorMessage = response.body().path(testCaseErrorMessagePathInResponse);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualStatusCode, HTTP_NOT_FOUND, "Status code is NOT 404");
        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message doesn't match");
        softAssert.assertAll();
    }

}
