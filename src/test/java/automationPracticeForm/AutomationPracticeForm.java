package automationPracticeForm;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void baseSteps() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true; // После выполнения теста браузер останется открытым
        Configuration.pageLoadStrategy = "eager"; // Не будем дожидаться полной загрузки страницы
    }

    @Test
    void testAutomationPracticeForm() {

        // 1. Открыть страницу в браузере
        open("/automation-practice-form");

        // 2. Найти все элементы и заполнить данными
        $("#firstName").setValue("Anastasia");
        $("#lastName").setValue("Test");
        $("#userEmail").setValue("anastasia.test@gmail.com");
        $("label[for='gender-radio-2']").click(); // Селектор ищет элемент <label> с атрибутом for="gender-radio-2"
        $("#userNumber").setValue("9994441312");

        // Заполнить данные поля Date Of Birth
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__year-select").click();
        $("select.react-datepicker__year-select").selectOptionByValue("2000");
        $("select.react-datepicker__month-select").click();
        $("select.react-datepicker__month-select").selectOptionByValue("4");
        $("div.react-datepicker__day--021").click();

        $("#subjectsInput").setValue("Physics");
        $$(".subjects-auto-complete__option").findBy(text("Physics")).click();

        $("label[for='hobbies-checkbox-2']").click();

        $("#uploadPicture").uploadFromClasspath("dog.png");  // для файла из ресурсов (src/test/resources)

        $("#currentAddress").setValue("Kazan");

        $("#subjectsInput").setValue("Math");

        // Блок State and City
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").pressEnter();


        // Проверить корректность наполнения формы
        $(".table-responsive").shouldHave(text("Anastasia Test"));
        $(".table-responsive").shouldHave(text("Test"));
        $(".table-responsive").shouldHave(text("anastasia.test@gmail.com"));
        $(".table-responsive").shouldHave(text("9994441312"));
        $(".table-responsive").shouldHave(text("21 May,2000"));
        $(".table-responsive").shouldHave(text("Test"));
        $(".table-responsive").shouldHave(text("Physics"));
        $(".table-responsive").shouldHave(text("Reading"));
        $(".table-responsive").shouldHave(text("dog.png"));
        $(".table-responsive").shouldHave(text("Kazan"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));


    }
}
