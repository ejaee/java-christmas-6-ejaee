package christmas.service;

import christmas.constant.Constants;
import christmas.constant.Gift;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.Benefits;
import christmas.domain.benefit.BenefitsGenerator;
import christmas.domain.order.Reservation;
import java.util.Objects;

public class EventService {

    public Benefits calculateBenefits(final Reservation reservation) {

        BenefitsGenerator eventGenerator = BenefitsGenerator.getInstance();
        return eventGenerator.generateBenefits(reservation);
    }

    public Gift getGift(final Benefits benefits) {
        String targetEventName = Constants.GIFT_EVENT_NAME;

        for (Benefit benefit : benefits.benefits()) {
            if (Objects.equals(targetEventName, benefit.name())) {
                return Gift.findByEventName(benefit.name());
            }
        }
        return Gift.NONE;
    }

    public long getTotalOrderCostAfterDiscount(final Reservation reservation, final Benefits benefits) {
        long totalOrderCostBeforeDiscount = reservation.order().getTotalOrderCostBeforeDiscount();
        long totalBenefitPrice = benefits.getTotalDiscount();
        long giftValue = getGiftValue(benefits);

        return totalOrderCostBeforeDiscount + totalBenefitPrice + giftValue;
    }

    private long getGiftValue(Benefits benefits) {
        Gift gift = getGift(benefits);
        return gift.getProfits();
    }
}
