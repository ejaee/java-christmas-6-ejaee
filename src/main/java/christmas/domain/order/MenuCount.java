package christmas.domain.order;

public record MenuCount(long count) {

    public static MenuCount of(long count) {
        if (count <= 0) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return new MenuCount(count);
    }
}
