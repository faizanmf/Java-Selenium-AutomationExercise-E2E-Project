package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends ActionUtils {

    WebDriver driver;

    public CartPage()
    {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

//    public CartPage()
//    {
//        super();
//    }

    @FindBy(xpath = "//h2[normalize-space(text())='Subscription']")
    private WebElement subscriptionTxt;

    @FindBy(xpath = "//input[@placeholder='Your email address']")
    private WebElement subscriptionEmailField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement subscribeBtn;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    private WebElement subscriptionSuccessMessage;

    @FindBy(xpath = "//table[@id='cart_info_table']/tbody[1]/tr[1]/td[4]/button[1]")
    private WebElement productQuantity;

    @FindBy(xpath = "//a[contains(@class,'btn btn-default')]")
    private WebElement proceedTOCheckout;

    @FindBy(xpath = "//u[normalize-space(text())='Register / Login']")
    private WebElement registerLoginBtn;

    @FindBy(xpath = "//li[normalize-space(text())='Shopping Cart']")
    private WebElement shoppingCartText;

    @FindBy(xpath = "//a[normalize-space(text())='Proceed To Checkout']")
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//a[normalize-space(text())='Signup / Login']")
    private WebElement signupLogin;

    public WebElement productName(String name)
    {
        return driver.findElement(By.xpath("//a[starts-with(text(),'"+name+"')]"));
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

    public String verifyProductName(String name)
    {
       return productName(name).getText();
    }

    public String verifyProductQuantity()
    {
       return productQuantity.getText();
    }

    public void clickCheckOut()
    {
        proceedTOCheckout.click();
    }

    public void clickRegisterLogin()
    {
        ActionUtils.waitTillElementVisible(registerLoginBtn);
        registerLoginBtn.click();
    }

    public boolean verifyCartPage()
    {
        return shoppingCartText.isDisplayed();
    }

    public void clickProceedToCheckout()
    {
        proceedToCheckout.click();
    }

    private WebElement cancelBtnForProduct(String ProName)
    {
        ActionUtils.waitTillElementVisible(driver.findElement(By.xpath("//h4[contains(.,'"+ProName+"')]/ancestor::tr[@id='product-1']/td/a[@class='cart_quantity_delete']/i")));
       return driver.findElement(By.xpath("//h4[contains(.,'"+ProName+"')]/ancestor::tr[@id='product-1']/td/a[@class='cart_quantity_delete']/i"));
    }

    public void clickCancel(String ProductName) throws InterruptedException {

        WebElement ele = cancelBtnForProduct(ProductName);
        ActionUtils.waitForClickable(ele);
        ActionUtils.clickElementJS(ele);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

    }

    private WebElement ProductName(String name)
    {
       WebElement web = driver.findElement(By.xpath("//a[normalize-space(text())='"+name+"']"));
       ActionUtils.waitTillElementVisible(web);
       return web;
    }

    public boolean verifyProductDisplayed(String name)

    {
        ActionUtils.scrollToElement(ProductName(name));
        ActionUtils.waitTillElementDisplayed(By.xpath("//a[normalize-space(.)='"+name+"']"));
       return ProductName(name).isDisplayed();
    }

    public boolean verifyProductRemoved(String productName) {
        By productLocator = By.xpath("//a[normalize-space(text())='" + productName + "']");
        ActionUtils.waitForElementToBeInvisible(driver.findElement(productLocator));
        return driver.findElements(productLocator).isEmpty();
    }

    public void clickSignupLogin()
    {
        ActionUtils.scrollToElement(signupLogin);
        signupLogin.click();
    }

}
