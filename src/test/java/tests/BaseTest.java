package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setupConfig() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager"; // Дожидаемся полной загрузки страницы
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }
}
