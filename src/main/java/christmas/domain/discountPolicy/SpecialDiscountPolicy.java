package christmas.domain.discountPolicy;

import christmas.constant.Event;
import christmas.domain.benefit.Benefit;
import christmas.domain.order.Reservation;

public class SpecialDiscountPolicy implements EventPolicy {

    private static final SpecialDiscountPolicy instance = new SpecialDiscountPolicy();

    private SpecialDiscountPolicy() {
    }

    public static SpecialDiscountPolicy getInstance() {
        return instance;
    }

    @Override
    public Benefit calculateBenefit(final Reservation reservation) {
        Event event = getEvent(reservation);

        return new Benefit(event.getEventName(), event.getDiscountStandardPrice());
    }

    private static Event getEvent(Reservation reservation) {
        return Event.getSpecialEvent(reservation.visitDate());
    }
}
