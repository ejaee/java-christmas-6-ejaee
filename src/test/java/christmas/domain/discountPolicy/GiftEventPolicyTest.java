package christmas.domain.discountPolicy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.constant.Gift;
import christmas.domain.benefit.Benefit;
import christmas.domain.order.Order;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventPolicyTest {

    private final OrderService orderService = new OrderService();
    private final GiftEventPolicy instance = GiftEventPolicy.getInstance();
    private Reservation discountReservation;
    private Reservation nonDiscountReservation;

    @BeforeEach
    void setUp() {
        VisitDate visitDate = orderService.generateVisitDate("3");
        Order discountOrder = orderService.generateOrder("티본스테이크-3,양송이수프-1");
        Order nonDiscountOrder = orderService.generateOrder("티본스테이크-1,양송이수프-1");

        discountReservation = orderService.generateReservation(visitDate, discountOrder);
        nonDiscountReservation = orderService.generateReservation(visitDate, nonDiscountOrder);
    }

    @DisplayName("증정 이벤트가 적용되는 날에는 증정 혜택을 반환한다")
    @Test
    void calculateBenefit() {
        // given

        // when
        Benefit benefit = instance.calculateBenefit(discountReservation);

        // then
        assertEquals(Gift.CHAMPAGNE.getEventName(), benefit.name());
        assertEquals(-25_000, benefit.discount());
    }

    @DisplayName("증정 이벤트가 적용되지 않는 날에는 없음을 반환한다")
    @Test
    void calculateNonBenefit() {
        // given

        // when
        Benefit nonBenefit = instance.calculateBenefit(nonDiscountReservation);

        // then
        assertEquals(Gift.NONE.getEventName(), nonBenefit.name());
        assertEquals(0, nonBenefit.discount());
    }
}