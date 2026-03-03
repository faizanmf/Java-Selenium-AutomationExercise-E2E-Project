package Test;

import base.BaseTest;
import com.utils.RandomDetails;
import com.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FullFlowTest extends BaseTest {

    public String FirstProductName = "Men ";
    public String SecondProductName = "Blue Top";
    int price = 400;

    //@Test(dataProvider = "ValidloginData", dataProviderClass = TestDataProvider.class, groups = "Data Driven Testing")
    public void loginTest(String email, String pass) throws InterruptedException {
        boolean title = page.loginPage().clickLoginBtn();
        Assert.assertTrue(title);

        page.loginPage().login(email, pass);
        if (page.loginPage().logoutBtn.isDisplayed()) {
            page.loginPage().logoutBtn.click();
        }
    }

   // @Test(dataProvider = "InvalidloginData", dataProviderClass = TestDataProvider.class, groups = "Data Driven Testing")
    public void loginTest1(String email, String pass) {

        boolean title = page.loginPage().clickLoginBtn();
        Assert.assertTrue(title);
        page.loginPage().login(email, pass);
        Assert.assertTrue(page.loginPage().errorValidation());

    }

    @Test(dataProvider = "DBValidData", dataProviderClass = TestDataProvider.class, groups = "Data Driven Testing")
    public void DBValidLoginTest(String email, String password) {
        System.out.println("Email: " + email + " | Password: " + password);
        boolean title = page.loginPage().clickLoginBtn();
        Assert.assertTrue(title);

        page.loginPage().login(email, password);
        if (page.loginPage().logoutBtn.isDisplayed()) {
            page.loginPage().logoutBtn.click();
        }

    }

    @Test(dataProvider = "DBInValidData", dataProviderClass = TestDataProvider.class, groups = "Data Driven Testing")
    public void DBInvalidLoginTest(String email, String password) {
        System.out.println("Email: " + email + " | Password: " + password);
        boolean title = page.loginPage().clickLoginBtn();
        Assert.assertTrue(title);
        page.loginPage().login(email, password);
        Assert.assertTrue(page.loginPage().errorValidation());

    }

    @Test(groups = "Regression")
    public void ExistingEmail() {
        page.loginPage().clickLoginBtn();
        Assert.assertTrue(page.loginPage().newSignup());
        page.loginPage().entersignupDetails("faizan", "faizanmf.5252@gmail.com");
        Assert.assertTrue(page.loginPage().alreadyExistEmail());
    }

    @Test(groups = "Regression")
    public void VerifyContactusForm() {
        page.homePage().clickContactUs();
        Assert.assertTrue(page.contactusPage().verifyheader());
        page.contactusPage().enterDetails("faizan", "faizanmf.5252@gmail.com", "test", "automation testing");
        page.contactusPage().fileupload();
        page.contactusPage().clickSubmit();

    }

    @Test(groups = "Regression")
    public void VerifyTestcasePage() {
        page.homePage().clickTestCase();
        Assert.assertEquals(page.testCasePage().verifyTestcasePage(), "Automation Practice Website for UI Testing - Test Cases");
    }

    @Test(groups = "Regression")
    public void verifyProductDetailPage() {
        page.homePage().clickProductBtn();
        Assert.assertTrue(page.productPage().verifyProductHeader());
        page.productPage().clickProduct(FirstProductName);
        Assert.assertEquals(page.productDetailPage().verifyDetailsPage(), "Automation Exercise - Product Details");
        Assert.assertTrue(page.productDetailPage().verifyprice(price));

    }

    @Test(groups = "Regression")
    public void verifySearch() {
        page.homePage().clickProductBtn();
        Assert.assertTrue(page.productPage().verifyProductHeader());
        page.productPage().enterProductinSearch(FirstProductName);
        Assert.assertTrue(page.productPage().verifySearchedProduct(FirstProductName));
    }

    @Test(groups = "Regression")
    public void verifySubscriptionInHomePage() {
        page.homePage().verifySubscription();
        Assert.assertTrue(page.homePage().enterSubscribeEmail("faizanmf.5252@gmail.com"));
    }

    @Test(groups = "Regression")
    public void verifySubscriptionInCartPage() {
        page.homePage().clickCartBtn();
        page.cartPage().verifySubscription();
        Assert.assertTrue(page.cartPage().enterSubscribeEmail("faizanmf.5252@gmail.com"));
    }

    @Test(groups = "Regression")
    public void AddProductsInCart() throws InterruptedException {
        page.homePage().clickProductBtn();
        page.productPage().clickAddToCart(FirstProductName);
        page.productPage().clickAddToCart(SecondProductName);
        page.productPage().scrollToHeader();
        page.productPage().clickCart();
        page.cartPage().verifyProductName(FirstProductName);
        page.cartPage().verifyProductName(SecondProductName);
        //Assert.assertEquals(page.cartPage().verifyProductName(Name), Name);
        Assert.assertEquals(page.cartPage().verifyProductName(SecondProductName), SecondProductName);
    }

    @Test(groups = "Regression")
    public void productQuantityInCart() {
        int n = 5;
        String expectedPrice = page.homePage().priceOfProduct(FirstProductName);
        page.homePage().clickviewProduct(FirstProductName);
        String actualPrice = page.productDetailPage().getProductPrice(expectedPrice);
        Assert.assertEquals(actualPrice, expectedPrice);
        page.productDetailPage().incQuality(n);
        page.productDetailPage().AddToCart();
        page.productDetailPage().clickViewCart();
        int actualQty = Integer.parseInt(page.cartPage().verifyProductQuantity());
        Assert.assertEquals(actualQty, n);

    }

    @Test(groups = "Regression")
    public void PlaceOrderRegisterWhileCheckout() throws InterruptedException {
        page.homePage().clickProductBtn();
        page.productPage().clickAddToCart(SecondProductName);
        page.productPage().scrollToHeader();
        page.productPage().clickCart();
        page.cartPage().clickCheckOut();
        page.cartPage().clickRegisterLogin();
        String name = RandomDetails.generateRandomName();
        String mail = RandomDetails.generateRandomGmail();
        page.signupLoginPage().enterDetails(name, mail);
        page.accountInfoPage().fillAccountDetails(
                "Mr",
                "Test@123",
                "10",
                "May",
                "1998",
                "Faizan",
                "Safa",
                "ABC Ltd",
                "Street 123",
                "Near Market",
                "India",
                "Karnataka",
                "Bangalore",
                "560001",
                "9876543210"
        );

        page.accountInfoPage().clickCreateAccountBtn();
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedHeading(), "Account Created heading not displayed");
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedMessage(), "Account Created Message not displayed");

        page.accountCreatedPage().clickContinue();
        Assert.assertTrue(
                page.homePage().verifyLoggedInUser(name),
                "Logged in user name is incorrect!"
        );

        page.homePage().clickCartBtn();
        page.cartPage().clickCheckOut();
        Assert.assertTrue(
                page.checkoutPage().verifyDeliveryAddress(
                        "Mr. Faizan Safa",   // full name
                        "ABC Ltd",           // line1 (company)
                        "Street 123",        // line2
                        "Near Market",       // line3
                        "Bangalore Karnataka 560001", // city + state + postcode
                        "India",             // country
                        "9876543210"         // phone
                ),
                "Delivery Address mismatch!"
        );

        page.checkoutPage().enterCommentAndPlaceOrder("Please deliver between 5 PM to 7 PM");
        page.paymentPage().enterPaymentDetailsAndPay(
                "Faizan Safa",
                "4242424242424242",
                "311",
                "05",
                "2028"
        );

        Assert.assertTrue(page.paymentSuccessPage().verifyPaymentSuccessPage(),
                "Payment success page elements are not displayed correctly!");

        page.paymentSuccessPage().clickContinue();

        page.homePage().clickDeleteAccount();

        Assert.assertTrue(
                page.accountDeletedPage().isAccountDeletedMessageDisplayed(),
                "Account Deleted! message not displayed"
        );

        page.accountDeletedPage().clickContinue();

    }

    @Test(groups = "Regression")
    public void PlaceOrderRegisterBeforeCheckout() {
        page.homePage().clickProductBtn();
        page.homePage().clickSignupLogin();
        String name = RandomDetails.generateRandomName();
        String mail = RandomDetails.generateRandomGmail();
        page.signupLoginPage().enterDetails(name, mail);
        page.accountInfoPage().fillAccountDetails(
                "Mr",
                "Test@123",
                "10",
                "May",
                "1998",
                "Faizan",
                "Safa",
                "ABC Ltd",
                "Street 123",
                "Near Market",
                "India",
                "Karnataka",
                "Bangalore",
                "560001",
                "9876543210"
        );

        page.accountInfoPage().clickCreateAccountBtn();
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedHeading(), "Account Created heading not displayed");
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedMessage(), "Account Created Message not displayed");

        page.accountCreatedPage().clickContinue();
        Assert.assertTrue(
                page.homePage().verifyLoggedInUser(name),
                "Logged in user name is incorrect!"
        );

        page.homePage().addProduct(SecondProductName);
        page.homePage().clickViewCart();
        Assert.assertTrue(page.cartPage().verifyCartPage());
        page.cartPage().clickProceedToCheckout();
        Assert.assertTrue(
                page.checkoutPage().verifyDeliveryAddress(
                        "Mr. Faizan Safa",   // full name
                        "ABC Ltd",           // line1 (company)
                        "Street 123",        // line2
                        "Near Market",       // line3
                        "Bangalore Karnataka 560001", // city + state + postcode
                        "India",             // country
                        "9876543210"         // phone
                ),
                "Delivery Address mismatch!"
        );

        page.checkoutPage().enterCommentAndPlaceOrder("Please deliver between 5 PM to 7 PM");
        page.paymentPage().enterPaymentDetailsAndPay(
                "Faizan Safa",
                "4242424242424242",
                "311",
                "05",
                "2028"
        );

        Assert.assertTrue(page.paymentSuccessPage().verifyPaymentSuccessPage(),
                "Payment success page elements are not displayed correctly!");

        page.paymentSuccessPage().clickContinue();

        page.homePage().clickDeleteAccount();

        Assert.assertTrue(
                page.accountDeletedPage().isAccountDeletedMessageDisplayed(),
                "Account Deleted! message not displayed"
        );

        page.accountDeletedPage().clickContinue();
    }


    @Test(groups = "Regression")
    public void PlaceOrderLoginBeforeCheckout() {
        page.homePage().clickSignupLogin();
        page.loginPage().login("faizan5252@gmail.com", "Test@123");
        page.homePage().verifyLoggedInUser("Faizan");
        page.homePage().addProduct(SecondProductName);
        page.homePage().clickContinueShopping();
        page.homePage().clickCartBtn();
        Assert.assertTrue(page.cartPage().verifyCartPage());
        page.cartPage().clickProceedToCheckout();
        Assert.assertTrue(
                page.checkoutPage().verifyDeliveryAddress(
                        "Mr. Faizan Safa",   // full name
                        "ABC Ltd",           // line1 (company)
                        "Street 123",        // line2
                        "Near Market",       // line3
                        "Bangalore Karnataka 560001", // city + state + postcode
                        "India",             // country
                        "9876543210"         // phone
                ),
                "Delivery Address mismatch!"
        );
        page.checkoutPage().enterCommentAndPlaceOrder("Please deliver between 5 PM to 7 PM");
        page.paymentPage().enterPaymentDetailsAndPay(
                "Faizan Safa",
                "4242424242424242",
                "311",
                "05",
                "2028"
        );
        Assert.assertTrue(page.paymentSuccessPage().verifyPaymentSuccessPage(),
                "Payment success page elements are not displayed correctly!");

    }

    @Test(groups = "Regression")
    public void removeProductsFromCart() throws InterruptedException {
        page.homePage().addProduct(SecondProductName);
        page.homePage().clickContinueShopping();
        page.homePage().clickCartBtn();
        Assert.assertTrue(page.cartPage().verifyCartPage());
        Thread.sleep(2000);
        page.cartPage().clickCancel(SecondProductName);
        Assert.assertTrue(page.cartPage().verifyProductRemoved(SecondProductName), "Product is still displayed in the cart");

    }

    @Test(groups = "Regression")
    public void viewCategoryProducts() {
        Assert.assertTrue(page.homePage().verifyCategories());
        page.homePage().expandCategoryAndSelect("Women", "Dress");
        Assert.assertEquals(page.homePage().verifyBannerTitle("Women", "Dress"), "Women > Dress");
        page.homePage().expandCategoryAndSelect("Men", "Jeans");
        Assert.assertEquals(page.homePage().verifyBannerTitle("Men", "Jeans"), "Men > Jeans");

    }

    @Test(groups = "Regression")
    public void viewCartBrandProducts() {
        String brand = "Polo";
        String title = "Brand";
        page.homePage().clickProductBtn();
        page.productPage().verifyBrands();
        page.productPage().clickBrandName(brand);
        String actual = page.homePage()
                .verifyFilterTitle(brand)
                .replaceAll("\\s+", " ")
                .trim()
                .toLowerCase();

        String expected = (title + " - " + brand + " Products").toLowerCase();

        Assert.assertEquals(actual, expected, "Filter title text mismatch");
    }

    @Test(groups = "Regression")
    public void SearchProductsVerifyCartAfterLogin() throws InterruptedException {
        page.homePage().clickProductBtn();
        Assert.assertTrue(page.productPage().verifyProductHeader());
        page.productPage().verifySearchedProduct("Blue Top");
        page.productPage().clickAddToCart("Blue Top");
        page.productPage().clickCart();
        page.cartPage().clickSignupLogin();
        page.loginPage().login("faizanmf.5252@gmail.com", "Test@123");
        page.homePage().clickCartBtn();
        Assert.assertTrue(page.cartPage().verifyProductDisplayed("Blue Top"));
    }

    @Test(groups = "Regression")
    public void addReviewOnProduct() {
        page.homePage().clickProductBtn();
        Assert.assertTrue(page.productPage().verifyProductHeader());
        page.productPage().clickviewProduct("Blue Top");
        page.productDetailPage().verifyReview("Blue Top");
        page.productDetailPage().writeReview("faizan", "faizanmf.5252@gmail.com", "good");
        Assert.assertEquals(page.productDetailPage().verifyReviewSuccessMsg(), "Thank you for your review.");
    }

    @Test(groups = "Regression")
    public void AddToCartFromRecommendedItems() {
        Assert.assertTrue(page.homePage().scrollToRecommendedItems());
        page.homePage().clickProductInRecommended("Blue Top");
        page.homePage().clickViewCart();
        Assert.assertTrue(page.cartPage().verifyProductDisplayed("Blue Top"));

    }

    @Test(groups = "Regression")
    public void verifyAddressDetailsInCheckoutPage() {
        page.homePage().clickSignupLogin();
        String name = RandomDetails.generateRandomName();
        String mail = RandomDetails.generateRandomGmail();
        page.signupLoginPage().enterDetails(name, mail);
        page.accountInfoPage().fillAccountDetails(
                "Mr",
                "Test@123",
                "10",
                "May",
                "1998",
                "Faizan",
                "Safa",
                "ABC Ltd",
                "Street 123",
                "Near Market",
                "India",
                "Karnataka",
                "Bangalore",
                "560001",
                "9876543210"
        );
        page.accountInfoPage().clickCreateAccountBtn();
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedHeading(), "Account Created heading not displayed");
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedMessage(), "Account Created Message not displayed");

        page.accountCreatedPage().clickContinue();
        Assert.assertTrue(
                page.homePage().verifyLoggedInUser(name),
                "Logged in user name is incorrect!"
        );
        page.homePage().addProduct("Blue Top");
        page.homePage().clickCartBtn();
        Assert.assertTrue(page.cartPage().verifyCartPage());
        page.cartPage().clickProceedToCheckout();
        Assert.assertTrue(
                page.checkoutPage().verifyDeliveryAddress(
                        "Mr. Faizan Safa",   // full name
                        "ABC Ltd",           // line1 (company)
                        "Street 123",        // line2
                        "Near Market",       // line3
                        "Bangalore Karnataka 560001", // city + state + postcode
                        "India",             // country
                        "9876543210"         // phone
                ),
                "Delivery Address mismatch!"
        );

        Assert.assertTrue(
                page.checkoutPage().verifyBillingAddress(
                        "Mr. Faizan Safa",
                        "ABC Ltd",
                        "Street 123",
                        "Near Market",
                        "Bangalore Karnataka 560001",
                        "India",
                        "9876543210"
                ),
                "Billing Address mismatch!"
        );
        page.checkoutPage().deleteAccount();
        Assert.assertTrue(
                page.accountDeletedPage().isAccountDeletedMessageDisplayed(),
                "Account Deleted! message not displayed"
        );

        page.accountDeletedPage().clickContinue();

    }

    @Test(groups = "Regression")
    public void downloadInvoiceAfterPurchaseOrder() {
        page.homePage().addProduct("Blue Top");
        page.homePage().clickViewCart();
        page.cartPage().verifyCartPage();
        page.cartPage().clickProceedToCheckout();
        page.cartPage().clickRegisterLogin();
        page.cartPage().clickSignupLogin();
        String name = RandomDetails.generateRandomName();
        String mail = RandomDetails.generateRandomGmail();
        page.signupLoginPage().enterDetails(name, mail);
        page.accountInfoPage().fillAccountDetails(
                "Mr",
                "Test@123",
                "10",
                "May",
                "1998",
                "Faizan",
                "Safa",
                "ABC Ltd",
                "Street 123",
                "Near Market",
                "India",
                "Karnataka",
                "Bangalore",
                "560001",
                "9876543210"
        );

        page.accountInfoPage().clickCreateAccountBtn();
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedHeading(), "Account Created heading not displayed");
        Assert.assertTrue(page.accountCreatedPage().verifyAccountCreatedMessage(), "Account Created Message not displayed");
        page.accountCreatedPage().clickContinue();
        Assert.assertTrue(
                page.homePage().verifyLoggedInUser(name),
                "Logged in user name is incorrect!"
        );
        page.homePage().clickCartBtn();
        page.cartPage().clickProceedToCheckout();
        Assert.assertTrue(
                page.checkoutPage().verifyDeliveryAddress(
                        "Mr. Faizan Safa",   // full name
                        "ABC Ltd",           // line1 (company)
                        "Street 123",        // line2
                        "Near Market",       // line3
                        "Bangalore Karnataka 560001", // city + state + postcode
                        "India",             // country
                        "9876543210"         // phone
                ),
                "Delivery Address mismatch!"
        );

        Assert.assertTrue(
                page.checkoutPage().verifyBillingAddress(
                        "Mr. Faizan Safa",
                        "ABC Ltd",
                        "Street 123",
                        "Near Market",
                        "Bangalore Karnataka 560001",
                        "India",
                        "9876543210"
                ),
                "Billing Address mismatch!"
        );
        page.checkoutPage().enterCommentAndPlaceOrder("Please deliver between 5 PM to 7 PM");
        page.paymentPage().enterPaymentDetailsAndPay(
                "Faizan Safa",
                "4242424242424242",
                "311",
                "05",
                "2028"
        );
        Assert.assertTrue(page.paymentSuccessPage().verifyPaymentSuccessPage(),
                "Payment success page elements are not displayed correctly!");
        page.paymentSuccessPage().downloadInvoice();
        page.paymentSuccessPage().clickContinue();

        page.homePage().clickDeleteAccount();

        Assert.assertTrue(
                page.accountDeletedPage().isAccountDeletedMessageDisplayed(),
                "Account Deleted! message not displayed"
        );

        page.accountDeletedPage().clickContinue();

    }

    @Test(groups = "Regression")
    public void verifyScrollUpUsingArrowButtonScrollDownFunctionality() {
        Assert.assertTrue(page.homePage().scrollToSubscription());
        Assert.assertTrue(page.homePage().scrollupBtn());
        Assert.assertEquals(page.homePage().verifyAutomationEngineerTxt(), "Full-Fledged practice website for Automation Engineers");
    }

    @Test(groups = "Regression")
    public void verifyScrollUpWithoutArrowButtonScrollDownFunctionality() {
        Assert.assertTrue(page.homePage().scrollToSubscription());
        Assert.assertTrue(page.homePage().scrollTop());
        Assert.assertEquals(page.homePage().verifyAutomationEngineerTxt(), "Full-Fledged practice website for Automation Engineers");
    }

    @Test
    public void sampleJenkinsTest()
    {
        int a=5;
        int b=5;
        int c=a+b;

        Assert.assertEquals(c, 10);
    }



}



























