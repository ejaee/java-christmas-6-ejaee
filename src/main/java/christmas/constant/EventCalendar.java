package christmas.constant;

import christmas.domain.order.VisitDate;
import java.util.Arrays;

public enum EventCalendar {

    THIRD(3, "별"),
    TENTH(10, "별"),
    SEVENTEENTH(17, "별"),
    TWENTY_FOURTH(24, "별"),
    TWENTY_FIFTH(25, "별"),
    THIRTY_FIRST(31, "별");

    private final int eventDay;
    private final String standardKeyWord;

    EventCalendar(int eventDay, String standardKeyWord) {
        this.eventDay = eventDay;
        this.standardKeyWord = standardKeyWord;
    }

    public static boolean isEvent(VisitDate visitDate, String keyWord) {
        return Arrays.stream(values())
                .anyMatch(event -> event.matches(visitDate, keyWord));
    }

    private boolean matches(VisitDate visitDate, String keyWord) {
        return eventDay == visitDate.getDayOfMonth() && standardKeyWord.equals(keyWord);
    }
}
