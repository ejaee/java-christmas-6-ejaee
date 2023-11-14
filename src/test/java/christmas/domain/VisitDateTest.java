package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.order.VisitDate;
import christmas.utils.parser.NumberParser;
import java.time.DayOfWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("날짜로 유효한 숫자로 객체를 생성할 수 있다.")
    @ValueSource(strings = {"1", "25"})
    @ParameterizedTest
    void generateVisitDate(Integer inputDay) {
        //given

        //when
        VisitDate visitDate = VisitDate.from(inputDay);

        //then
        assertThat(visitDate).isNotNull();
    }

    @DisplayName("날짜로 유효하지 않은 숫자는 예외가 발생한다.")
    @ValueSource(strings = {"0", "32", "-1"})
    @ParameterizedTest
    void throwExceptionIfIllegalDay(Integer inputDay) {
        //given

        //when
        //then
        assertThatThrownBy(() -> VisitDate.from(inputDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumberParser.ILLEGAL_DATE_EXCEPTION_MESSAGE);
    }

    @DisplayName("날짜를 통해 무슨 요일인지 알 수 있다.")
    @Test
    void getDayOfWeek() {
        // given
        int day = 1;

        // when
        VisitDate visitDate = VisitDate.from(day);

        // then
        assertEquals(DayOfWeek.FRIDAY, visitDate.getDayOfWeek());
    }

    @DisplayName("날짜를 통해 몇 일인지 알 수 있다.")
    @Test
    void getDayOfMonth() {
        // given
        int day = 1;

        // when
        VisitDate visitDate = VisitDate.from(day);

        // then
        assertEquals(1, visitDate.getDayOfMonth());
    }
}