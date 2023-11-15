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

class DayOfWeekDiscountPolicyTest {

    private final OrderService orderService = new OrderService();
    private final DayOfWeekDiscountPolicy instance = DayOfWeekDiscountPolicy.getInstance();
    private Reservation weekdayReservation;
    private Reservation weekendReservation;

    @BeforeEach
    void setUp() {
        VisitDate weekday = orderService.generateVisitDate("7");
        VisitDate weekend = orderService.generateVisitDate("8");
        Order order = orderService.generateOrder("초코케이크-3,티본스테이크-2");

        weekdayReservation = orderService.generateReservation(weekday, order);
        weekendReservation = orderService.generateReservation(weekend, order);
    }

    @DisplayName("평일 할인이 적용되는 날에는 평일 혜택을 반환한다")
    @Test
    void calculateWeekdayBenefit() {
        // given

        // when
        Benefit weekdayBenefit = instance.calculateBenefit(weekdayReservation);

        // then
        assertEquals(Event.WEEKDAY.getEventName(), weekdayBenefit.name());
    }

    @DisplayName("주말 할인이 적용되는 날에는 주말 혜택을 반환한다")
    @Test
    void calculateWeekendBenefit() {
        // given

        // when
        Benefit weekendBenefit = instance.calculateBenefit(weekendReservation);

        // then
        assertEquals(Event.WEEKEND.getEventName(), weekendBenefit.name());
    }

    @DisplayName("평일 할인 정책에 맞는 할인 금액을 계산한다")
    @Test
    void calculateWeekdayDiscount() {
        // given

        // when
        Benefit weekdayBenefit = instance.calculateBenefit(weekdayReservation);

        // then
        int discountedPricePerDessert = -2023;
        int dessertCount = 3;
        int totalDiscountPrice = discountedPricePerDessert * dessertCount;

        assertEquals(totalDiscountPrice, weekdayBenefit.discount());
    }

    @DisplayName("주말 할인 정책에 맞는 할인 금액을 계산한다")
    @Test
    void calculateWeekendDiscount() {
        // given

        // when
        Benefit weekdayBenefit = instance.calculateBenefit(weekendReservation);

        // then
        int discountedPricePerMain = -2023;
        int mainCount = 2;
        int totalDiscountPrice = discountedPricePerMain * mainCount;

        assertEquals(totalDiscountPrice, weekdayBenefit.discount());
    }
}