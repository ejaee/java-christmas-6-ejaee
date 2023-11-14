package christmas.constant;

import christmas.domain.order.VisitDate;
import christmas.utils.DateUtils;

public enum Event {

    CHRISTMAS("크리스마스 디데이 할인", -1_000, "100"),
    WEEKDAY("평일 할인", -2_023, "디저트"),
    WEEKEND("주말 할인", -2_023, "메인"),
    SPECIAL("특별 할인", -1_000, "별"),
    NONE("없음", 0, "없음");

    private final String eventName;
    private final long discountStandardPrice;
    private final String standardKeyWord;

    Event(String eventName, long discountStandardPrice, String standardKeyWord) {
        this.eventName = eventName;
        this.discountStandardPrice = discountStandardPrice;
        this.standardKeyWord = standardKeyWord;
    }

    public static Event getChristmasEvent(final VisitDate visitDate) {
        if (visitDate.getDayOfMonth() > Constants.LAST_DAY_OF_CHRISTMAS_EVENT) {
            return NONE;
        }
        return CHRISTMAS;
    }

    public static Event getDayOfWeekEvent(final VisitDate visitDate) {
        if (DateUtils.isWeekend(visitDate.getDayOfWeek())) {
            return WEEKEND;
        }
        return WEEKDAY;
    }

    public static Event getSpecialEvent(final VisitDate visitDate) {
        if (EventCalendar.isEvent(visitDate, SPECIAL.getStandardKeyWord())) {
            return SPECIAL;
        }
        return NONE;
    }

    public String getEventName() {
        return eventName;
    }

    public long getDiscountStandardPrice() {
        return discountStandardPrice;
    }

    public String getStandardKeyWord() {
        return standardKeyWord;
    }
}
