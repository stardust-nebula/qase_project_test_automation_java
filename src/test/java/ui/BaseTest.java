package ui;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ui.driver.DriverSingleton;

@Log4j2
public class BaseTest {
    protected WebDriver driver;

    @BeforeClass(description = "Opening browser")
    public void openBrowser() {
        log.info("Opening browser");
        driver = DriverSingleton.getInstance().getDriver();
    }

    @AfterClass(alwaysRun = true, description = "Closing browser")
    public void closeBrowser() {
        log.info("Closing browser");
        DriverSingleton.getInstance().closeBrowser();
    }
}
