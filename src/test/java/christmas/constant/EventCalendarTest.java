package christmas.constant;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.order.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventCalendarTest {

    @DisplayName("달력에 표시된 정보와 날짜를 비교해 이벤트가 해당되는지 확인한다.")
    @Test
    void isEvent() {
        // given
        VisitDate availableDate = VisitDate.from(3);
        VisitDate impossibleDate = VisitDate.from(4);
        String standardKeyWord = Event.SPECIAL.getStandardKeyWord();

        // when
        // then
        assertTrue(EventCalendar.isEvent(availableDate, standardKeyWord));
        assertFalse(EventCalendar.isEvent(impossibleDate, standardKeyWord));
    }

}