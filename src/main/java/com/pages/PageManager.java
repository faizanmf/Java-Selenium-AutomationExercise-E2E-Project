package com.pages;

public class PageManager {

    public LoginPage loginPage() {
        return new LoginPage();
    }

    public HomePage homePage() {
        return new HomePage();
    }

    public ContactusPage contactusPage() {
        return new ContactusPage();
    }

    public TestCasePage testCasePage() {
        return new TestCasePage();
    }

    public ProductPage productPage() {
        return new ProductPage();
    }

    public ProductDetailsPage productDetailPage() {
        return new ProductDetailsPage();
    }

    public CartPage cartPage() {
        return new CartPage();
    }

    public SignupLoginPage signupLoginPage() {
        return new SignupLoginPage();
    }

    public AccountInfoPage accountInfoPage() {
        return new AccountInfoPage();
    }

    public AccountCreatedPage accountCreatedPage() {
        return new AccountCreatedPage();
    }

    public CheckoutPage checkoutPage() {
        return new CheckoutPage();
    }

    public PaymentPage paymentPage() {
        return new PaymentPage();
    }

    public PaymentSuccessPage paymentSuccessPage() {
        return new PaymentSuccessPage();
    }

    public AccountDeletedPage accountDeletedPage() {
        return new AccountDeletedPage();
    }
}
