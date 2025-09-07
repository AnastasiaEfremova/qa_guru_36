package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private final String REPOSITORY = "selenide/selenide";
    private final String ISSUE = "3091";

    @Test
    public void testLambdaStep() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });

        step("Кликаем по ссылке репозитория", () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issues ", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issues с номером " + ISSUE, () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedTest() {
        WebSteps webSteps = new WebSteps();
        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.clickOnRepositoryLink(REPOSITORY);
        webSteps.openIssuesTub();
        webSteps.shouldHaveIssueWithNumber(ISSUE);
    }
}
