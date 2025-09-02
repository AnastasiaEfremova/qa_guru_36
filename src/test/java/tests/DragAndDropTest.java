package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DragAndDropTest {

    @Test
    public void dragAndDropTest() {

        // 1. Открыть https://the-internet.herokuapp.com/drag_and_drop
        open("https://the-internet.herokuapp.com/drag_and_drop");

        // 2. Запомнить исходные значения
        String columnA = $("#column-a").text();
        String columnB = $("#column-b").text();

        // 3. Перенести прямоугольник А на место B через Actions
        actions()
                .clickAndHold($("#column-a"))
                .moveToElement($("#column-b"))
                .release()
                .perform();

        // 4. Проверить, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text(columnB));
        $("#column-b").shouldHave(text(columnA));

    }
}