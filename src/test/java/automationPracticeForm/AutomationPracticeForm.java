package automationPracticeForm;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm extends BaseTest {

    @Test
    void testAutomationPracticeForm() {

        // 1. Открыть страницу в браузере
        open("/automation-practice-form");

        // 2. Найти все элементы и заполнить данными
        $("#firstName").setValue("Anastasia");
        $("#lastName").setValue("Test");
        $("#userEmail").setValue("anastasia.test@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("9994441312");
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__year-select").click();
        $("select.react-datepicker__year-select").selectOptionByValue("2000");
        $("select.react-datepicker__month-select").click();
        $("select.react-datepicker__month-select").selectOptionByValue("4");
        $("div.react-datepicker__day--021").click();
        $("#subjectsInput").setValue("Physics");
        $$(".subjects-auto-complete__option").findBy(text("Physics")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("dog.png");  // для файла из ресурсов (src/test/resources)
        $("#currentAddress").setValue("Kazan");
        $("#subjectsInput").setValue("Math");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").pressEnter();


        // 3. Проверить корректность наполнения формы
        $(".table-responsive").shouldHave(text("Anastasia Test"));
        $(".table-responsive").shouldHave(text("Test"));
        $(".table-responsive").shouldHave(text("anastasia.test@gmail.com"));
        $(".table-responsive").shouldHave(text("9994441312"));
        $(".table-responsive").shouldHave(text("21 May,2000"));
        $(".table-responsive").shouldHave(text("Test"));
        $(".table-responsive").shouldHave(text("Physics"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("dog.png"));
        $(".table-responsive").shouldHave(text("Kazan"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

        // 4. Добавлены новые 2 теста
//        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
//        $(byText("Close")).click();

    }
}
