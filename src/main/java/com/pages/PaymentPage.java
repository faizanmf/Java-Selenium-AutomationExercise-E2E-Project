package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends ActionUtils
{

    WebDriver driver;


    public PaymentPage()
    {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name_on_card")
    private WebElement nameOnCard;

    @FindBy(name = "card_number")
    private WebElement cardNumber;

    @FindBy(name = "cvc")
    private WebElement cvc;

    @FindBy(name = "expiry_month")
    private WebElement expiryMonth;

    @FindBy(name = "expiry_year")
    private WebElement expiryYear;

    @FindBy(css = "button[data-qa='pay-button']")
    private WebElement payAndConfirmBtn;

    public void enterPaymentDetailsAndPay(String name,
                                          String cardNum,
                                          String cvcCode,
                                          String month,
                                          String year) {


        ActionUtils.waitForClickable(nameOnCard);

        nameOnCard.clear();
        nameOnCard.sendKeys(name);

        cardNumber.clear();
        cardNumber.sendKeys(cardNum);

        cvc.clear();
        cvc.sendKeys(cvcCode);

        expiryMonth.clear();
        expiryMonth.sendKeys(month);

        expiryYear.clear();
        expiryYear.sendKeys(year);
        ActionUtils.scrollToElement(payAndConfirmBtn);
        payAndConfirmBtn.click();
    }



}
