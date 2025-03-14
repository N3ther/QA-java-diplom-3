import PageObjects.RegisterPage;
import PageObjects.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertTrue;

public class RegistrationTests {
    private WebDriver driver;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        // Установка драйвера для Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register"); // Замените на URL вашего приложения
        registerPage = new RegisterPage(driver);
    }


    @Test
    public void testSuccessfulRegistration() {
        registerPage.setName("Test User");
        registerPage.setRegisterEmail("test@example.com");
        registerPage.setRegisterPassword("password123");
        registerPage.clickRegistedButton();

        // Проверка успешной регистрации
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница входа не отображается", loginPage.isLoginPageDisplayed());
    }

    @Test
    public void testPasswordTooShort() {
        registerPage.setName("Test User");
        registerPage.setRegisterEmail("test@example.com");
        registerPage.setRegisterPassword("12345"); // слишком короткий пароль
        registerPage.clickRegistedButton();

        // Проверка ошибки для некорректного пароля
        assertTrue(driver.getPageSource().contains("Некорректный пароль"));
    }

    @After
    public void tearDown() {
        // Закрытие драйвера
        if (driver != null) {
            driver.quit();
        }
    }
}