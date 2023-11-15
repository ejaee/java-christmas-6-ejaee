package christmas.domain.order;

import christmas.constant.Constants;
import java.util.Map;
import java.util.stream.Collectors;

public record Order(Map<MenuName, MenuCount> order) {

    public long getTotalOrderCostBeforeDiscount() {
        return order.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue().count())
                .sum();
    }

    @Override
    public String toString() {
        return order.entrySet().stream()
                .map(entry -> String.format(Constants.ORDER_MENU_TEMPLATE, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(Constants.NEW_LINE_CHARACTER));
    }
}
