package christmas.domain;

import christmas.Constants;
import christmas.utils.parser.NumberParser;
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
            throw new IllegalArgumentException(NumberParser.ILLEGAL_DATE_EXCEPTION_MESSAGE);
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
