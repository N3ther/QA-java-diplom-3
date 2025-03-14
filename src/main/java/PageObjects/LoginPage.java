package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginEmailField = By.xpath("//input[@name='name']");
    private By loginPasswordField = By.xpath("//input[@name='Пароль']");
    private By loginButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    private By loginForm = By.xpath("//form[contains(@class, 'Auth_form')]");

    public By getLoginFormLocator() { return loginForm; }


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // вместо Duration
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailField)).sendKeys(email);
        driver.findElement(loginPasswordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
    public boolean isLoginPageDisplayed() {
        // Проверяем наличие элемента
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();

    }

    public boolean isLoginFormDisplayed() {
        return driver.findElement(loginForm).isDisplayed();
    }
}
