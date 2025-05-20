import browser.factory.BrowserFactory;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.UserApi;
import models.UserModel;
import ru.yandex.pracktikum.page.objects.ForgotPassPage;
import ru.yandex.pracktikum.page.objects.LoginPage;
import ru.yandex.pracktikum.page.objects.MainPage;
import ru.yandex.pracktikum.page.objects.RegisterPage;
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
    private UserApi userApi;
    private UserModel testUser;
    private String accessToken;
    private Faker faker;

    @Before
    public void setUp() {
        // Установка драйвера для Chrome
        driver = BrowserFactory.createDriver();
        faker = new Faker();
        userApi = new UserApi();

        // Создание тестового пользователя через API
        testUser = new UserModel(
                faker.internet().emailAddress(),
                faker.internet().password(8, 12),
                faker.name().username()
        );

        Response registerResponse = userApi.registerUser(testUser);
        accessToken = registerResponse.jsonPath().getString("accessToken");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    public void loginViaMainAccountButton() {
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Логин не выполнен", mainPage.isOrderButtonVisible());
    }

    @Test
    public void loginViaPersonalAccountButton() {
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPaLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Форма логина не отображается", loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void loginViaRegistrationFormButton() {
        driver.get(BASE_URL + "/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickRegPageloginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Форма логина не отображается", loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void loginViaForgotPasswordFormButton() {
        driver.get(BASE_URL + "/forgot-password");

        ForgotPassPage forgotPassPage = new ForgotPassPage(driver);
        forgotPassPage.clickForgotPassPageLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Форма логина не отображается", loginPage.isLoginButtonDisplayed());
    }
}