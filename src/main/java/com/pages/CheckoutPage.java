package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends ActionUtils {

    WebDriver driver;


    public CheckoutPage()
    {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "address_delivery")
    private WebElement deliveryAddressBox;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']")
    private WebElement fullName;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][1]")
    private WebElement addressLine1;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][2]")
    private WebElement addressLine2;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][3]")
    private WebElement addressLine3;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']")
    private WebElement cityStatePostcode;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_country_name']")
    private WebElement country;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[@class='address_phone']")
    private WebElement phone;

    @FindBy(css = "#ordermsg textarea[name='message']")
    private WebElement orderCommentBox;

    @FindBy(css = "a.btn.btn-default.check_out")
    private WebElement placeOrderBtn;

    //BillingAddress
    // Assuming checkoutPage.java
    @FindBy(xpath = "//ul[@id='address_invoice']//li[contains(@class,'address_firstname') and contains(@class,'address_lastname')]")
    private WebElement billingFullName;

    @FindBy(xpath = "(//ul[@id='address_invoice']//li[contains(@class,'address_address1') and contains(@class,'address_address2')])[1]")
    private WebElement billingAddressLine1;

    @FindBy(xpath = "(//ul[@id='address_invoice']//li[contains(@class,'address_address1') and contains(@class,'address_address2')])[2]")
    private WebElement billingAddressLine2;

    @FindBy(xpath = "(//ul[@id='address_invoice']//li[contains(@class,'address_address1') and contains(@class,'address_address2')])[3]")
    private WebElement billingAddressLine3;

    @FindBy(xpath = "//ul[@id='address_invoice']//li[contains(@class,'address_city') and contains(@class,'address_state_name') and contains(@class,'address_postcode')]")
    private WebElement billingCityStatePostcode;

    @FindBy(xpath = "//ul[@id='address_invoice']//li[contains(@class,'address_country_name')]")
    private WebElement billingCountry;

    @FindBy(xpath = "//ul[@id='address_invoice']//li[contains(@class,'address_phone')]")
    private WebElement billingPhone;

    @FindBy(xpath = "//a[normalize-space(text())='Delete Account']")
    private WebElement deleteAcc;


    public boolean verifyDeliveryAddress(String name,
                                         String line1,
                                         String line2,
                                         String line3,
                                         String cityStateZip,
                                         String countryName,
                                         String phoneNumber)
    {
        return fullName.getText().trim().equalsIgnoreCase(name)
                && addressLine1.getText().trim().equalsIgnoreCase(line1)
                && addressLine2.getText().trim().equalsIgnoreCase(line2)
                && addressLine3.getText().trim().equalsIgnoreCase(line3)
                && cityStatePostcode.getText().trim().equalsIgnoreCase(cityStateZip)
                && country.getText().trim().equalsIgnoreCase(countryName)
                && phone.getText().trim().equalsIgnoreCase(phoneNumber);
    }



    public void enterCommentAndPlaceOrder(String comment) {
        WebDriver driver = DriverManager.getDriver();

        // Scroll textarea into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderCommentBox);

        // Click using JS to bypass the iframe issue
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderCommentBox);

        orderCommentBox.clear();
        orderCommentBox.sendKeys(comment);

        // JS click for Place Order also safer
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderBtn);
    }

    public boolean verifyBillingAddress(String name,
                                        String line1,
                                        String line2,
                                        String line3,
                                        String cityStateZip,
                                        String countryName,
                                        String phoneNumber) {
        return billingFullName.getText().trim().equalsIgnoreCase(name)
                && billingAddressLine1.getText().trim().equalsIgnoreCase(line1)
                && billingAddressLine2.getText().trim().equalsIgnoreCase(line2)
                && billingAddressLine3.getText().trim().equalsIgnoreCase(line3)
                && billingCityStatePostcode.getText().trim().equalsIgnoreCase(cityStateZip)
                && billingCountry.getText().trim().equalsIgnoreCase(countryName)
                && billingPhone.getText().trim().equalsIgnoreCase(phoneNumber);
    }

    public void deleteAccount()
    {
        ActionUtils.scrollToElement(deleteAcc);
        deleteAcc.click();
    }


}
