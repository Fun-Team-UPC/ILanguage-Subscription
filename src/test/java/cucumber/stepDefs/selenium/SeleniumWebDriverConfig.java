package cucumber.stepDefs.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWebDriverConfig {
    public WebDriver driver;
    public static String API_URL = "http://localhost:4200/home";
    public static String PATH_DRIVER ;

    public SeleniumWebDriverConfig() {
        String PATH_DRIVER = System.getProperty("user.dir") + "\\driver\\windows\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", PATH_DRIVER);
        driver = new ChromeDriver();
        driver.get(API_URL);
        driver.manage().window().fullscreen();
    }
}
