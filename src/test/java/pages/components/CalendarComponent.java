package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement monthSelect = $(".react-datepicker__month-select"),
            yearSelect = $(".react-datepicker__year-select");

    private void selectDay(String day) {
        String dayLocator = String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day);
        $(dayLocator).click();
    }

    public void setDate(String day, String month, String year) {
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        selectDay(day);

    }
}