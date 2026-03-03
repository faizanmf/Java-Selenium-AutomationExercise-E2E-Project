package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentSuccessPage extends ActionUtils {

    private WebDriver driver;

    public PaymentSuccessPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2[data-qa='order-placed']")
    private WebElement orderPlacedTitle;

    @FindBy(xpath = "//p[contains(normalize-space(), 'Congratulations!')]")
    private WebElement successMessage;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueBtn;

    @FindBy(xpath = "//a[normalize-space(text())='Download Invoice']")
    private WebElement downloadInvoice;

    // --- Verification Methods ----------------------------

    public boolean verifyOrderPlaced() {
        return orderPlacedTitle.isDisplayed()
                && orderPlacedTitle.getText().trim().equalsIgnoreCase("Order Placed!");
    }

    public boolean verifySuccessMessage() {
        return successMessage.isDisplayed()
                && successMessage.getText().trim()
                .equalsIgnoreCase("Congratulations! Your order has been confirmed!");
    }

    // Recommended: single method to validate the whole page
    public boolean verifyPaymentSuccessPage() {
        return verifyOrderPlaced() && verifySuccessMessage() && continueBtn.isDisplayed();
    }

    // --- Actions -----------------------------------------

    public void clickContinue() {
        continueBtn.click();
    }

    public void downloadInvoice()
    {
        downloadInvoice.click();
    }
}
