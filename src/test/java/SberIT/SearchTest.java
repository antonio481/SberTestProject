package SberIT;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SearchTest {
    private static WebDriver driver;
    private static SearchPage searchPage;
    private static MarketPage marketPage;
    private static ComputersPage computersPage;
    private static NotebooksPage notebooksPage;
    private static TabletsPage tabletsPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("searchpage"));
        searchPage = new SearchPage(driver);
        marketPage = new MarketPage(driver);
        computersPage = new ComputersPage(driver);
        notebooksPage = new NotebooksPage(driver);
        tabletsPage = new TabletsPage(driver);

    }

    @Description(value = "Тест проверяет отображение ноутбуков, количество, соответствие производителей")
    @Test
    public void notebookTest() {
        searchPage.clickMarketBtn();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        marketPage.clickComputersBtn();
        computersPage.clickNotebooksBtn();
        notebooksPage.inputPriceToField(String.valueOf(30000));
        notebooksPage.clickHpCheckbox();
        notebooksPage.clickLenovoCheckbox();
        notebooksPage.waitSuccessSearch();
        notebooksPage.findNotebooks();
        notebooksPage.checkNumberOfNotebooks(48);
        notebooksPage.checkProducersOfNotebooks();
    }

    @Description(value = "Тест проверяет отображение планшетов, количество, соответствие цен")
    @Test
    public void tabletTest() {
        searchPage.clickMarketBtn();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        marketPage.clickComputersBtn();
        computersPage.clickTabletsBtn();
        tabletsPage.inputPriceFromField(String.valueOf(20000));
        tabletsPage.inputPriceToField(String.valueOf(25000));
        tabletsPage.clickHuaweiCheckbox();
        tabletsPage.clickLenovoCheckbox();
        notebooksPage.waitSuccessSearch();
        tabletsPage.checkNumberOfPrices(48);
        tabletsPage.checkPricesOfTablets(2000, 25000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
