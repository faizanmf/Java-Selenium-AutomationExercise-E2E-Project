package com.driver;

import com.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.network.Network;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DriverFactory {

    public static WebDriver createInstance(String browser) {

        if (browser == null) {
            browser = ConfigReader.get("browser");
        }

        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--incognito");

                options.addArguments("--headless=new");

                driver = new ChromeDriver(options);

                // ✅ BLOCK GOOGLE ADS USING DEVTOOLS
                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();

                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

                devTools.send(Network.setBlockedURLs(Arrays.asList(
                        "*doubleclick.net*",
                        "*googlesyndication.com*",
                        "*googleads.g.doubleclick.net*"
                )));

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        return driver;
    }
}
