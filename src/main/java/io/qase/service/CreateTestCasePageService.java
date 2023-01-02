package io.qase.service;

import io.qameta.allure.Step;
import io.qase.model.TestCase;
import io.qase.page.CreateTestCasePage;
import io.qase.page.ProjectRepositoryPage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CreateTestCasePageService {
    private CreateTestCasePage createTestCasePage = new CreateTestCasePage();

    @Step("Fill in test case data and save")
    public ProjectRepositoryPage fillTestCaseDataAndSave(TestCase testCase) {
        log.info("Fill in test case data and save");
        createTestCasePage
                .enterTestCaseTitle(testCase.getTitle())
                .clickOnSaveButton();
        return new ProjectRepositoryPage();
    }
}
