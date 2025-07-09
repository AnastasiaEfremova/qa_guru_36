package automationPracticeForm;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
        $("select. css-1wa3eu0-placeholder").selectOptionByValue("4");
        $("div.react-datepicker__day--021").click();

        $("#subjectsInput").setValue("Test");

        $("label[for='hobbies-checkbox-2']").click();

        $("#uploadPicture").uploadFromClasspath("dog.png");  // для файла из ресурсов (src/test/resources)



    }
}
