package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By exitButton = By.xpath("//button[normalize-space()='Выход']");
    private By constructButton = By.xpath("//p[normalize-space()='Конструктор']/parent::a");
    private By sbLogo = By.xpath("//*[@id=\"root\"]/div/header/nav/div");

    public By getExitButton() { return exitButton; }

    public PaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    //кнопка выход
    public void clickExitButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(exitButton)).click();
    }
    //кнопка конструктор
    public void clickConstructButton() {
        driver.findElement(constructButton).click();
    }
    //кнопка лого
    public void clickSbLogo() {
        driver.findElement(sbLogo).click();
    }

    public boolean isExitButtonVisible() {
        return driver.findElement(exitButton).isDisplayed();
    }

}
