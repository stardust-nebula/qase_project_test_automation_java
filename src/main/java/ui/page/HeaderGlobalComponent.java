package ui.page;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.util.AllureUtils;

@Log4j2
public class HeaderGlobalComponent extends BasePage{

    @FindBy(xpath = "//span/img[contains(@src,'.jpg')]")
    private WebElement userProfileIcon;

    @FindBy(xpath = "//span[contains(text(),'Sign out')]")
    private WebElement signOutOptionElement;

    @Step("Click on the user's profile icon in the header")
    public HeaderGlobalComponent clickOnUserProfileIcon(){
        log.info("Click on the user's profile icon in the header");
        waitVisibilityOfElement(userProfileIcon).click();
        AllureUtils.takeScreenshot(driver);
        return new HeaderGlobalComponent();
    }

    @Step("Click on the Sign out")
    public void clickSignOutOption(){
        log.info("Click on the Sign out");
        waitVisibilityOfElement(signOutOptionElement).click();
        AllureUtils.takeScreenshot(driver);
    }
}
