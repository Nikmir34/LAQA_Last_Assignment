package UI.Test;

import UI.Pages.AuthorizationPage;
import UI.Pages.DbPage;
import UI.Pages.GeneratorPage;
import UI.Pages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static AuthorizationPage authorizationPage;
    public static MainPage mainPage;
    public static DbPage dbPage;
    public static GeneratorPage generatorPage;
    public static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        authorizationPage = new AuthorizationPage(driver);
        dbPage = new DbPage(driver);
        generatorPage = new GeneratorPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
