package christmas.view;

public enum Message {

    START_MESSAGE_TEMPLATE("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다."),
    VISIT_DATE_MESSAGE_TEMPLATE("%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_MESSAGE_PREFIX("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_MESSAGE_PREFIX("<할인 전 총주문 금액>"),
    AMOUNT_MESSAGE_TEMPLATE("%,d원"),
    GIFT_MENU_MESSAGE_PREFIX("<증정 메뉴>"),
    BENEFITS_PREFIX("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT_MESSAGE_PREFIX("<총혜택 금액>"),
    TOTAL_ORDER_AMOUNT_AFTER_DISCOUNT_MESSAGE_PREFIX("<할인 후 예상 결제 금액>"),
    BADGE_MESSAGE_PREFIX("<12월 이벤트 배지>"),

    VISIT_DATE_REQUEST_MESSAGE("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_REQUEST_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
