import PageObjects.ForgotPassPage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginTests {
    private WebDriver driver;
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    @Before
    public void setUp() {
        // Установка драйвера для Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loginViaMainAccountButton() {
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Форма логина не отображается", loginPage.isLoginFormDisplayed());
    }

    @Test
    public void loginViaPersonalAccountButton() {
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPaLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Форма логина не отображается", loginPage.isLoginFormDisplayed());
    }

    @Test
    public void loginViaRegistrationFormButton() {
        driver.get(BASE_URL + "/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickRegPageloginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Форма логина не отображается", loginPage.isLoginFormDisplayed());
    }

    @Test
    public void loginViaForgotPasswordFormButton() {
        driver.get(BASE_URL + "/forgot-password");

        ForgotPassPage forgotPassPage = new ForgotPassPage(driver);
        forgotPassPage.clickForgotPassPageLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Форма логина не отображается", loginPage.isLoginFormDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}