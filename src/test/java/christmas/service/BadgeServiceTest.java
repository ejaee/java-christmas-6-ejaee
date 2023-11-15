package christmas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.constant.Badge;
import christmas.constant.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeServiceTest {

    private final BadgeService badgeService = new BadgeService();

    @DisplayName("산타 배지를 얻는다")
    @Test
    void getSantaBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.SANTA_BADGE_BASE_AMOUNT;

        // when
        Badge badge = badgeService.generateBadge(totalBenefitAmount);

        // then
        assertEquals(Badge.SANTA, badge);
    }

    @DisplayName("트리 배지를 얻는다")
    @Test
    void getTreeBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.TREE_BADGE_BASE_AMOUNT;

        // when
        Badge badge = badgeService.generateBadge(totalBenefitAmount);

        // then
        assertEquals(Badge.TREE, badge);
    }

    @DisplayName("스타 배지를 얻는다")
    @Test
    void getStarBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.STAR_BADGE_BASE_AMOUNT;

        // when
        Badge badge = badgeService.generateBadge(totalBenefitAmount);

        // then
        assertEquals(Badge.STAR, badge);
    }

    @DisplayName("해당되지 않으면 없음을 반환한다")
    @Test
    void getNoneBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.STAR_BADGE_BASE_AMOUNT - 1;

        // when
        Badge badge = badgeService.generateBadge(totalBenefitAmount);

        // then
        assertEquals(Badge.NONE, badge);
    }
}