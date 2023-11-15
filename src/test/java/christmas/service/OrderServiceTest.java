package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.order.Order;
import christmas.view.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @DisplayName("orderService 를 통해 사용자 입력 값으로 Order 를 생성한다.")
    @Test
    void generateOrder() {
        // given
        String inputValue = "제로콜라-1,초코케이크-2";

        // when
        Order order = orderService.generateOrder(inputValue);

        // then
        assertThat(order).isNotNull();
    }

    @DisplayName("orderService 를 통해 order 의 총 주문 금액을 반환한다.")
    @Test
    void getOrderCost() {
        // given
        String inputValue = "제로콜라-1,초코케이크-2";
        Order order = orderService.generateOrder(inputValue);

        // when
        long orderCost = order.getTotalOrderCostBeforeDiscount();

        // then
        Assertions.assertEquals(33_000, orderCost);
    }

    @DisplayName("음료만 주문하는 경우 예외를 발생한다.")
    @Test
    void throwExceptionIfMenuAllBeverages() {
        // given
        String inputValue = "제로콜라-1,레드와인-3";

        // when
        // then
        assertThatThrownBy(() -> orderService.generateOrder(inputValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
    }

    @DisplayName("주문 메뉴 총 수량이 허용치를 초과하면 예외를 발생한다.")
    @Test
    void throwExceptionIfOrderCountExceeded() {
        // given
        String inputValue = "타파스-11,초코케이크-30";

        // when
        // then
        assertThatThrownBy(() -> orderService.generateOrder(inputValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
    }
}