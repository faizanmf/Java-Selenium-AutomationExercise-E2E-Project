package com.utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentLogger {

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void info(String message) {
        extentTest.get().info(message);
    }

    public static void pass(String message) {
        extentTest.get().pass(message);
    }

    public static void warning(String message) {
        extentTest.get().warning(message);
    }

    public static void fail(String message) {
        extentTest.get().fail(message);
    }

    public static void infoWithScreenshot(String message) {
        extentTest.get().info(message);
        String screenshotPath = ScreenshotUtils.captureScreenshot(message.replaceAll("\\s","_"));
        if (screenshotPath != null) {
            extentTest.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

}
