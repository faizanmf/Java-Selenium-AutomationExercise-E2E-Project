package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage extends ActionUtils {

    WebDriver driver;


    public AccountCreatedPage()
    {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    private WebElement accountCreatedHeading;

    @FindBy(xpath = "//p[contains(text(),'successfully created')]")
    private WebElement accountCreatedMsg;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueBtn;

    // Verify heading
    public boolean verifyAccountCreatedHeading() {
        return accountCreatedHeading.isDisplayed();
    }

    // Verify message
    public boolean verifyAccountCreatedMessage() {
        return accountCreatedMsg.isDisplayed();
    }

    // Click Continue Button
    public void clickContinue() {
        continueBtn.click();
    }
}
