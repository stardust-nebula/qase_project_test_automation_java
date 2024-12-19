package io.qase.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qase.util.AllureUtils;

@Log4j2
public class HeaderGlobalComponent extends BasePage{

    @FindBy(xpath = "//span/img[contains(@src,'.jpg')]")
    private WebElement userProfileIcon;

    @FindBy(xpath = "//span[contains(text(),'Sign out')]")
    private WebElement signOutOptionElement;

    @Step("Click on the user's profile icon in the header")
    public HeaderGlobalComponent clickOnUserProfileIcon(){
        log.info("Click on the user's profile icon in the header");
        waitElementToBeClickable(userProfileIcon).click();
        AllureUtils.takeScreenshot(driver);
        return new HeaderGlobalComponent();
        // Commit 3000
    }

    @Step("Click on the Sign out")
    public void clickSignOutOption(){
        log.info("Click on the Sign out");
        waitElementToBeClickable(signOutOptionElement).click();
        AllureUtils.takeScreenshot(driver);
    }
}
