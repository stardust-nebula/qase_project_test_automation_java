package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.User;
import page.LoginPage;
import page.ProjectsPage;

import static util.StringConstant.LOGIN_PAGE_URL;

@Log4j2
public class LoginPageService {
    private LoginPage loginPage = new LoginPage();

    @Step("Authorization with valid credentials")
    public ProjectsPage loginValidCredentials(User user) {
        log.info("Login as user " + user.getEmail());
        loginPage.openPage(LOGIN_PAGE_URL)
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickLoginButton();
        return new ProjectsPage();
    }

    @Step("Authorization with invalid credentials")
    public LoginPage loginInvalidCredentials(User user) {
        log.info("Login as user " + user.getEmail());
        loginPage.openPage(LOGIN_PAGE_URL)
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickLoginButton();
        return loginPage;
    }
}
