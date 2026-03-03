package com.utils;

import com.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionUtils {

    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    private static JavascriptExecutor js() {
        return (JavascriptExecutor) getDriver();
    }

    private static Actions actions() {
        return new Actions(getDriver());
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    }

    // -------- Actions --------

    public static void scroll() {
        js().executeScript("window.scrollBy(0,300);");
    }

    public static void scrollToElement(WebElement element) {
        js().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void hover(WebElement element) {
        actions()
                .moveToElement(element)
                .pause(Duration.ofSeconds(2))
                .perform();
    }

    public static void waitTillElementDisplayed(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitTillElementVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void clickElementJS(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        js().executeScript("arguments[0].click();", element);
    }

    public static void waitForClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeInvisible(WebElement element)
    {
        getWait().until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public static void scrollWithKeyboard() {
        actions().sendKeys(Keys.ARROW_DOWN).perform();
    }
}
