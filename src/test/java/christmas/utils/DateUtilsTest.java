package christmas.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @DisplayName("날짜를 통해 이벤트 플레네에서 규정한 평일, 주말을 반환한다")
    @Test
    void isWeekend() {
        // given
        DayOfWeek weekday = DayOfWeek.MONDAY;
        DayOfWeek weekend = DayOfWeek.FRIDAY;

        // when
        // then
        assertFalse(DateUtils.isWeekend(weekday));
        assertTrue(DateUtils.isWeekend(weekend));
    }
}