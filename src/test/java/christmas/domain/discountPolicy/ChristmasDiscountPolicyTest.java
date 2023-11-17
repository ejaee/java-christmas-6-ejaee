package christmas.domain.discountPolicy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.constant.Event;
import christmas.domain.benefit.Benefit;
import christmas.domain.order.Order;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountPolicyTest {

    private final OrderService orderService = new OrderService();
    private final ChristmasDiscountPolicy instance = ChristmasDiscountPolicy.getInstance();
    private Reservation discountReservation;
    private Reservation nonDiscountReservation;

    @BeforeEach
    void setUp() {
        VisitDate discountDay = orderService.generateVisitDate("3");
        VisitDate nonDiscountDay = orderService.generateVisitDate("26");
        Order order = orderService.generateOrder("제로콜라-3,양송이수프-1");

        discountReservation = orderService.generateReservation(discountDay, order);
        nonDiscountReservation = orderService.generateReservation(nonDiscountDay, order);
    }

    @DisplayName("크리스마스 할인이 적용되는 날에는 크리스마스 혜택을 반환한다")
    @Test
    void calculateBenefit() {
        // given

        // when
        Benefit benefit = instance.calculateBenefit(discountReservation);

        // then
        assertEquals(Event.CHRISTMAS.getEventName(), benefit.name());
        assertEquals(-1_200, benefit.discount());
    }

    @DisplayName("크리스마스 할인이 적용되지 않는 날에는 없음을 반환한다")
    @Test
    void calculateNonBenefit() {
        // given

        // when
        Benefit nonBenefit = instance.calculateBenefit(nonDiscountReservation);

        // then
        assertEquals(Event.NONE.getEventName(), nonBenefit.name());
        assertEquals(0, nonBenefit.discount());
    }
}