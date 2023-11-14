package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Constants;

public class InputView {

    public static final String VISIT_DATE_REQUEST_MESSAGE = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String ORDER_REQUEST_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String inputVisitDate() {
        System.out.printf((VISIT_DATE_REQUEST_MESSAGE) + "\n", Constants.MONTH);
        return input();
    }

    public String inputOrderMenu() {
        System.out.println(ORDER_REQUEST_MESSAGE);
        return input();
    }

    private String input() {
        return Console.readLine();
    }
}
