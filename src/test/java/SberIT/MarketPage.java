package SberIT;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class MarketPage {
    private WebDriver driver;

    MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='_381y5 _21Njf']//*[text()='Компьютеры']")
    private WebElement computersBtn;

    @Step
    void clickComputersBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(computersBtn));
        Screen.takeScreen("Скрин маркета", driver);
        computersBtn.click();
        Screen.takeScreen("Скрин 'Компьютеры'", driver);
    }
}
