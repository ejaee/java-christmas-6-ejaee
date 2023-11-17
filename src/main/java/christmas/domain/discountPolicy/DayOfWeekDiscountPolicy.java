package christmas.domain.discountPolicy;

import christmas.constant.Event;
import christmas.domain.benefit.Benefit;
import christmas.domain.order.Order;
import christmas.domain.order.Reservation;

public class DayOfWeekDiscountPolicy implements EventPolicy {

    private static final DayOfWeekDiscountPolicy instance = new DayOfWeekDiscountPolicy();

    private DayOfWeekDiscountPolicy() {
    }

    public static DayOfWeekDiscountPolicy getInstance() {
        return instance;
    }

    @Override
    public Benefit calculateBenefit(final Reservation reservation) {
        Event event = getEvent(reservation);

        long count = countTargetCategory(reservation.order(), event);
        long discount = getDiscount(count, event);

        return new Benefit(event.getEventName(), discount);
    }

    private long countTargetCategory(final Order order, Event dayOfWeekEvent) {
        return order.order().entrySet().stream()
                .filter(entry -> entry.getKey().getCategory().equals(dayOfWeekEvent.getStandardKeyWord()))
                .mapToLong(entry -> entry.getValue().count())
                .sum();
    }

    private Event getEvent(Reservation reservation) {
        return Event.getDayOfWeekEvent(reservation.visitDate());
    }

    private long getDiscount(final long count, Event dayOfWeekEvent) {
        return count * dayOfWeekEvent.getDiscountStandardPrice();
    }
}
