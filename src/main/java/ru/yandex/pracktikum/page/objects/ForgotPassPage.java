package ru.yandex.pracktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassPage {
    private final WebDriver driver;
    private final By forgotPassPageLoginButton = By.cssSelector("a[href='/login']");

    public ForgotPassPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку входа на странице восстановления пароля")
    public void clickForgotPassPageLoginButton() {
        driver.findElement(forgotPassPageLoginButton).click();
    }
}

