package christmas.domain.order;

import christmas.constant.Constants;
import christmas.view.ErrorMessage;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VisitDate {

    private static final String DATE_PATTERN = "MM월 d일";
    private final LocalDate localDate;

    public VisitDate(final int day) {
        try {
            this.localDate = LocalDate.of(Constants.YEAR, Constants.MONTH, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_DATE_ERROR_MESSAGE.getMessage());
        }
    }

    public static VisitDate from(final int day) {
        return new VisitDate(day);
    }

    public DayOfWeek getDayOfWeek() {
        return localDate.getDayOfWeek();
    }

    public int getDayOfMonth() {
        return localDate.getDayOfMonth();
    }

    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
