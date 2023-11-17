package christmas.domain.benefit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.order.Order;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitsGeneratorTest {

    private final OrderService orderService = new OrderService();
    private final BenefitsGenerator benefitsGenerator = BenefitsGenerator.getInstance();
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
        Benefits firstBenefits = benefitsGenerator.generateBenefits(firstReservation);
        Benefits secondBenefits = benefitsGenerator.generateBenefits(secondReservation);

        // then
        assertBenefits(-31_246, firstBenefits);
        assertBenefits(0, secondBenefits);
    }

    private void assertBenefits(int expectedTotalDiscount, Benefits benefits) {
        assertEquals(benefits.getTotalDiscount(), expectedTotalDiscount);
    }
}