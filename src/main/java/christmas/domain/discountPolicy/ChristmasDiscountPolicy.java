package christmas.domain.discountPolicy;

import christmas.constant.Constants;
import christmas.constant.Event;
import christmas.domain.benefit.Benefit;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;

public class ChristmasDiscountPolicy implements EventPolicy {

    private static final ChristmasDiscountPolicy instance = new ChristmasDiscountPolicy();

    private ChristmasDiscountPolicy() {
    }

    public static ChristmasDiscountPolicy getInstance() {
        return instance;
    }

    @Override
    public Benefit calculateBenefit(final Reservation reservation) {
        Event event = getEvent(reservation);

        if (event.equals(Event.NONE)) {
            return new Benefit(event.getEventName(), event.getDiscountStandardPrice());
        }
        long discount = calculateDiscount(reservation.visitDate(), event);
        return new Benefit(event.getEventName(), discount);
    }

    private static Event getEvent(Reservation reservation) {
        return Event.getChristmasEvent(reservation.visitDate());
    }

    private long calculateDiscount(final VisitDate visitDate, Event event) {
        return event.getDiscountStandardPrice() + calculateAdditionalDiscount(visitDate);
    }

    private long calculateAdditionalDiscount(VisitDate visitDate) {
        return (long) (visitDate.getDayOfMonth() - 1) * Constants.BASE_DISCOUNT_AMOUNT_OF_CHRISTMAS_EVENT;
    }
}
