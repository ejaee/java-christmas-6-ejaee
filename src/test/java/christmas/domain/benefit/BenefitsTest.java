package christmas.domain.benefit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitsTest {

    private Benefits benefits;

    @BeforeEach
    void setUp() {
        Benefit christmas = new Benefit("크리스마스 디데이 할인", -1_200);
        Benefit weekday = new Benefit("평일 할인", -4_046);
        Benefit special = new Benefit("특별 할인", -1_000);
        Benefit gift = new Benefit("증정 이벤트", -25_000);

        benefits = new Benefits(List.of(christmas, weekday, special, gift), false);
    }

    @DisplayName("Benefits 의 총 혜택 금액을 반환한다")
    @Test
    void getTotalDiscount() {
        // given

        // when
        long totalDiscount = benefits.getTotalDiscount();

        // then
        assertEquals(-31_246, totalDiscount);
    }
}