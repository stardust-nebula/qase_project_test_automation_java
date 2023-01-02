package io.qase.tests.ui;

import io.qase.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qase.service.LoginPageService;
import io.qase.service.ProjectSettingsPageService;
import io.qase.service.ProjectsPageService;
import io.qase.util.Retry;

public class ProjectSettingsPageTest extends BaseTest {

    private ProjectSettingsPageService projectSettingsPageService;

    @BeforeClass
    public void setUp() {
        String demoProjectName = "Demo Project";
        new LoginPageService().loginValidCredentials(new User());
        new ProjectsPageService()
                .clickOnMeatballsIconForProjectByName(demoProjectName)
                .clickOnSettingsOptionByProject(demoProjectName);
        projectSettingsPageService = new ProjectSettingsPageService();
    }

    @Test(testName = "Success alert appears on successful changing project's description",
            description = "Verify success alert appears on successful changing project's description",
            retryAnalyzer = Retry.class)
    public void verifySuccessAlertOnChangingProjectDescriptionTest() {
        String newDescription = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.";
        String expectedSuccessText = "Project settings were successfully updated!";
        projectSettingsPageService.changeProjectSettingsAndSave(newDescription);
        String actualSuccessText = projectSettingsPageService.getTextFromUpdateProjectSettingsSuccessAlert();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(projectSettingsPageService.isAlertOnSuccessfulProjectUpdateAppears());
        softAssert.assertEquals(actualSuccessText, expectedSuccessText);
        softAssert.assertAll();
    }
}
