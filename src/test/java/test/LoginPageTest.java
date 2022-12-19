package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.LoginPageService;

public class LoginPageTest extends BaseTest {

    private LoginPageService loginPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
    }

    @Test
    public void verifySuccessfulAuthorizationValidCredentialsTest() {
        User user = new User();
        String expectedMainPageTitle = "Projects";
        String actualMainPageTitle = loginPageService
                .loginValidCredentials(user)
                .getPageTitleText();
        Assert.assertEquals(actualMainPageTitle, expectedMainPageTitle);
    }

    @Test
    public void verifyUnsuccessfulAuthorizationInvalidCredentials() {
        User user = new User("email@email.con", "password");
        boolean isLoginErrorMessageDisplays = loginPageService
                .loginInvalidCredentials(user)
                .isLoginErrorMessageDisplays();
        Assert.assertTrue(isLoginErrorMessageDisplays);
    }
}
