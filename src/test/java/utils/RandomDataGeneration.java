package utils;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class RandomDataGeneration {

    private final static Faker faker = new Faker(new Locale("en-GB"));

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomUserEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomGender() {
        return faker.demographic().sex();
    }

    public static String getRandomUserNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static Date getRandomBirthDate() {
        return faker.date().birthday();
    }

    public static String getRandomDay(Date date) {
        return new SimpleDateFormat("dd", Locale.ENGLISH).format(date);
    }

    public static String getRandomMonth(Date date) {
        return new SimpleDateFormat("MMMM", Locale.ENGLISH).format(date);
    }

    public static String getRandomYear(Date date) {
        return new SimpleDateFormat("y", Locale.ENGLISH).format(date);
    }

    public static String getFormattedDate(Date date) {
        return new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH).format(date);
    }

    public static String getRandomSubject() {
        return faker.options().option(SUBJECT_OPTIONS);
    }

    public static String getRandomHobby() {
        return faker.options().option(HOBBY_OPTIONS);
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String getRandomState() {
        return faker.options().option(mapStateWithCity.keySet().toArray()).toString();
    }

    public static String getRandomCity(String state) {
        return faker.options().option(mapStateWithCity.get(state));
    }

    public static final String[] SUBJECT_OPTIONS = {
            "Accounting", "Maths", "Arts", "English", "Physics", "Chemistry",
            "Computer Science", "Economics", "Social Studies", "History",
            "Civics", "Commerce", "Hindi", "Biology"
    };

    public static final String[] HOBBY_OPTIONS = {
            "Reading", "Sports", "Music"
    };

    public static final Map<String, String[]> mapStateWithCity = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
    );
}
