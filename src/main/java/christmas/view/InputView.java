package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Constants;

public class InputView {

    public String inputVisitDate() {
        System.out.printf((Message.VISIT_DATE_REQUEST_MESSAGE.getMessage()) + "\n", Constants.MONTH);
        return input();
    }

    public String inputOrderMenu() {
        System.out.println(Message.ORDER_REQUEST_MESSAGE.getMessage());
        return input();
    }

    private String input() {
        return Console.readLine();
    }
}
