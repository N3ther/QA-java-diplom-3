package ru.yandex.pracktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By exitButton = By.xpath("//button[text()='Выход']");
    private final By constructButton = By.xpath("//a[p/text()='Конструктор']");
    private final By sbLogo = By.cssSelector("div.AppHeader_header__logo__2D0X2");

    public By getExitButton() { return exitButton; }

    public PaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Нажать кнопку 'Выход'")
    public void clickExitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exitButton)).click();
    }

    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructButton() {
        driver.findElement(constructButton).click();
    }

    @Step("Нажать логотип Stellar Burgers")
    public void clickSbLogo() {
        driver.findElement(sbLogo).click();
    }

    @Step("Проверить видимость кнопки выхода")
    public boolean isExitButtonVisible() {
        return driver.findElement(exitButton).isDisplayed();
    }
}
