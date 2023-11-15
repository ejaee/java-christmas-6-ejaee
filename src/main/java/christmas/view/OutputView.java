package christmas.view;

import christmas.constant.Badge;
import christmas.constant.Constants;
import christmas.constant.Gift;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.Benefits;
import christmas.domain.order.Order;
import christmas.domain.order.VisitDate;

public class OutputView {

    public void printBadge(final Badge badge) {
        printMessage(Message.BADGE_MESSAGE_PREFIX);
        print(badge.getType());
    }

    public void printTotalOrderAmountAfterDiscount(final long discountedCost) {
        printMessage(Message.TOTAL_ORDER_AMOUNT_AFTER_DISCOUNT_MESSAGE_PREFIX);
        print(String.format(Message.AMOUNT_MESSAGE_TEMPLATE.getMessage(), discountedCost));
        printNewLine();
    }

    public void printTotalBenefitAmount(final long totalDiscount) {
        printMessage(Message.TOTAL_BENEFIT_AMOUNT_MESSAGE_PREFIX);
        print(String.format(Message.AMOUNT_MESSAGE_TEMPLATE.getMessage(), totalDiscount));
        printNewLine();
    }


    public void printBenefits(final Benefits benefits) {
        printMessage(Message.BENEFITS_PREFIX);
        printBenefit(benefits);
        printNewLine();
    }

    private void printBenefit(Benefits benefits) {
        benefits.benefits().stream()
                .map(Benefit::toString)
                .forEach(this::print);

        printEventInfo(benefits);
    }

    private void printEventInfo(Benefits benefits) {
        if (benefits.hasNotBenefit()) {
            print(Message.EVENT_INFO_PREFIX.getMessage() + Message.EVENT_INFO_MESSAGE.getMessage());
        }
    }

    public void printGiftMenu(final Gift gift) {
        printMessage(Message.GIFT_MENU_MESSAGE_PREFIX);
        print(gift.getGiveaway());
        printNewLine();
    }

    public void printTotalOrderAmountBeforeDiscount(final long totalOrderCost) {
        printMessage(Message.TOTAL_ORDER_AMOUNT_MESSAGE_PREFIX);
        print(String.format(Message.AMOUNT_MESSAGE_TEMPLATE.getMessage(), totalOrderCost));
        printNewLine();
    }

    public void printOrderMenu(final Order order) {
        printMessage(Message.ORDER_MENU_MESSAGE_PREFIX);
        print(order.toString());
        printNewLine();
    }

    public void printVisitDate(final VisitDate visitDate) {
        print(String.format(Message.VISIT_DATE_MESSAGE_TEMPLATE.getMessage(), visitDate.toString()));
        printNewLine();
    }

    public void printStartMessage() {
        print(String.format(Message.START_MESSAGE_TEMPLATE.getMessage(), Constants.MONTH));
    }

    public void printErrorMessage(String errorMessage) {
        print(ErrorMessage.ERROR_PREFIX_MESSAGE.getMessage() + errorMessage);
    }

    private void printMessage(Message message) {
        System.out.println(message.getMessage());
    }

    private void printNewLine() {
        System.out.println();
    }

    private void print(String message) {
        System.out.println(message);
    }
}
