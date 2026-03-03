package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountInfoPage extends ActionUtils {

    WebDriver driver;
    // Title
    @FindBy(id = "id_gender1")
    private WebElement titleMr;
    @FindBy(id = "id_gender2")
    private WebElement titleMrs;
    // Account Information
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "days")
    private WebElement days;
    @FindBy(id = "months")
    private WebElement months;
    @FindBy(id = "years")
    private WebElement years;
    // Checkboxes
    @FindBy(id = "newsletter")
    private WebElement newsletter;
    @FindBy(id = "optin")
    private WebElement optin;
    // Address Information
    @FindBy(id = "first_name")
    private WebElement firstName;
    @FindBy(id = "last_name")
    private WebElement lastName;
    @FindBy(id = "company")
    private WebElement company;
    @FindBy(id = "address1")
    private WebElement address1;
    @FindBy(id = "address2")
    private WebElement address2;
    @FindBy(id = "country")
    private WebElement country;
    @FindBy(id = "state")
    private WebElement state;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "zipcode")
    private WebElement zipcode;
    @FindBy(id = "mobile_number")
    private WebElement mobileNumber;
    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountBtn;

    public AccountInfoPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void fillAccountDetails(
            String title,
            String pass,
            String day,
            String month,
            String year,
            String fName,
            String lName,
            String companyName,
            String addr1,
            String addr2,
            String countryName,
            String stateName,
            String cityName,
            String zip,
            String mobile
    ) {

        // Select title
        if (title.equalsIgnoreCase("Mr")) {
            titleMr.click();
        } else {
            titleMrs.click();
        }

        password.sendKeys(pass);

        // Select DOB dropdowns
        new Select(days).selectByVisibleText(day);
        new Select(months).selectByVisibleText(month);
        new Select(years).selectByVisibleText(year);

        // Checkboxes
        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newsletterCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletterCheckbox);


        optin.click();

        // Address section
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        company.sendKeys(companyName);
        address1.sendKeys(addr1);
        address2.sendKeys(addr2);

        new Select(country).selectByVisibleText(countryName);

        state.sendKeys(stateName);
        city.sendKeys(cityName);
        zipcode.sendKeys(zip);
        mobileNumber.sendKeys(mobile);
    }

    public void clickCreateAccountBtn() {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll into view
        js.executeScript("arguments[0].scrollIntoView(true);", createAccountBtn);

        // Wait until clickable (Selenium-level)
        wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn));

        // Click via JS (bypasses iframe ads)
        js.executeScript("arguments[0].click();", createAccountBtn);
    }

}



