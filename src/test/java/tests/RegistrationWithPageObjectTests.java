package tests;

import automationPracticeForm.BaseTest;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;


public class RegistrationWithPageObjectTests extends BaseTest {

    public static RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successFullFormTest() {

        registrationPage.openAutomationPracticeForm()
                .setFirstName("Anastasia")
                .setLastName("Efremova")
                .setUserEmail("anastasia.test@gmail.com")
                .setGender("Female")
                .setUserNumber("9994441312")
                .setDateOfBirth("21", "4", "1994")
                .setSubject("Physics")
                .setHobby("Music")
                .uploadPicture("dog.png")
                .setCurrentAddress("Kazan")
                .setState("NCR")
                .setCity("Delhi")
                .submit();

        registrationPage.checkResult("Student Name", "Anastasia Efremova")
                .checkResult("Student Email", "anastasia.test@gmail.com")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "9994441312")
                .checkResult("Date of Birth", "21 May,2000")
                .checkResult("Subjects", "Physics")
                .checkResult("Hobbies", "Music")
                .checkResult("Picture", "dog.png")
                .checkResult("Address", "Kazan")
                .checkResult("State and City", "NCR Delhi");
    }

    @Test
    void successMinFormTest() {

        registrationPage.openAutomationPracticeForm()
                .setFirstName("Anastasia")
                .setLastName("Efremova")
                .setGender("Female")
                .setUserNumber("9994441312")
                .submit();

        registrationPage.checkResult("Student Name", "Anastasia Efremova")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "9994441312");
    }

    @Test
    void negativeFormTest() {

        registrationPage.openAutomationPracticeForm()
                .setFirstName("Anastasia")
                .setLastName("Efremova")
                .setGender("Female")
                .setUserNumber("")
                .submit();

        registrationPage.shouldNotHaveTableResult();
    }


}
