package christmas.constant;

public enum Badge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");

    private final String type;

    Badge(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Badge getBadgeByTotalBenefitAmount(final long totalBenefitAmount) {
        if (totalBenefitAmount >= Constants.SANTA_BADGE_BASE_AMOUNT) {
            return SANTA;
        } else if (totalBenefitAmount >= Constants.TREE_BADGE_BASE_AMOUNT) {
            return TREE;
        } else if (totalBenefitAmount >= Constants.STAR_BADGE_BASE_AMOUNT) {
            return STAR;
        }
        return NONE;
    }
}
