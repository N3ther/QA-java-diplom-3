package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By nameField = By.xpath("//input[contains(@class, 'input__textfield') and @name='name']");
    private By registerEmailField = By.xpath("//label[normalize-space()='Email']/following-sibling::input");
    private By registerPasswordField = By.xpath("//input[@name='Пароль']");
    private By registedButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    private By regPageloginButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }



    public void setName(String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        element.click();
        element.clear();
        element.sendKeys(name);
    }

    public void setRegisterEmail(String email) {
        driver.findElement(registerEmailField).sendKeys(email);
    }

    public void setRegisterPassword(String password) {
        driver.findElement(registerPasswordField).sendKeys(password);
    }

    public void clickRegistedButton() {
        driver.findElement(registedButton).click();
    }

    //кнопка войти на странице регистрации
    public void clickRegPageloginButton() {
        driver.findElement(regPageloginButton).click();
    }

}
