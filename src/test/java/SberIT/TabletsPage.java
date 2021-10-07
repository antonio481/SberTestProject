package SberIT;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

class TabletsPage {
    private WebDriver driver;


    TabletsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "glpriceto")
    private WebElement priceToField;
    @FindBy(id = "glpricefrom")
    private WebElement priceFromField;
    @FindBy(xpath = "//*[@class='_1exhF']//*[text()='Lenovo']")
    private WebElement lenovoCheckbox;
    @FindBy(xpath = "//*[@class='_1exhF']//*[text()='HUAWEI']")
    private WebElement huaweiCheckbox;
    @FindBy(xpath = "//div[@class='_3NaXx _33ZFz _2m5MZ']/span[@data-autotest-currency='₽']/span[1]")
    private List<WebElement> pricesModels;
    @FindBy(xpath = "//div[@class='_3NaXx _33ZFz _2m5MZ c5gK7']/span[@data-autotest-currency='₽']/span[1]")
    private List<WebElement> pricesOffers;


    @Step
    void inputPriceToField(String price) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(priceToField));
        priceToField.sendKeys(price);
        Screen.takeScreen("Скрин цены 'От'", driver);
    }

    @Step
    void inputPriceFromField(String price) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(priceFromField));
        priceFromField.sendKeys(price);
        Screen.takeScreen("Скрин цены 'До'", driver);
    }

    @Step
    void clickLenovoCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(lenovoCheckbox));
        lenovoCheckbox.click();
        Screen.takeScreen("Скрин 'Lenovo' чекбокс", driver);
    }

    @Step
    void clickHuaweiCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(huaweiCheckbox));
        huaweiCheckbox.click();
        Screen.takeScreen("Скрин 'Huawei' чекбокс", driver);
    }

    @Step
    void checkNumberOfPrices(int expectedNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfAllElements(pricesModels));
        wait.until(ExpectedConditions.visibilityOfAllElements(pricesOffers));
        Screen.takeScreen("Скрин прогруженной страницы", driver);
        Assert.assertEquals(expectedNumber, pricesModels.size() + pricesOffers.size());
    }

    @Step
    void checkPricesOfTablets(int fromPrice, int toPrice) {
        pricesModels.stream().map(pricesModel -> (Integer.parseInt(pricesModel.getText().replaceAll("\\s+", "")) < toPrice) || (Integer.parseInt(pricesModel.getText().replaceAll("\\s+", "")) > fromPrice)).forEach(Assert::assertTrue);
        pricesOffers.stream().map(pricesOffer -> (Integer.parseInt(pricesOffer.getText().replaceAll("\\s+", "")) < toPrice) || (Integer.parseInt(pricesOffer.getText().replaceAll("\\s+", "")) > fromPrice)).forEach(Assert::assertTrue);
    }
}
