package christmas.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("산타 배지 기준 금액으로 산타 배지를 얻는다")
    @Test
    void getSantaBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.SANTA_BADGE_BASE_AMOUNT;

        // when
        Badge badge = Badge.getBadgeByTotalBenefitAmount(totalBenefitAmount);

        // then
        assertEquals(Badge.SANTA, badge);
    }

    @DisplayName("트리 배지 기준 금액으로 트리 배지를 얻는다")
    @Test
    void getTreeBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.TREE_BADGE_BASE_AMOUNT;

        // when
        Badge badge = Badge.getBadgeByTotalBenefitAmount(totalBenefitAmount);

        // then
        assertEquals(Badge.TREE, badge);
    }

    @DisplayName("스타 배지 기준 금액으로 스타 배지를 얻는다")
    @Test
    void getStarBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.STAR_BADGE_BASE_AMOUNT;

        // when
        Badge badge = Badge.getBadgeByTotalBenefitAmount(totalBenefitAmount);

        // then
        assertEquals(Badge.STAR, badge);
    }

    @DisplayName("어느 배지 기준 금액에도 해당되지 않으면 없음을 반환한다")
    @Test
    void getNoneBadgeByTotalBenefitAmount() {
        // given
        final long totalBenefitAmount = Constants.STAR_BADGE_BASE_AMOUNT + 1;

        // when
        Badge badge = Badge.getBadgeByTotalBenefitAmount(totalBenefitAmount);

        // then
        assertEquals(Badge.NONE, badge);
    }
}