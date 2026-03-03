package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupLoginPage extends ActionUtils {

    WebDriver driver;


    public SignupLoginPage()
    {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement Name;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement Email;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signup;

    public void enterDetails(String name, String email)
    {
        Name.sendKeys(name);
        Email.sendKeys(email);
        signup.click();
    }
}
