package io.qase.service;

import io.qameta.allure.Step;
import io.qase.page.CreateNewSuiteDialog;
import lombok.extern.log4j.Log4j2;
import io.qase.model.Suite;
import io.qase.page.ProjectRepositoryPage;

@Log4j2
public class CreateNewSuiteDialogService {

    private CreateNewSuiteDialog createNewSuiteDialog = new CreateNewSuiteDialog();

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
    // Local comment 2
}
