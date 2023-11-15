package christmas.domain.benefit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.constant.Event;
import christmas.constant.Gift;
import christmas.domain.discountPolicy.ChristmasDiscountPolicy;
import christmas.domain.discountPolicy.DayOfWeekDiscountPolicy;
import christmas.domain.discountPolicy.GiftEventPolicy;
import christmas.domain.discountPolicy.SpecialDiscountPolicy;
import christmas.domain.order.Order;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitCalculatorTest {

    private final OrderService orderService = new OrderService();
    private Reservation discountReservation;
    private Reservation nonDiscountReservation;

    @BeforeEach
    void setUp() {
        VisitDate discountDay = orderService.generateVisitDate("3");
        VisitDate nonDiscountDay = orderService.generateVisitDate("28");
        Order discountOrder = orderService.generateOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order nonDiscountOrder = orderService.generateOrder("양송이수프-1");

        discountReservation = orderService.generateReservation(discountDay, discountOrder);
        nonDiscountReservation = orderService.generateReservation(nonDiscountDay, nonDiscountOrder);
    }

    @DisplayName("여러 정책을 모두 계산한다")
    @Test
    void calculateBenefit() {
        // given
        BenefitCalculator instance = BenefitCalculator.getInstance();

        // when
        Benefit christmas = instance.calculateBenefit(discountReservation, ChristmasDiscountPolicy.getInstance());
        Benefit dayOfWeek = instance.calculateBenefit(discountReservation, DayOfWeekDiscountPolicy.getInstance());
        Benefit special = instance.calculateBenefit(discountReservation, SpecialDiscountPolicy.getInstance());
        Benefit gift = instance.calculateBenefit(discountReservation, GiftEventPolicy.getInstance());

        // then
        assertEquals(Event.CHRISTMAS.getEventName(), christmas.name());
        assertEquals(Event.WEEKDAY.getEventName(), dayOfWeek.name());
        assertEquals(Event.SPECIAL.getEventName(), special.name());
        assertEquals(Gift.CHAMPAGNE.getEventName(), gift.name());
    }

    @DisplayName("이벤트가 없는 경우 없음을 반환한다")
    @Test
    void test() {
        // given
        BenefitCalculator instance = BenefitCalculator.getInstance();

        // when
        Benefit christmas = instance.calculateBenefit(nonDiscountReservation, ChristmasDiscountPolicy.getInstance());
        Benefit dayOfWeek = instance.calculateBenefit(nonDiscountReservation, DayOfWeekDiscountPolicy.getInstance());
        Benefit special = instance.calculateBenefit(nonDiscountReservation, SpecialDiscountPolicy.getInstance());
        Benefit gift = instance.calculateBenefit(nonDiscountReservation, GiftEventPolicy.getInstance());

        // then
        assertEquals(Event.NONE.getEventName(), christmas.name());
        assertEquals(Event.NONE.getEventName(), dayOfWeek.name());
        assertEquals(Event.NONE.getEventName(), special.name());
        assertEquals(Gift.NONE.getEventName(), gift.name());
    }
}