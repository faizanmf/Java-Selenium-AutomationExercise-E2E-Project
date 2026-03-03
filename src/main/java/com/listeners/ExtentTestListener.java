package com.listeners;

import com.aventstack.extentreports.*;
import com.utils.ExtentLogger;
import com.utils.ExtentManager;
import com.utils.ScreenshotUtils;
import org.testng.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ExtentTestListener implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getExtentReport();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        ExtentLogger.setTest(extentTest);

        extentTest.info("Thread Name : " + Thread.currentThread().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        int invocation = result.getMethod().getCurrentInvocationCount();

        if (invocation > 1) {
            test.get().warning("Test passed after retry attempt : " + (invocation - 1));
        }

        // ✅ Step screenshot optional: only for success if needed
        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName() + "_PASS");
        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        int invocation = result.getMethod().getCurrentInvocationCount();

        if (invocation <= 1) {
            test.get().warning("Retrying test due to failure...");
        } else {
            test.get().fail("Test Failed after retry");
        }

        test.get().fail(result.getThrowable());

        // 📸 Capture screenshot for failures
        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName() + "_FAIL");
        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().warning("Test Skipped (Retry in progress)");

        // Optional screenshot for skipped tests
        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName() + "_SKIPPED");
        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // 📊 Retry Statistics Summary
        ExtentTest summary = extent.createTest("🔁 Retry Summary");

        for (Map.Entry<String, AtomicInteger> entry : RetryAnalyzer.getRetryStats().entrySet()) {
            summary.info(
                    entry.getKey() + " → Retry Count : " + Math.max(0, entry.getValue().get() - 1)
            );
        }

        extent.flush();
    }
}
