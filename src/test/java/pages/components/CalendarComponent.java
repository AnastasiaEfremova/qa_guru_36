package pages.components;

import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        $("select.react-datepicker__year-select").click();
        $("select.react-datepicker__year-select").selectOptionByValue("2000");
        $("select.react-datepicker__month-select").click();
        $("select.react-datepicker__month-select").selectOptionByValue("4");
        $("div.react-datepicker__day--021").click();
    }
}
