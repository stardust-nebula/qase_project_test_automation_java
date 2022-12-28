package ui.service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.Suite;
import ui.page.CreateNewSuiteDialog;
import ui.page.ProjectRepositoryPage;

@Log4j2
public class CreateNewSuiteDialogService {

    private ProjectRepositoryPageService projectRepositoryPageService = new ProjectRepositoryPageService();
    private CreateNewSuiteDialog createNewSuiteDialog = new CreateNewSuiteDialog();

    @Step("Open 'Add Suite' dialog")
    public CreateNewSuiteDialog openCreateNewSuiteDialog() {
        log.info("Open 'Add Suite' dialog");
        projectRepositoryPageService.clickOnAddSuiteButton();
        return new CreateNewSuiteDialog();
    }

    @Step("FillInSuiteDataAndCreate")
    public ProjectRepositoryPage fillSuiteDataCreate(Suite suite) {
        log.info("Create new suite");
        createNewSuiteDialog
                .enterSuiteName(suite.getSuiteName())
                .enterDescription(suite.getDescription())
                .enterPreconditions(suite.getPreconditions())
                .clickCreateButton();
        return new ProjectRepositoryPage();
    }
}
