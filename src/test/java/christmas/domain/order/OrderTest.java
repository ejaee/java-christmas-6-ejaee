package christmas.domain.order;

import christmas.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    private final OrderService orderService = new OrderService();

    @DisplayName("Order 에서 저장한 메뉴들의 총 주문 금액을 반환한다.")
    @Test
    void getTotalOrderCost() {
        // given

        // when
        Order order = orderService.generateOrder("제로콜라-1,티본스테이크-2");
        long totalOrderCost = order.getTotalOrderCost();

        // then
        Assertions.assertEquals(113_000, totalOrderCost);
    }
}