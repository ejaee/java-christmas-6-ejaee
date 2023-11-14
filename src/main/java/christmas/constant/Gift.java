package christmas.constant;

import java.util.Arrays;

public enum Gift {

    CHAMPAGNE("증정 이벤트", "샴페인 1개", 25_000),
    NONE("없음", "없음", 0);

    private final String eventName;
    private final String giveaway;
    private final long profits;

    Gift(String eventName, String giveaway, long profits) {
        this.eventName = eventName;
        this.giveaway = giveaway;
        this.profits = profits;
    }

    public static Gift getGift(final long totalOrderCost) {
        if (totalOrderCost < Constants.GIFT_EVENT_BASE_PRICE) {
            return NONE;
        }
        return CHAMPAGNE;
    }

    public String getEventName() {
        return eventName;
    }

    public static Gift findByEventName(final String name) {
        return Arrays.stream(values())
                .filter(gift -> gift.getEventName().equals(name))
                .findFirst()
                .orElse(Gift.NONE);
    }

    public String getGiveaway() {
        return giveaway;
    }

    public long getProfits() {
        return profits;
    }
}
