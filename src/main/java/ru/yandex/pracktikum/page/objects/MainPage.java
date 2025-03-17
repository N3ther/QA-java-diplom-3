package ru.yandex.pracktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current__2BEPc";

    private final By paLoginButton = By.cssSelector("a[href='/account']");
    private final By enterLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsSection = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG')]" +
            "//span[text()='Булки']/ancestor::div[1]");
    private final By saucesSection = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG')]" +
            "//span[text()='Соусы']/ancestor::div[1]");
    private final By fillingsSection = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG')]" +
            "//span[text()='Начинки']/ancestor::div[1]");
    private final By orderButton = By.cssSelector("button.button_button_type_primary__1O7Bx");



    public By getOrderButton() {
        return orderButton;
    }
    public By getBunsSection() {
        return bunsSection;
    }
    public By getSaucesSection() {
        return saucesSection;
    }
    public By getFillingsSection() {
        return fillingsSection;
    }
    @Step("Проверить активность раздела 'Булки'")
    public boolean isBunsActive() {
        return driver.findElement(bunsSection)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }
    @Step("Проверить активность раздела 'Соусы'")
    public boolean isSaucesActive() {
        return driver.findElement(saucesSection)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }
    @Step("Проверить активность раздела 'Начинки'")
    public boolean isFillingsActive() {
        return driver.findElement(fillingsSection)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPaLoginButton() {
        driver.findElement(paLoginButton).click();
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickEnterLoginButton() {
        driver.findElement(enterLoginButton).click();
    }

    @Step("Проверить видимость кнопки входа")
    public boolean isEnterLoginButtonVisible() {
        return driver.findElement(enterLoginButton).isDisplayed();
    }


    @Step("Проверить видимость конструктора бургеров")
    public boolean isBurgerConstructorVisible() {
        return driver.findElement(bunsSection).isDisplayed();
    }

    @Step("Проверить видимость кнопки оформления заказа")
    public boolean isOrderButtonVisible() {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


