package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDeletedPage extends ActionUtils
{
    WebDriver driver;

    public AccountDeletedPage()
    {
        this.driver= DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    private WebElement accountDeletedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueBtn;

    // Verify Account Deleted Message
    public boolean isAccountDeletedMessageDisplayed() {
       ActionUtils.waitTillElementVisible(accountDeletedMessage); // from ActionUtils (assuming)
        String msg = accountDeletedMessage.getText().trim();
        return msg.equalsIgnoreCase("Account Deleted!");
    }

    // Click Continue Button
    public void clickContinue() {
        waitForClickable(continueBtn); // recommended
        continueBtn.click();
    }

}
