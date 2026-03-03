package com.utils;

import com.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String path = ConfigReader.get("screenshot.path")
                    + testName + ".png";
            File dest = new File(path);

            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());

            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
