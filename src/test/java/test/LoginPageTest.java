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
                .login(user)
                .getPageTitleText();
        Assert.assertEquals(actualMainPageTitle, expectedMainPageTitle);
    }
}
