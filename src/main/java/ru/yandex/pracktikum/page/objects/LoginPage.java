package ru.yandex.pracktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginEmailField = By.cssSelector("input[name='name']");
    private final By loginPasswordField = By.cssSelector("input[name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By loginForm = By.cssSelector("form.Auth_form__3qGwq");

    public By getLoginFormLocator() { return loginButton; }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Вход с учётными данными {email}/{password}")
    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailField)).sendKeys(email);
        driver.findElement(loginPasswordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Проверить отображение страницы входа")
    public boolean isLoginPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }

    @Step("Проверить отображение формы входа")
    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }
}