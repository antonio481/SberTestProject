package SberIT;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class ComputersPage {
    private WebDriver driver;


    ComputersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@class='_3VMnE']//*[text()='Ноутбуки']")
    private WebElement notebooksBtn;
    @FindBy(xpath = "//*[@class='_3VMnE']//*[text()='Планшеты']")
    private WebElement tabletsBtn;

    @Step
    void clickNotebooksBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(notebooksBtn));
        notebooksBtn.click();
        Screen.takeScreen("Скрин 'Ноутбуки'", driver);
    }

    @Step
    void clickTabletsBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(notebooksBtn));
        tabletsBtn.click();
        Screen.takeScreen("Скрин 'Планшеты'", driver);
    }
}
