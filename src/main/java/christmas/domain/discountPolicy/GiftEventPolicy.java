package christmas.domain.discountPolicy;

import christmas.constant.Gift;
import christmas.domain.benefit.Benefit;
import christmas.domain.order.Reservation;

public class GiftEventPolicy implements EventPolicy {

    private static final GiftEventPolicy instance = new GiftEventPolicy();

    private GiftEventPolicy() {
    }

    public static GiftEventPolicy getInstance() {
        return instance;
    }

    @Override
    public Benefit calculateBenefit(final Reservation reservation) {
        long totalOrderCost = reservation.order().getTotalOrderCost();
        Gift gift = getGift(totalOrderCost);

        long discount = getDiscount(gift);
        return new Benefit(gift.getEventName(), discount);
    }

    private Gift getGift(long totalOrderCost) {
        return Gift.getGift(totalOrderCost);
    }

    private long getDiscount(Gift gift) {
        return gift.getProfits() * -1;
    }
}
