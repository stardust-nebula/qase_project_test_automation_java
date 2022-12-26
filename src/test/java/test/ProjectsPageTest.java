package test;

import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.CreateNewProjectService;
import service.LoginPageService;
import service.ProjectRepositoryPageService;
import service.ProjectsPageService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectsPageTest extends BaseTest {

    private LoginPageService loginPageService;
    private ProjectsPageService projectsPageService;
    private CreateNewProjectService createNewProjectService;
    private ProjectRepositoryPageService projectRepositoryPageService;
    private static User user = new User();
    private static Project project = new Project();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private static int prefixProjectNameLength = 5;
    private static int prefixProjectCodeLength = 3;


    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectsPageService = new ProjectsPageService();
        createNewProjectService = new CreateNewProjectService();
        projectRepositoryPageService = new ProjectRepositoryPageService();
        loginPageService.loginValidCredentials(user);
    }

    @Test
    public void verifySuccessfulSearchExistingProjectTest() {
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
        projectsPageService.openProjectsPage();
        projectsPageService.enterSearchCriteriaInSearchField(projectName);
        boolean isProjectLocated = projectsPageService.isProjectByNamePresentOnPage(projectName);
        Assert.assertTrue(isProjectLocated);
    }
}
