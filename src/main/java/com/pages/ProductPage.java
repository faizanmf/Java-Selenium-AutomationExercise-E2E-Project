package com.pages;

import com.driver.DriverManager;
import com.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends ActionUtils {

    private final WebDriver driver;

    public ProductPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[@class='title text-center']")
    private WebElement productHeader;

    @FindBy(xpath = "//p[contains(text(),'Men Tshirt')]//following::div[@class='choose']")
    private List<WebElement> viewProduct;

    @FindBy(id = "search_product")
    private WebElement searchBar;

    @FindBy(id = "submit_search")
    private WebElement searchBtn;

    @FindBy(xpath = "//div[@class='productinfo text-center']")
    private List<WebElement> countProduct;

    @FindBy(xpath = "//h4[normalize-space(text())='Added!']")
    private WebElement addedTxt;

    @FindBy(xpath = "//button[contains(@class,'btn btn-success')]")
    private WebElement contBtn;

    @FindBy(xpath = "(//ul[@class='nav navbar-nav']//a)[1]")
    private WebElement homeBtn;

    @FindBy(xpath = "//a[normalize-space(text())='Cart']")
    private WebElement cartBtn;

    @FindBy(xpath = "//u[normalize-space(text())='View Cart']")
    private WebElement viewCart;

    @FindBy(xpath = "//h2[normalize-space(text())='Brands']")
    private WebElement brandTitle;



    public boolean verifyProductHeader()
    {
       return productHeader.isDisplayed();
    }

    private WebElement getViewButton(String productName) {
        return driver.findElement(By.xpath("(//p[contains(text(),'"+productName+"')]/following::div[@class='choose'])[1]"));
    }

    public WebElement getProductName(String productName) {
        return driver.findElement(By.xpath("//div[@class='productinfo text-center']/p[contains(text(),'"+productName+"')]"));
    }

    public WebElement hoverSlider(String productName) {
        return driver.findElement(By.xpath("(//p[starts-with(text(),'Men ')])/parent::div[@class='productinfo text-center']"));

    }

    public WebElement hoverSliderAddToCartBtn(String productName) {
        return driver.findElement(By.xpath("((//p[starts-with(text(),'"+productName+"')])/parent::div[@class='productinfo text-center']//a)[1]"));

    }

    public void clickProduct(String productName) {
        ActionUtils.scrollToElement(getViewButton(productName));
        getViewButton(productName).click();

    }

    public void enterProductinSearch(String name)
    {
        searchBar.sendKeys(name);
        searchBtn.click();
    }

    public int countProduct()
    {
        return countProduct.size();
    }

    public boolean verifySearchedProduct(String name) {

        ActionUtils.scrollToElement(searchBar);
        ActionUtils.waitTillElementVisible(getProductName(name));
        return getProductName(name).isDisplayed();
    }


    public void clickAddToCart(String proName) throws InterruptedException {
        ActionUtils.scroll();
        ActionUtils.hover(hoverSlider(proName));
        Thread.sleep(1000);
        ActionUtils.clickElementJS(hoverSliderAddToCartBtn(proName));

       By addedTxt1 = By.xpath("//h4[normalize-space(text())='Added!']");
        ActionUtils.waitTillElementDisplayed(addedTxt1);
        contBtn.click();
    }

    public void scrollToHeader()
    {
        ActionUtils.scrollToElement(homeBtn);
    }

    public void clickCart()
    {
        cartBtn.click();
    }

    public void clickViewCart()
    {
        ActionUtils.waitTillElementVisible(viewCart);
    }

    public void verifyBrands()
    {
        ActionUtils.scrollToElement(brandTitle);
        brandTitle.isDisplayed();
    }

    public void clickBrandName(String name)
    {
        WebElement element = driver.findElement(By.xpath("//a[contains(.,'"+name+"')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }

    public String verifyFilterTitle(String name, String item)
    {
        ActionUtils.waitTillElementDisplayed(By.xpath("//h2[normalize-space(.)='"+name+" - "+item+" Products']"));
        return driver.findElement(By.xpath("//h2[normalize-space(.)='"+name+" - "+item+" Products']")).getText();
    }

    private WebElement getDynamicProductName(String productName)
    {
        return driver.findElement(By.xpath("//p[normalize-space()='"+productName+"']" +
                "/ancestor::div[@class='single-products']" +
                "/following-sibling::div[@class='choose']//a[normalize-space()='View Product']"));
    }

    public void clickviewProduct(String productName) {
        WebElement element = getDynamicProductName(productName);
        ActionUtils.scrollToElement(element);
        ActionUtils.clickElementJS(element);
    }




}
