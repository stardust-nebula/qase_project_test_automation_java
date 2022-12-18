package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

import static util.WaitTimeout.IMPLICIT_WAIT_TIMEOUT_SECONDS;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
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
        }
        return driver;
    }

    public static void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
