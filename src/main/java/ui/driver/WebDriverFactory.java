package ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

import static ui.util.WaitTimeout.IMPLICIT_WAIT_TIMEOUT_SECONDS;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver getWebDriver() {
        WebDriver driver = null;
        switch (System.getProperty("browser", "chrome")) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case "safari": {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT_SECONDS));
        return driver;
    }
}
