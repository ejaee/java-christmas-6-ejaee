package christmas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.constant.Gift;
import christmas.domain.benefit.Benefits;
import christmas.domain.order.Order;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest {

    private final OrderService orderService = new OrderService();
    private final EventService eventService = new EventService();
    private Reservation firstReservation;
    private Reservation secondReservation;

    @BeforeEach
    void setUp() {
        VisitDate visitDate = orderService.generateVisitDate("3");
        Order firstOrder = orderService.generateOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order secondOrder = orderService.generateOrder("타파스-1,제로콜라-1 ");
        firstReservation = orderService.generateReservation(visitDate, firstOrder);
        secondReservation = orderService.generateReservation(visitDate, secondOrder);
    }

    @DisplayName("Reservation 을 통해 Benefit을 반환한다")
    @Test
    void calculateBenefits() {
        // given

        // when
        Benefits firstBenefits = eventService.calculateBenefits(firstReservation);
        Benefits secondBenefits = eventService.calculateBenefits(secondReservation);

        // then
        assertBenefits(-31_246, firstBenefits);
        assertBenefits(0, secondBenefits);
    }

    @DisplayName("Reservation 을 통해 Gift를 반환한다")
    @Test
    void getGift() {
        // given
        Benefits firstBenefits = eventService.calculateBenefits(firstReservation);
        Benefits secondBenefits = eventService.calculateBenefits(secondReservation);

        // when
        Gift firstGift = eventService.getGift(firstBenefits);
        Gift secondGift = eventService.getGift(secondBenefits);

        // then
        assertGift(Gift.CHAMPAGNE, firstGift);
        assertGift(Gift.NONE, secondGift);
    }

    @DisplayName("Reservation 과 Benefits 를 통해 할인 후 결제 금액을 반환한다")
    @Test
    void getTotalOrderCostAfterDiscount() {
        // given
        Benefits firstBenefits = eventService.calculateBenefits(firstReservation);
        Benefits secondBenefits = eventService.calculateBenefits(secondReservation);

        // when
        long firstTotalOrderCostAfterDiscount = eventService.getTotalOrderCostAfterDiscount(
                firstReservation,
                firstBenefits
        );
        long secondTotalOrderCostAfterDiscount = eventService.getTotalOrderCostAfterDiscount(
                secondReservation,
                secondBenefits
        );

        // then
        assertEquals(135_754, firstTotalOrderCostAfterDiscount);
        assertEquals(8_500, secondTotalOrderCostAfterDiscount);
    }

    private void assertBenefits(int expectedTotalDiscount, Benefits benefits) {
        assertEquals(benefits.getTotalDiscount(), expectedTotalDiscount);
    }

    private void assertGift(Gift expectedGift, Gift gift) {
        assertEquals(expectedGift.getEventName(), gift.getEventName());
        assertEquals(expectedGift.getProfits(), gift.getProfits());
    }
}