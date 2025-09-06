package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CheckResultComponent {

    private final SelenideElement tableResponsive = $(".table-responsive"),
            modalContent = $(".modal-content");

    public void checkTableResult(String key, String value) {
        tableResponsive.$(byText(key)).parent()
                .shouldHave(text(value));
    }

    public void shouldNotHaveTableResult() {
        modalContent.shouldNot();
    }
}
