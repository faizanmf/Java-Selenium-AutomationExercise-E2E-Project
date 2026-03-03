package com.pages;

import com.driver.DriverManager;
import com.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactusPage {

    WebDriver driver;

    public ContactusPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[normalize-space(text())='Get In Touch']")
    private WebElement getInTouch;

    @FindBy(xpath = "//input[@data-qa='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@data-qa='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@data-qa='subject']")
    private WebElement subject;

    @FindBy(xpath = "//textarea[@data-qa='message']")
    private WebElement message;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement file;

    @FindBy(xpath = "//input[@data-qa='submit-button']")
    private WebElement submit;

    public boolean verifyheader()
    {
        return getInTouch.isDisplayed();
    }

    public void enterDetails(String nme, String mail, String sub, String msg)
    {
        name.sendKeys(nme);
        email.sendKeys(mail);
        subject.sendKeys(sub);
        message.sendKeys(msg);
    }

    public void fileupload()
    {
        file.sendKeys(ConfigReader.get("FileUpload"));
    }

    public void clickSubmit()
    {
        submit.click();
    }


















}
