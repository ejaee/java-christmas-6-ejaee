package christmas.domain.discountPolicy;

import christmas.domain.benefit.Benefit;
import christmas.domain.order.Reservation;

public interface EventPolicy {

    Benefit calculateBenefit(final Reservation reservation);
}
