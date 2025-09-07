package tests.jenkins;

import models.TestDataForRegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import tests.BaseTest;

import com.codeborne.selenide.Configuration;
import static models.TestDataForRegistrationPage.file;


@Tag("registrationPage")
public class RegistrationPageJenkinsTests {

    @BeforeAll
    static void setupConfig() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        // Минимальные опции для работы в Jenkins
//        Configuration.browserCapabilities.setCapability("goog:chromeOptions",
//                java.util.Map.of(
//                        "args", java.util.List.of(
//                                "--no-sandbox",
//                                "--disable-dev-shm-usage",
//                                "--remote-allow-origins=*"
//                        )
//                )
//        );
    }

    RegistrationPageJenkins registrationPage = new RegistrationPageJenkins();

    TestDataForRegistrationPage testData = new TestDataForRegistrationPage().generateRandomData();

    @Test
    @DisplayName("Успешное заполнение всех полей формы регистрации")
    void successFullFormTest() {

        registrationPage.openAutomationPracticeForm()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.email)
                .setGender(testData.gender)
                .setUserNumber(testData.phone)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setSubject(testData.subject)
                .setHobby(testData.hobbies)
                .uploadPicture(file)
                .setCurrentAddress(testData.address)
                .setState(testData.state)
                .setCity(testData.city)
                .submit();

        registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.email)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phone)
                .checkResult("Date of Birth", testData.formattedDate)
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.hobbies)
                .checkResult("Picture", "dog.png")
                .checkResult("Address", testData.address)
                .checkResult("State and City", testData.state + " " + testData.city);
    }

    @Test
    @DisplayName("Успешное заполнение минимально допустимых полей формы регистрации")
    void successMinFormTest() {

        registrationPage.openAutomationPracticeForm()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.phone)
                .submit();

        registrationPage.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.phone);
    }

    @Test
    @DisplayName("Ошибка из-за незаполненного поля с номером телефона")
    void negativeFormTest() {

        registrationPage.openAutomationPracticeForm()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber("")
                .submit();

        registrationPage.shouldNotHaveTableResult();
    }


}
