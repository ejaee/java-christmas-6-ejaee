package christmas.view;

public enum ErrorMessage {

    ERROR_PREFIX_MESSAGE("[ERROR] "),
    INPUT_BLANK_ERROR_MESSAGE("입력 값이 비어있습니다. 다시 입력해 주세요."),
    INPUT_DATE_ERROR_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU_ERROR_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE_MENU_ERROR_MESSAGE("음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요."),
    EXCEEDED_MENU_COUNT_ERROR_MESSAGE("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
