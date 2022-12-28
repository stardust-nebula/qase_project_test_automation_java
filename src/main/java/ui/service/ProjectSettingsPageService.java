package ui.service;

import io.qameta.allure.Step;
import ui.page.ProjectSettingsPage;

public class ProjectSettingsPageService {

    private ProjectSettingsPage projectSettingsPage = new ProjectSettingsPage();

    @Step("Change Project information and save")
    public ProjectSettingsPage changeProjectSettingsAndSave(String newDescription) {
        projectSettingsPage
                .enterNewDescription(newDescription)
                .clickOnUpdateSettingsButton();
        return new ProjectSettingsPage();
    }

    public boolean isAlertOnSuccessfulProjectUpdateAppears() {
        return projectSettingsPage.isAlertOnSuccessfulProjectUpdateAppears();
    }

    public String getTextFromUpdateProjectSettingsSuccessAlert() {
        return projectSettingsPage.getTextFromUpdateProjectSettingsSuccessAlert();
    }
}
