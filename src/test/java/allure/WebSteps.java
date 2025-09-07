package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    SelenideElement searchButton = $(".header-search-button"),
            searchString = $("#query-builder-test"),
            issuesTub = $("#issues-tab");

    @Step("Открыть главную страницу github ")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Найти репозиторий {repository}")
    public void searchForRepository(String repository) {
        searchButton.click();
        searchString.sendKeys(repository);
        searchString.submit();
    }

    @Step("Кликнуть на {repository}")
    public void clickOnRepositoryLink(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Кликнуть на Issues ")
    public void openIssuesTub() {
        issuesTub.click();
    }

    @Step("Issues содержит номер - {number}")
    public void shouldHaveIssueWithNumber(String number) {
        $(withText(number)).should(Condition.exist);
    }
}
