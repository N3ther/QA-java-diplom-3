package ru.yandex.pracktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameField = By.cssSelector("input[name='name']");
    private final By registerEmailField = By.xpath("//label[normalize-space()=\"Email\"]/following-sibling::input");
    private final By registerPasswordField = By.cssSelector("input[name='Пароль']");
    private final By registedButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By regPageloginButton = By.cssSelector("a[href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Указать имя {name}")
    public void setName(String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        element.clear();
        element.sendKeys(name);
    }

    @Step("Указать email {email}")
    public void setRegisterEmail(String email) {
        driver.findElement(registerEmailField).sendKeys(email);
    }

    @Step("Указать пароль {password}")
    public void setRegisterPassword(String password) {
        driver.findElement(registerPasswordField).sendKeys(password);
    }

    @Step("Нажать кнопку регистрации")
    public void clickRegistedButton() {
        driver.findElement(registedButton).click();
    }

    @Step("Нажать ссылку входа на странице регистрации")
    public void clickRegPageloginButton() {
        driver.findElement(regPageloginButton).click();
    }
}
