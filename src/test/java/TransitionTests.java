import PageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;

public class TransitionTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private PaPage paPage;
    private LoginPage loginPage;
    private final String EMAIL = "enot2111@gmail.com";
    private final String PASSWORD = "21111995";
    private final int TIMEOUT = 10;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, TIMEOUT);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        paPage = new PaPage(driver);

        performLogin();
    }

    @After
    public void tearDown() {
        try {
            performLogout();
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void personalAccountNavigation_afterLogin_shouldOpenAccountPage() {
        mainPage.clickPaLoginButton();
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        assertTrue("Должен быть на странице ЛК",
                driver.getCurrentUrl().contains("/account/profile"));
    }

    @Test
    public void navigationFromAccountConstructorTest() {
        mainPage.clickPaLoginButton();
        paPage.clickConstructButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getConstructorLocator()));
        assertTrue("Должны вернуться на главную через конструктор",
                mainPage.isBurgerConstructorVisible());
    }

    @Test
    public void navigationFromAccountLogoTest() {
        mainPage.clickPaLoginButton();
        paPage.clickSbLogo();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getConstructorLocator()));
        assertTrue("Должны вернуться на главную через лого",
                mainPage.isBurgerConstructorVisible());
    }

    @Test
    public void logoutFromAccountTest() {
        mainPage.clickPaLoginButton();
        paPage.clickExitButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginFormLocator()));
        assertTrue("Должны быть на странице входа",
                loginPage.isLoginFormDisplayed());
    }

    @Test
    public void defaultSection_shouldBeBuns() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getBunsButton()));
        assertTrue("Булки должны быть активны по умолчанию",
                mainPage.isBunsActive());
    }

    @Test
    public void saucesSection_shouldActivateWhenClicked() {
        driver.findElement(mainPage.getSaucesButton()).click();
        wait.until(ExpectedConditions.attributeContains(
                mainPage.getSaucesButton(), "class", "tab_tab_type_current__2BEPc"
        ));
        assertTrue("Соусы должны активироваться при клике",
                mainPage.isSaucesActive());
    }

    @Test
    public void fillingsSection_shouldActivateWhenClicked() {
        driver.findElement(mainPage.getFillingsButton()).click();
        wait.until(ExpectedConditions.attributeContains(
                mainPage.getFillingsButton(), "class", "tab_tab_type_current__2BEPc"
        ));
        assertTrue("Начинки должны активироваться при клике",
                mainPage.isFillingsActive());
    }


    private void performLogin() {
        if (!isUserLoggedIn()) {
            mainPage.clickEnterLoginButton();
            loginPage.login(EMAIL, PASSWORD);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loginPage.getLoginFormLocator()));
        }
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