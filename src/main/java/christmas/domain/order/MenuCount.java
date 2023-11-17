package christmas.domain.order;

import christmas.view.ErrorMessage;

public record MenuCount(long count) {

    public static MenuCount of(long count) {
        if (count <= 0) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR_MESSAGE.getMessage());
        }
        return new MenuCount(count);
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
