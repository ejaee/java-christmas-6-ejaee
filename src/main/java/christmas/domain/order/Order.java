package christmas.domain.order;

import java.util.Map;
import java.util.stream.Collectors;

public record Order(Map<MenuName, MenuCount> order) {

    private static final String ORDER_MENU_TEMPLATE = "%s %s개";

    public long getTotalOrderCostBeforeDiscount() {
        return order.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue().count())
                .sum();
    }

    @Override
    public String toString() {
        return order.entrySet().stream()
                .map(entry -> String.format(ORDER_MENU_TEMPLATE, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
