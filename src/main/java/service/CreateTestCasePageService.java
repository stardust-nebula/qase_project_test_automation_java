package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.TestCase;
import page.CreateTestCasePage;
import page.ProjectRepositoryPage;

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
