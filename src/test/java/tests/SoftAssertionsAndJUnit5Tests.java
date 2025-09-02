package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsAndJUnit5Tests {

    @Test
    void softAssertionsAndJUnit5Test () {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://github.com";

        // 1. Откройте страницу Selenide в Github
        open("/selenide/selenide");
        // 2. Перейдите в раздел Wiki проекта
        $("[id=wiki-tab]").click();
        // 3. Убедитесь, что в списке страниц (Pages) есть страница Soft assertions
        $$("[id=wiki-body]").shouldHave(texts("Soft assertions"));
        // 4. Откройте страницу SoftAssertions
        $(byText("Soft assertions")).click();
        // 5. Проверьте что внутри есть пример кода для JUnit5
        $$(".heading-element").findBy(text("JUnit5"))
                .shouldHave(matchText(".*Using JUnit5.*"));

        $(".markdown-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}