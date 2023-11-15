package christmas.service;

import christmas.constant.Constants;
import christmas.constant.Gift;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.BenefitCalculator;
import christmas.domain.benefit.Benefits;
import christmas.domain.discountPolicy.ChristmasDiscountPolicy;
import christmas.domain.discountPolicy.DayOfWeekDiscountPolicy;
import christmas.domain.discountPolicy.EventPolicy;
import christmas.domain.discountPolicy.GiftEventPolicy;
import christmas.domain.discountPolicy.SpecialDiscountPolicy;
import christmas.domain.order.Reservation;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventService {

    public Benefits calculateBenefits(final Reservation reservation) {
        List<EventPolicy> discountPolicies = Arrays.asList(
                ChristmasDiscountPolicy.getInstance(),
                DayOfWeekDiscountPolicy.getInstance(),
                SpecialDiscountPolicy.getInstance(),
                GiftEventPolicy.getInstance()
        );

        List<Benefit> benefitByType = calculateBenefitsForPolicies(reservation, discountPolicies);

        return new Benefits(benefitByType);
    }

    private List<Benefit> calculateBenefitsForPolicies(final Reservation reservation, List<EventPolicy> policies) {
        return policies.stream()
                .map(policy -> calculateBenefits(reservation, policy))
                .collect(Collectors.toList());
    }

    private Benefit calculateBenefits(final Reservation reservation, EventPolicy policy) {
        return BenefitCalculator.getInstance().calculateBenefit(reservation, policy);
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
