package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class SelenideRepositorySearchTests {

    @Test
    void FindSelenideRepositoryAtTheTopTest() {

        Configuration.holdBrowserOpen = true;

        open("https://github.com");
        $("[data-target='qbsearch-input.inputButton']").click();
        $("[id='query-builder-test']").setValue("selenide").pressEnter();
        $$("[data-testid='results-list']").first().$("[href='/selenide/selenide']").click();
        $("#repository-container-header").shouldHave(text("selenide"));

    }
}