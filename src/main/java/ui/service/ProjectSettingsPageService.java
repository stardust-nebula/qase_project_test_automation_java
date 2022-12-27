package ui.service;

import io.qameta.allure.Step;
import ui.page.ProjectSettingsPage;

public class ProjectSettingsPageService {

    private ProjectSettingsPage projectSettingsPage = new ProjectSettingsPage();
    private ProjectsPageService projectsPageService = new ProjectsPageService();

    @Step("Open Project settings page by project name")
    public ProjectSettingsPage openProjectSettingsPageByProjectName(String projectName) {
        projectsPageService
                .clickOnMeatballsIconForProjectByName(projectName)
                .clickOnSettingsOptionByProject(projectName);
        return new ProjectSettingsPage();
    }

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
