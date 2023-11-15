package christmas.domain.benefit;

import christmas.constant.Constants;
import christmas.constant.Event;
import christmas.domain.discountPolicy.EventPolicy;
import christmas.domain.order.Reservation;

public class BenefitCalculator {

    public static BenefitCalculator getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final BenefitCalculator INSTANCE = new BenefitCalculator();
    }

    public Benefit calculateBenefit(final Reservation reservation, final EventPolicy eventPolicy) {
        long totalOrderCost = reservation.order().getTotalOrderCost();

        if (totalOrderCost < Constants.EVENT_MINIMUM_CONDITION_PRICE) {
            return new Benefit(Event.NONE.getEventName(), Event.NONE.getDiscountStandardPrice());
        }

        return eventPolicy.calculateBenefit(reservation);
    }
}
