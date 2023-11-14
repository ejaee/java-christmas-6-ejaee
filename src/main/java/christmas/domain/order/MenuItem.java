package christmas.domain.order;

public enum MenuItem {

    MUSHROOM_SOUP("에피타이저", "양송이수프", 6_000),
    TAPAS("에피타이저", "타파스", 5_500),
    CAESAR_SALAD("에피타이저", "시저셀러드", 8_000),

    T_BONE_STEAK("메인", "티본스테이크", 55_000),
    BBQ_RIBS("메인", "바비큐립", 54_000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35_000),

    CHOCOLATE_CAKE("디저트", "초코케이크", 15_000),
    ICECREAM("디저트", "아이스크림", 5_000),

    ZERO_COLA("음료", "제로콜라", 3_000),
    RED_WINE("음료", "레드와인", 60_000),
    CHAMPAGNE("음료", "샴페인", 25_000);

    private final String category;
    private final String name;
    private final long price;

    MenuItem(final String category, final String name, final long price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static MenuItem findByName(final String name) {
        for (MenuItem menuItem : MenuItem.values()) {
            if (menuItem.getName().equals(name)) {
                return menuItem;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
