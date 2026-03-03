package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class ProductDetailsPage {

    WebDriver driver;
    @FindBy(id = "quantity")
    private WebElement quantityCount;
    @FindBy(xpath = "//button[contains(.,'Add to cart')]")
    private WebElement addToCart;
    @FindBy(xpath = "//u[normalize-space(text())='View Cart']")
    private WebElement viewCart;
    @FindBy(id = "review")
    private WebElement reviewBox;
    @FindBy(id = "name")
    private WebElement Name;
    @FindBy(id = "email")
    private WebElement Email;
    @FindBy(id = "button-review")
    private WebElement reviewSubmitBtn;
    @FindBy(xpath = "//span[normalize-space(.)='Thank you for your review.']")
    private WebElement reviewSuccessMsg;

    public ProductDetailsPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    private WebElement verifyProductName(String productName) {
        return driver.findElement(By.xpath("//h2[normalize-space(text())='" + productName + "']"));
    }

    private WebElement verifyProductPrice(int productPrice) {
        return driver.findElement(By.xpath("//span[normalize-space(text())='Rs. " + productPrice + "']"));
    }

    public String verifyDetailsPage() {
        return driver.getTitle();
    }

    public boolean verifyprice(int pri) {
        return verifyProductPrice(pri).isDisplayed();
    }

    public String verifyname(String name) {
        return verifyProductName(name).getText();
    }

    public String getProductPrice(String price) {
        return driver.findElement(By.xpath("//span[normalize-space(text())='" + price + "']")).getText();
    }


    public void incQuality(int no) {
        quantityCount.click();
        quantityCount.clear();
        for (int i = 1; i <= no; i++) {
            quantityCount.sendKeys(Keys.ARROW_UP);
        }
    }

    public void AddToCart() {
        addToCart.click();
    }

    public void clickViewCart() {
        ActionUtils.waitTillElementVisible(viewCart);
        viewCart.click();
    }

    public void verifyReview(String name) {
        ActionUtils.waitTillElementVisible(verifyProductName(name));
        ActionUtils.scrollToElement(reviewBox);

    }

    public void writeReview(String name, String email, String reviewMsg)
    {
        Name.sendKeys(name);
        Email.sendKeys(email);
        reviewBox.sendKeys(reviewMsg);
        ActionUtils.clickElementJS(reviewSubmitBtn);

    }

    public String verifyReviewSuccessMsg()
    {
       return reviewSuccessMsg.getText();
    }

}
