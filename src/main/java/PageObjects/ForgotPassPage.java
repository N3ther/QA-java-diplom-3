package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By forgotPassPageLoginButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    public ForgotPassPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    //кнопка войти на странице восстановления пароля
    public void clickForgotPassPageLoginButton() {
        driver.findElement(forgotPassPageLoginButton).click();
    }
}
