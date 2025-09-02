package pages.components;

import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CheckResultComponent {

    public void checkTableResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));
    }

    public void shouldNotHaveTableResult() {
        $(".modal-content").shouldNot();
    }
}
