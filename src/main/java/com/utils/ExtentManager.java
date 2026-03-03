package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReport() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // 🌐 Environment Info
            extent.setSystemInfo("Tester", "Faizan");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Environment", ConfigReader.get("env"));
        }
        return extent;
    }
}
