package tests.junit5;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты auto.ru")
@Tag("SMOKE")
public class WebTests {

    @BeforeEach
    void setUp() {
        open("https://auto.ru/");
    }


    @ValueSource(strings = {
            "Mitsubishi", "BMW", "Volvo"
    })
    @ParameterizedTest(name = "Для поискового запроса модели авто - {0} должен отображаться не пустой список карточек")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void successfulAutoSearchTest(String searchQuery) {
        $(".TextInput__control").click();
        $(".TextInput__control").setValue(searchQuery).pressEnter();
        $$(".ListingCars__items").shouldBe(CollectionCondition.sizeGreaterThan(0));

    }

    @CsvSource(value = {
            "Mitsubishi, ASX",
            "BMW, X1",
            "Volvo, S40"
    })
    @ParameterizedTest(name = "Для автомобиля марки {0} должна быть доступна модель {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void autoShouldHaveModels(String searchQuery, String model) {
        $(".TextInput__control").click();
        $(".TextInput__control").setValue(searchQuery).pressEnter();
        $(".ListingPopularMMM__chips").shouldHave(text(model));
    }

    static Stream<Arguments> autoShouldHaveCorrectListOfModels() {
        return Stream.of(
                Arguments.of("Mitsubishi", List.of("ASX", "Carisma", "Colt", "Galant", "L200")),
                Arguments.of("BMW", List.of("X1", "X3", "X4", "X5", "X6")),
                Arguments.of("Volvo", List.of("C30", "S40", "S60", "S80", "S90"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для автомобиля марки {0} должен быть доступен список моделей {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void autoShouldHaveCorrectListOfModels(String searchQuery, List<String> expectedModels) {
        $(".TextInput__control").click();
        $(".TextInput__control").setValue(searchQuery).pressEnter();

        // Проверяем, что для каждой ожидаемой модели есть хотя бы один элемент
        for (String expectedModel : expectedModels) {
            $$(".ListingPopularMMM__chips div")
                    .filterBy(Condition.text(expectedModel))
                    .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        }
    }
}
