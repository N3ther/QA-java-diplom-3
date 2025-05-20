package browser.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.*;

public class BrowserFactory {
    private static final String YANDEX_PATH = Paths.get(
            System.getProperty("user.home"),
            "AppData",
            "Local",
            "Yandex",
            "YandexBrowser",
            "Application",
            "browser.exe"
    ).toString();

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        return browser.equals("yandex")
                ? setupYandexBrowser()
                : setupChrome();
    }

    private static WebDriver setupChrome() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver setupYandexBrowser() {
        // Для версии Chromium 120.0.6099.110
        WebDriverManager.chromedriver()
                .driverVersion("120.0.6099.110")
                .setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_PATH);
        options.addArguments("--remote-allow-origins=*"); // Важно для новых версий

        return new ChromeDriver(options);
    }
}