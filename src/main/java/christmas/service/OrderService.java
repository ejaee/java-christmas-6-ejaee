package christmas.service;

import christmas.domain.order.MenuCount;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.utils.parser.KeyValueParser;
import christmas.utils.parser.NumberParser;
import christmas.view.ErrorMessage;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderService {

    private static final String BEVERAGE_CATEGORY = "음료";
    public static final int MEX_ORDER_COUNT = 20;

    public VisitDate generateVisitDate(final String inputDay) {
        int number = NumberParser.parseStringToInt(inputDay);
        return VisitDate.from(number);
    }

    public Order generateOrder(final String inputValue) {
        Map<String, Long> menuInfo = KeyValueParser.parseStringToMap(inputValue);
        Map<MenuName, MenuCount> order = putMenuInfo(menuInfo);

        validate(order);
        return new Order(order);
    }

    public Reservation generateReservation(final VisitDate visitDate, final Order order) {
        return new Reservation(visitDate, order);
    }

    public long getOrderCost(final Reservation reservation) {
        return reservation.order().getTotalOrderCost();
    }

    private static Map<MenuName, MenuCount> putMenuInfo(Map<String, Long> menuInfo) {
        Map<MenuName, MenuCount> order = new LinkedHashMap<>();

        menuInfo.forEach((menuItemName, count) -> {
            MenuName menuName = MenuName.findByName(menuItemName);
            MenuCount menuCount = MenuCount.of(count);
            order.put(menuName, menuCount);
        });
        return order;
    }

    private void validate(Map<MenuName, MenuCount> order) {
        validateMenuCategory(order);
        validateMenuTotalCount(order);
    }

    private void validateMenuCategory(Map<MenuName, MenuCount> order) {
        if (isOnlyBeverages(order)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private boolean isOnlyBeverages(Map<MenuName, MenuCount> order) {
        return order.keySet().stream()
                .allMatch(item -> item.getCategory().equals(BEVERAGE_CATEGORY));
    }

    private void validateMenuTotalCount(Map<MenuName, MenuCount> order) {
        long totalMenuCount = getTotalMenuCount(order);
        if (totalMenuCount > MEX_ORDER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
    }

    private long getTotalMenuCount(Map<MenuName, MenuCount> order) {
        return order.values().stream()
                .mapToLong(MenuCount::count)
                .sum();
    }
}
