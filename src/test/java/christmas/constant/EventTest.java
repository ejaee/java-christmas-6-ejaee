package christmas.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.order.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @DisplayName("예약 날짜를 고려해 ChristmasEvent 를 반환한다.")
    @Test
    void getChristmasEvent() {
        // given
        VisitDate possibleDate = new VisitDate(1);
        VisitDate impossibleDate = new VisitDate(30);

        // when
        Event christmasEvent = Event.getChristmasEvent(possibleDate);
        Event noneEvent = Event.getChristmasEvent(impossibleDate);

        // then
        assertEquals(Event.CHRISTMAS, christmasEvent);
        assertEquals(Event.NONE, noneEvent);
    }

    @DisplayName("예약 날짜를 고려해 DayOfWeekEvent 를 반환한다")
    @Test
    void getDayOfWeekEvent() {
        // given
        VisitDate weekday = new VisitDate(7);
        VisitDate weekend = new VisitDate(8);

        // when
        Event weekdayEvent = Event.getDayOfWeekEvent(weekday);
        Event weekendEvent = Event.getDayOfWeekEvent(weekend);

        // then
        assertEquals(Event.WEEKDAY, weekdayEvent);
        assertEquals(Event.WEEKEND, weekendEvent);
    }

    @DisplayName("예약 날짜를 고려해 SpecialEvent 를 반환한다.")
    @Test
    void getSpecialEvent() {
        // given
        VisitDate specialDay = new VisitDate(3);
        VisitDate normalDay = new VisitDate(4);

        // when
        Event specialEvent = Event.getSpecialEvent(specialDay);
        Event noneEvent = Event.getSpecialEvent(normalDay);

        // then
        assertEquals(Event.SPECIAL, specialEvent);
        assertEquals(Event.NONE, noneEvent);
    }

    @DisplayName("Event 정보를 가져올 수 있다.")
    @Test
    void getEventInfo() {
        // given
        Event weekdayEvent = Event.WEEKDAY;

        // when
        String eventName = weekdayEvent.getEventName();
        long discountStandardPrice = weekdayEvent.getDiscountStandardPrice();
        String standardKeyWord = weekdayEvent.getStandardKeyWord();

        // then
        assertEquals("평일 할인", eventName);
        assertEquals(-2_023, discountStandardPrice);
        assertEquals("디저트", standardKeyWord);
    }
}