package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void timeout() {
        Configuration.pageLoadStrategy = "eager"; // Не будем дожидаться полной загрузки страницы
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true; // После выполнения теста браузер останется открытым

    }

    @Test
    void fillFormTest() {

        open( "/text-box");
        $("#userName").setValue("Anastasia");
        $("#userEmail").setValue("anastasiaTest@gmail.com");
        $("#currentAddress").setValue("Kazan");
        $("#permanentAddress").setValue("Moscow");
        Configuration.timeout = 5000;
        $("#submit").click();

        // Проверяем корректность заполнения формы
        $("#output").$("#name").shouldHave(text("Anastasia"));
        $("#output").$("#email").shouldHave(text("anastasiaTest@gmail.com"));
        $("#output").$("#currentAddress").shouldHave(text("Kazan"));
        $("#output").$("#permanentAddress").shouldHave(text("Moscow"));
    }
}