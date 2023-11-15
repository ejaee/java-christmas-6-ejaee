package christmas.domain.benefit;

import christmas.constant.Event;
import christmas.domain.discountPolicy.ChristmasDiscountPolicy;
import christmas.domain.discountPolicy.DayOfWeekDiscountPolicy;
import christmas.domain.discountPolicy.EventPolicy;
import christmas.domain.discountPolicy.GiftEventPolicy;
import christmas.domain.discountPolicy.SpecialDiscountPolicy;
import christmas.domain.order.Reservation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BenefitsGenerator {

    public static BenefitsGenerator getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final BenefitsGenerator INSTANCE = new BenefitsGenerator();
    }

    public Benefits generateBenefits(final Reservation reservation) {
        List<EventPolicy> discountPolicies = Arrays.asList(
                ChristmasDiscountPolicy.getInstance(),
                DayOfWeekDiscountPolicy.getInstance(),
                SpecialDiscountPolicy.getInstance(),
                GiftEventPolicy.getInstance()
        );

        List<Benefit> benefitByType = calculateBenefitsForPolicies(reservation, discountPolicies);
        if (isNoBenefit(benefitByType)) {
            Benefit noBenefit = new Benefit(Event.NONE.getEventName(), Event.NONE.getDiscountStandardPrice());
            return new Benefits(List.of(noBenefit));
        }

        removeNoBenefit(benefitByType);
        return new Benefits(benefitByType);
    }

    private void removeNoBenefit(List<Benefit> benefitByType) {
        benefitByType.removeIf(benefit -> benefit.discount() == 0);
    }

    private boolean isNoBenefit(List<Benefit> benefitByType) {
        return benefitByType.stream()
                .allMatch(benefit -> benefit.discount() == 0);
    }

    private List<Benefit> calculateBenefitsForPolicies(final Reservation reservation, List<EventPolicy> policies) {
        return policies.stream()
                .map(policy -> generateBenefits(reservation, policy))
                .collect(Collectors.toList());
    }

    private Benefit generateBenefits(final Reservation reservation, EventPolicy policy) {
        return BenefitCalculator.getInstance().calculateBenefit(reservation, policy);
    }
}
