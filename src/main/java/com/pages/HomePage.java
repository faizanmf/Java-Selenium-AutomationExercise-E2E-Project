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
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class HomePage   {
    WebDriver driver;
    @FindBy(xpath = "//a[normalize-space(text())='Contact us']")
    private WebElement contactUs;
    @FindBy(xpath = "//a[normalize-space(text())='Test Cases']")
    private WebElement testcaseBtn;
    @FindBy(xpath = "(//ul[@class='nav navbar-nav']//a)[2]")
    private WebElement productBtn;
    @FindBy(xpath = "//h2[normalize-space(text())='Subscription']")
    private WebElement subscriptionTxt;

    @FindBy(id = "susbscribe_email")
    private WebElement subscriptionEmailField;

    @FindBy(id = "subscribe")
    private WebElement subscribeBtn;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    private WebElement subscriptionSuccessMessage;

    @FindBy(xpath = "//a[normalize-space(text())='Cart']")
    private WebElement cartBtn;

    @FindBy(xpath = "//i[@class='fa fa-user']/parent::a")
    private WebElement loggedInText;

    @FindBy(xpath = "//a[@href='/delete_account']")
    private WebElement deleteAccountBtn;

    @FindBy(xpath = "//a[normalize-space(text())='Signup / Login']")
    private WebElement signupLoginBtn;

    @FindBy(xpath = "//u[normalize-space(text())='View Cart']")
    private WebElement viewCartLink;

    @FindBy(xpath = "//button[normalize-space(text())='Continue Shopping']")
    private WebElement continueShopping;

    @FindBy(xpath = "//h2[normalize-space(text())='Category']")
    private WebElement categoriesTxt;

    @FindBy(xpath = "//h2[normalize-space(text())='recommended items']")
    private WebElement recommendedItems;

    @FindBy(xpath = "//a[@id='scrollUp']/i")
    private WebElement scrollUpIndicator;

    @FindBy(xpath = "//a[normalize-space(text())='Home']")
    private WebElement homeBtn;

    @FindBy(xpath = "(//h2[normalize-space(.)='Full-Fledged practice website for Automation Engineers'])[1]")
    private WebElement AutomationEngineersTxt;


    public HomePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

//    //public HomePage()
//    {
//        super();
//    }

    public void clickContactUs() {
        contactUs.click();
    }

    public void clickTestCase() {
        testcaseBtn.click();
    }

    public void clickProductBtn() {
        productBtn.click();
    }

    public boolean verifySubscription()
    {
        ActionUtils.scrollToElement(subscriptionTxt);
        return subscriptionTxt.isDisplayed();
    }

    public boolean enterSubscribeEmail(String email)
    {
        subscriptionEmailField.sendKeys(email);
        subscribeBtn.click();
       return subscriptionSuccessMessage.isDisplayed();

    }

    public void clickCartBtn()
    {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(cartBtn)
        );

        element.click();
    }

    public WebElement getViewButton(String productName) {
        ActionUtils.scroll();
        return driver.findElement(By.xpath("(//p[contains(text(),'"+productName+"')]/following::div[@class='choose'])[1]"));
    }

    public void clickviewProduct(String productName)
    {
        ActionUtils.scrollToElement(getViewButton(productName));
        ActionUtils.waitTillElementVisible(getViewButton(productName));
        getViewButton(productName).click();
    }

    public String priceOfProduct(String productName)
    {
        ActionUtils.scroll();
        return driver.findElement(By.xpath("(//div[@class='productinfo text-center']/p[contains(text(),'"+productName+"')]/parent::div/h2)[1]")).getText();
    }

    public String getLoggedInUser() {
        return loggedInText.getText().replace("Logged in as", "").trim();
    }

    public boolean verifyLoggedInUser(String expectedName) {
        return getLoggedInUser().equalsIgnoreCase(expectedName);
    }

    public void clickDeleteAccount() {
        deleteAccountBtn.click();
    }

    public void clickSignupLogin()
    {
        signupLoginBtn.click();
    }

    public void addProduct(String proName)
    {
        ActionUtils.scrollToElement(driver.findElement(By.xpath("(//div[@class='productinfo text-center']/p[contains(text(),'"+proName+"')]/parent::div/a)[1]")));
        ActionUtils.waitForClickable(driver.findElement(By.xpath("(//div[@class='productinfo text-center']/p[contains(text(),'"+proName+"')]/parent::div/a)[1]")));
        driver.findElement(By.xpath("(//div[@class='productinfo text-center']/p[contains(text(),'"+proName+"')]/parent::div/a)[1]")).click();
    }

    public void clickViewCart()
    {
        ActionUtils.waitTillElementVisible(viewCartLink);
        viewCartLink.click();
    }

    public void clickContinueShopping()
    {
        ActionUtils.waitForClickable(continueShopping);
        continueShopping.click();
    }

    public boolean verifyCategories()
    {
        ActionUtils.scrollToElement(categoriesTxt);
       return categoriesTxt.isDisplayed();
    }

    public void expandCategoryAndSelect(String name, String item)
    {
       WebElement expand = driver.findElement(By.xpath("//h4[contains(.,'"+name+"')]//i"));
       ActionUtils.waitForClickable(expand);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", expand);
       WebElement element = driver.findElement(By.xpath("//div[@id='"+name+"']//a[normalize-space()='"+item+"']"));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);


    }

    public String verifyBannerTitle(String name, String item) {

        By titleLocator = By.xpath(String.format(
                "//div[@class='breadcrumbs']//li[normalize-space()='%s > %s']",
                name,
                item
        ));

        ActionUtils.waitTillElementDisplayed(titleLocator);

        return DriverManager.getDriver()
                .findElement(titleLocator)
                .getText()
                .trim();
    }

    public String verifyFilterTitle(String item) {

        By titleLocator = By.xpath(String.format(
                "//h2[@class='title text-center' and contains(normalize-space(),'%s')]",
                item
        ));

        ActionUtils.waitTillElementDisplayed(titleLocator);

        return DriverManager.getDriver()
                .findElement(titleLocator)
                .getText()
                .trim();
    }


    public boolean scrollToRecommendedItems()
    {
        ActionUtils.scrollToElement(recommendedItems);
       return recommendedItems.isDisplayed();
    }

    private WebElement getDynamicRecommendedProduct(String ProName)
    {
       return driver.findElement(By.xpath("//div[@class='recommended_items']//p[normalize-space()='"+ProName+"']/following-sibling::a[contains(@class,'add-to-cart')]"));
    }

    public void clickProductInRecommended(String ProName)
    {
        ActionUtils.clickElementJS(getDynamicRecommendedProduct(ProName));

    }

    public boolean scrollToSubscription()
    {
        ActionUtils.scrollToElement(subscriptionTxt);
       return subscriptionTxt.isDisplayed();

    }
    public boolean scrollupBtn()
    {
        ActionUtils.waitForClickable(scrollUpIndicator);
        scrollUpIndicator.click();
        ActionUtils.waitTillElementVisible(homeBtn);
        return homeBtn.isDisplayed();
    }
    public String verifyAutomationEngineerTxt()
    {
       return AutomationEngineersTxt.getText();
    }

    public boolean scrollTop()
    {
        ActionUtils.scrollToElement(homeBtn);
        return homeBtn.isDisplayed();
    }




}
