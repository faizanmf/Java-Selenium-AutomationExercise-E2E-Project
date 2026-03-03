package base;

import com.driver.DriverFactory;
import com.driver.DriverManager;
import com.pages.PageManager;
import com.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(com.listeners.ExtentTestListener.class)
public class BaseTest {

    protected PageManager page;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {

        WebDriver driver = DriverFactory.createInstance(browser);
        DriverManager.setDriver(driver);

        DriverManager.getDriver().get(ConfigReader.get("base.url"));
        page = new PageManager();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
