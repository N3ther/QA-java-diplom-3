import browser.factory.BrowserFactory;
import com.github.javafaker.Faker;
import models.UserApi;
import models.UserModel;
import ru.yandex.pracktikum.page.objects.MainPage;
import ru.yandex.pracktikum.page.objects.RegisterPage;
import ru.yandex.pracktikum.page.objects.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RegistrationTests {
    private WebDriver driver;
    private RegisterPage registerPage;
    private UserApi userApi;
    private Faker faker;
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private List<String> accessTokens = new ArrayList<>();

    @Before
    public void setUp() {
        driver = BrowserFactory.createDriver();
        faker = new Faker();
        userApi = new UserApi();
        driver.get(BASE_URL + "/register");
        registerPage = new RegisterPage(driver);
    }


    @Test
    public void testSuccessfulRegistration() {
        UserModel newUser = new UserModel(
                faker.internet().emailAddress(),
                faker.internet().password(8, 12),
                faker.name().username()
        );

        registerPage.setName(newUser.getName());
        registerPage.setRegisterEmail(newUser.getEmail());
        registerPage.setRegisterPassword(newUser.getPassword());
        registerPage.clickRegistedButton();

        // Проверка успешной регистрации и автологина
        MainPage mainPage = new MainPage(driver);
        assertTrue("Регистрация не выполнена", mainPage.isOrderButtonVisible());

        // Получаем токен для последующего удаления
        String token = userApi.loginUser(newUser).jsonPath().getString("accessToken");
        accessTokens.add(token);
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
        // Удаление пользователей с валидацией токена
        accessTokens.stream()
                .filter(token -> token != null && !token.isEmpty());
        accessTokens.clear();

        if (driver != null) {
            driver.quit();
        }
    }
}