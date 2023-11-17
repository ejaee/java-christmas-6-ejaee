package christmas.service;

import christmas.constant.Badge;

public class BadgeService {

    public Badge generateBadge(final long totalBenefitAmount) {
        return Badge.getBadgeByTotalBenefitAmount(totalBenefitAmount);
    }
}
