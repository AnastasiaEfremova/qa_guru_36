package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static  void baseSteps() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager"; // Дожидаемся полной загрузки страницы
        // Configuration.browser = "chrome";
        //Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true; // Только на время отладки, страница браузера не закроется
    }
}
