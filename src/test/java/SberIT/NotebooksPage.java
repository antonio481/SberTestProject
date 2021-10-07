package SberIT;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class NotebooksPage {
    private WebDriver driver;

    NotebooksPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "glpriceto")
    private WebElement priceToField;
    @FindBy(xpath = "//*[@class='_2y67x']//*[text()='HP']")
    private WebElement hpCheckbox;
    @FindBy(xpath = "//*[@class='_2y67x']//*[text()='Lenovo']")
    private WebElement lenovoCheckbox;
    private By messageSuccessSearch = By.xpath("//*[contains(text(), 'Найдено')]");


    private By notebooksLocator = By.xpath("//*[@class='_3U6u5 _1EDYE cia-vs']//*[@data-zone-name='snippet-card']");
    private List<WebElement> notebooks;

    void findNotebooks() {
        try {
            notebooks = driver.findElements(notebooksLocator);
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            notebooks = driver.findElements(notebooksLocator);
        }

    }

    @Step
    void inputPriceToField(String price) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(priceToField));
        priceToField.sendKeys(price);
        Screen.takeScreen("Скрин цены 'До'", driver);
    }

    @Step
    void clickHpCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(hpCheckbox));
        hpCheckbox.click();
        Screen.takeScreen("Скрин 'HP' чекбокс", driver);
    }


    void waitSuccessSearch() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageSuccessSearch));
    }

    @Step
    void clickLenovoCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(lenovoCheckbox));
        lenovoCheckbox.click();
        Screen.takeScreen("Скрин 'Lenovo' чекбокс", driver);
    }

    @Step
    void checkNumberOfNotebooks(int expectedNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfAllElements(notebooks));
        Screen.takeScreen("Скрин прогруженной страницы", driver);
        Assert.assertEquals(expectedNumber, notebooks.size());
    }

    @Step
    void checkProducersOfNotebooks() {
        IntStream.range(0, notebooks.size()).forEach(i -> Assert.assertTrue(notebooks.get(i).getText().contains("HP") || notebooks.get(i).getText().contains("Lenovo") || notebooks.get(i).getText().contains("LENOVO")));
    }
}
