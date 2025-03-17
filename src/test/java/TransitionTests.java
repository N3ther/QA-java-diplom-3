import browser.factory.BrowserFactory;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import models.UserApi;
import models.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.pracktikum.page.objects.LoginPage;
import ru.yandex.pracktikum.page.objects.MainPage;
import ru.yandex.pracktikum.page.objects.PaPage;

import static org.junit.Assert.*;

public class TransitionTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private PaPage paPage;
    private LoginPage loginPage;
    private UserApi userApi;
    private String accessToken;
    private UserModel testUser;
    private Faker faker;
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    @Before
    public void setUp() {
        driver = BrowserFactory.createDriver();
        wait = new WebDriverWait(driver, 15);
        faker = new Faker();
        userApi = new UserApi();

        // Инициализация Page Objects
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        paPage = new PaPage(driver);

        // Создание пользователя
        testUser = new UserModel(
                faker.internet().emailAddress(),
                faker.internet().password(8, 12),
                faker.name().username()
        );

        Response registerResponse = userApi.registerUser(testUser);
        assertEquals(200, registerResponse.getStatusCode());
        accessToken = registerResponse.jsonPath().getString("accessToken");
        assertNotNull("Access Token не получен", accessToken);

        performLogin();
    }

    private void performLogin() {
        driver.get(BASE_URL);
        mainPage.clickEnterLoginButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getOrderButton()));
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
    public void personalAccountNavigation() {
        mainPage.clickPaLoginButton();
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        assertTrue(driver.getCurrentUrl().contains("/account/profile"));
    }

    @Test
    public void navigationFromAccountConstructorTest() {
        mainPage.clickPaLoginButton();
        paPage.clickConstructButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getBunsSection()));
        assertTrue("Должны вернуться на главную через конструктор",
                mainPage.isBurgerConstructorVisible());
    }

    @Test
    public void navigationFromAccountLogoTest() {
        mainPage.clickPaLoginButton();
        paPage.clickSbLogo();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getBunsSection()));
        assertTrue("Должны вернуться на главную через лого",
                mainPage.isBurgerConstructorVisible());
    }

    @Test
    public void logoutFromAccountTest() {
        mainPage.clickPaLoginButton();
        paPage.clickExitButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginFormLocator()));
        assertTrue("Должны быть на странице входа",
                loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void BunsSectionTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getBunsSection()));
        assertTrue("Булки должны быть активны по умолчанию",
                mainPage.isBunsActive());
    }

    @Test
    public void saucesSectionTest() {
        WebElement saucesTab = wait.until(ExpectedConditions.elementToBeClickable(
                mainPage.getSaucesSection()
        ));
        saucesTab.click();

        wait.until(ExpectedConditions.attributeContains(
                mainPage.getSaucesSection(), "class", "tab_tab_type_current__2BEPc"
        ));
        assertTrue("Соусы должны активироваться при клике",
                mainPage.isSaucesActive());
    }

    @Test
    public void fillingsSectionTest() {
        WebElement fillingsTab = wait.until(ExpectedConditions.elementToBeClickable(
                mainPage.getFillingsSection()
        ));
        fillingsTab.click();

        wait.until(ExpectedConditions.attributeContains(
                mainPage.getFillingsSection(), "class", "tab_tab_type_current__2BEPc"
        ));
        assertTrue("Начинки должны активироваться при клике",
                mainPage.isFillingsActive());
    }


    private void performLogout() {
        if (isUserLoggedIn()) {
            try {
                driver.get("https://stellarburgers.nomoreparties.site/account/profile");
                wait.until(ExpectedConditions.elementToBeClickable(paPage.getExitButton())).click();
                wait.until(ExpectedConditions.urlContains("/login"));
            } catch (Exception e) {
                System.out.println("Logout exception handled: " + e.getMessage());
            }
        }
    }

    private boolean isUserLoggedIn() {
        try {
            driver.get("https://stellarburgers.nomoreparties.site");
            return !mainPage.isEnterLoginButtonVisible();
        } catch (Exception e) {
            return false;
        }
    }
}