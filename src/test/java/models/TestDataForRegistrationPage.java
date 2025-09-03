package models;

import utils.RandomDataGeneration;

import java.util.Date;

import static utils.RandomDataGeneration.*;


public class TestDataForRegistrationPage {
    public String firstName;
    public String lastName;
    public String email;
    public String gender;
    public String phone;
    public String day;
    public String month;
    public String year;
    public String formattedDate;
    public String subject;
    public String hobbies;
    public String address;
    public String state;
    public String city;

    public static final String file = "dog.png";

    public TestDataForRegistrationPage generateRandomData() {
        Date birthDate = RandomDataGeneration.getRandomBirthDate();

        this.firstName = getRandomFirstName();
        this.lastName = getRandomLastName();
        this.email = getRandomUserEmail();
        this.gender = getRandomGender();
        this.phone = getRandomUserNumber();
        this.day = getRandomDay(birthDate);
        this.month = getRandomMonth(birthDate);
        this.year = getRandomYear(birthDate);
        this.formattedDate = getFormattedDate(birthDate);
        this.subject = getRandomSubject();
        this.hobbies = getRandomHobby();
        this.address = getRandomAddress();
        this.state = getRandomState();
        this.city = getRandomCity(state);


        return this;
    }
}