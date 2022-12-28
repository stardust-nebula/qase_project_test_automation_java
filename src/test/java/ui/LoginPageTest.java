package ui;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.service.HeaderGlobalComponentService;
import ui.service.LoginPageService;

public class LoginPageTest extends BaseTest {

    private LoginPageService loginPageService;
    private HeaderGlobalComponentService headerGlobalComponentService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        headerGlobalComponentService = new HeaderGlobalComponentService();
    }

    @Test(testName = "Verify successful authorization with valid credentials")
    public void verifySuccessfulAuthorizationValidCredentialsTest() {
        User user = new User();
        String expectedMainPageTitle = "Projects";
        String actualMainPageTitle = loginPageService
                .loginValidCredentials(user)
                .getPageTitleText();
        headerGlobalComponentService.signOutFromAccount();
        Assert.assertEquals(actualMainPageTitle, expectedMainPageTitle);
    }

    @Test(testName = "Verify unsuccessful authorization with invalid credentials")
    public void verifyUnsuccessfulAuthorizationInvalidCredentialsTest() {
        User user = new User("email@email.con", "password");
        boolean isLoginErrorMessageDisplays = loginPageService
                .loginInvalidCredentials(user)
                .isLoginErrorMessageDisplays();
        Assert.assertTrue(isLoginErrorMessageDisplays);
    }
}
