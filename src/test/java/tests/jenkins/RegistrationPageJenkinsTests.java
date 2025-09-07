package tests.jenkins;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import models.TestDataForRegistrationPage;
import org.junit.jupiter.api.*;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.jenkins.helpers.Attach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static models.TestDataForRegistrationPage.file;


@Tag("registrationPage")
public class RegistrationPageJenkinsTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
//        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
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
}
