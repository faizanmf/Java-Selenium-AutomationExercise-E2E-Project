package com.pages;

import com.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLink;

    @FindBy(xpath = "//div[@class='login-form']//h2[1]")
    private WebElement loginheader;

    @FindBy(xpath = "(//input[@name='email'])[1]")
    private WebElement emailBox;

    @FindBy(name = "password")
    private WebElement passwordBox;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[normalize-space(text())='Logout']")
    public WebElement logoutBtn;

    @FindBy(xpath = "//p[normalize-space(text())='Your email or password is incorrect!']")
    private WebElement errorMsg;

    @FindBy(xpath = "//h2[normalize-space(text())='New User Signup!']")
    private WebElement newSignup;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement signupName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmail;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupBtn;

    @FindBy(xpath = "//p[normalize-space(text())='Email Address already exist!']")
    private WebElement emailExist;


    public boolean clickLoginBtn()
    {
        loginLink.click();
        return loginheader.isDisplayed();

    }

    public void login(String email, String pass)
    {
        emailBox.sendKeys(email);
        passwordBox.sendKeys(pass);
        loginBtn.click();

    }

    public boolean errorValidation()
    {
        return errorMsg.isDisplayed();
    }

    public boolean newSignup()
    {
        return newSignup.isDisplayed();
    }

    public void entersignupDetails(String name, String email)
    {
        signupName.sendKeys(name);
        signupEmail.sendKeys(email);
        signupBtn.click();
    }

    public boolean alreadyExistEmail()
    {
        return emailExist.isDisplayed();
    }












}
