package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setupConfig() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 10000;

        // Минимальные опции для работы в Jenkins
        Configuration.browserCapabilities.setCapability("goog:chromeOptions",
                java.util.Map.of(
                        "args", java.util.List.of(
                                "--no-sandbox",
                                "--disable-dev-shm-usage",
                                "--remote-allow-origins=*"
                        )
                )
        );
    }
}