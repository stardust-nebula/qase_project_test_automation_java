package ui;

import model.Project;
import model.Suite;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewSuiteDialogTest extends BaseTest {

    private static int prefixProjectNameLength = 5;
    private static int prefixProjectCodeLength = 3;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private LoginPageService loginPageService;
    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private ProjectRepositoryPageService projectRepositoryPageService;
    private CreateNewSuiteDialogService createNewSuiteDialogService;
    private static User user = new User();

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectsPageService = new ProjectsPageService();
        createNewProjectService = new CreateNewProjectService();
        projectRepositoryPageService = new ProjectRepositoryPageService();
        createNewSuiteDialogService = new CreateNewSuiteDialogService();
        loginPageService.loginValidCredentials(user);
    }

    @Test
    public void verifySuccessfulNewSuiteCreationTest() {
        String suiteName = "Regression";
        Suite suite = Suite.builder()
                .suiteName(suiteName)
                .build();
        Date date = new Date(System.currentTimeMillis());
        String dateForName = simpleDateFormat.format(date);
        String prefixProjectName = createNewProjectService.generateRandomString(prefixProjectNameLength);
        String projectCode = createNewProjectService.generateRandomString(prefixProjectCodeLength).toUpperCase();
        String projectName = prefixProjectName + " current date" + dateForName;
        Project project = Project.builder()
                .projectName(projectName)
                .projectCode(projectCode)
                .build();
        createNewProjectService.openCreateNewProjectDialog();
        createNewProjectService.fillInProjectInfoCreate(project);
        projectRepositoryPageService.clickOnAddSuiteButton();
        createNewSuiteDialogService.fillSuiteDataCreate(suite);
        Assert.assertTrue(projectRepositoryPageService.isSuiteByNamePresent(suiteName));
    }


}
