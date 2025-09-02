package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class EnterprisesPageTest {

    @Test
    public void enterprisesPageOpenTest() {

        // 1. Открыть главную страницу GitHub
        open("https://github.com");

        // 2. Выбрать в хедере Solutions с помощью команды hover
        $(".HeaderMenu-nav").find(byText("Solutions")).hover();

        // 3. Выбрать в списке Enterprise и перейти на страницу
        $(byTagAndText("a", "Enterprise")).click();

        // 4. Убедиться, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.")
        $("[data-testid='Hero']").shouldHave(text("The AI-powered developer platform"));

    }
}