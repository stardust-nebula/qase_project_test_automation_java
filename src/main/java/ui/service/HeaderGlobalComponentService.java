package ui.service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.page.HeaderGlobalComponent;
import ui.page.LoginPage;

@Log4j2
public class HeaderGlobalComponentService {

    private HeaderGlobalComponent headerGlobalComponent = new HeaderGlobalComponent();

    @Step("Sign out from the user's account")
    public LoginPage signOutFromAccount() {
        log.info("Sign out form the account");
        headerGlobalComponent
                .clickOnUserProfileIcon()
                .clickSignOutOption();
        return new LoginPage();
    }
}
