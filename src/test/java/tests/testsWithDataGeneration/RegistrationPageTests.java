package tests.testsWithDataGeneration;

import models.TestDataForRegistrationPage;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.BaseTest;

import static models.TestDataForRegistrationPage.file;


public class RegistrationPageTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    TestDataForRegistrationPage testData = new TestDataForRegistrationPage().generateRandomData();

    @Test
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
