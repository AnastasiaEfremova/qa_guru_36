package tests.jenkins;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.CheckResultComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPageJenkins {

    CalendarComponent calendarComponent = new CalendarComponent();
    CheckResultComponent checkResultComponent = new CheckResultComponent();

    private static final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submit = $("#submit");

    @Step("Открыть /automation-practice-form")
    public RegistrationPageJenkins openAutomationPracticeForm() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Заполнить поле имя")
    public RegistrationPageJenkins setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Заполнить поле фамилия")
    public RegistrationPageJenkins setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Заполнить поле с email")
    public RegistrationPageJenkins setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    @Step("Выбрать пол")
    public RegistrationPageJenkins setGender(String gender) {
        genderInput.$(byText(gender)).click();
        return this;
    }

    @Step("Заполнить поле с номером телефона")
    public RegistrationPageJenkins setUserNumber(String phone) {
        userNumberInput.setValue(phone);
        return this;
    }

    @Step("Указать дату рождения")
    public RegistrationPageJenkins setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Выбрать предмет")
    public RegistrationPageJenkins setSubject(String subject) {
        subjectInput.setValue(subject).pressEnter();
        return this;
    }

    @Step("Выбрать хобби")
    public RegistrationPageJenkins setHobby(String subject) {
        hobbiesInput.$(byText(subject)).click();
        return this;
    }

    @Step("Загрузить картинку")
    public RegistrationPageJenkins uploadPicture(String pathToPicture) {
        uploadPictureInput.uploadFromClasspath(pathToPicture);
        return this;
    }

    @Step("Указать текущий адрес")
    public RegistrationPageJenkins setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    @Step("Выбрать штат")
    public RegistrationPageJenkins setState(String state) {
        stateInput.setValue(state).pressEnter();
        return this;
    }

    @Step("Выбрать город")
    public RegistrationPageJenkins setCity(String city) {
        cityInput.setValue(city).pressEnter();
        return this;
    }

    @Step("Подтвердить")
    public void submit() {
        submit.pressEnter();
    }

    @Step("Проверить результат для {key} и {value}")
    public RegistrationPageJenkins checkResult(String key, String value) {
        checkResultComponent.checkTableResult(key, value);
        return this;
    }
}
