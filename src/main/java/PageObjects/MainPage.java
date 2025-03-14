package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private WebDriverWait wait;
    private final By paLoginButton = By.xpath("//a[contains(@href, '/account')]");
    private final By enterLoginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private final By bunsButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
    private final By saucesButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
    private final By fillingsButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");
    private final By constructorSection = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients__')]");

    public By getConstructorLocator() { return constructorSection; }
    public By getBunsButton() { return bunsButton; }
    public By getSaucesButton() { return saucesButton; }
    public By getFillingsButton() { return fillingsButton; }


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    //кнопка личный кабинет на главной странице
    public void clickPaLoginButton() {
        driver.findElement(paLoginButton).click();
    }
    //кнопка войти в аккаунт на главной странице
    public void clickEnterLoginButton() {
        driver.findElement(enterLoginButton).click();
    }

    public boolean isEnterLoginButtonVisible() {
        return driver.findElement(enterLoginButton).isDisplayed();
    }

    public boolean isBunsActive() {
        return driver.findElement(bunsButton)
                .getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    public boolean isSaucesActive() {
        return driver.findElement(saucesButton)
                .getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    public boolean isFillingsActive() {
        return driver.findElement(fillingsButton)
                .getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    public boolean isBurgerConstructorVisible() {
        return driver.findElement(constructorSection).isDisplayed();
    }

}


