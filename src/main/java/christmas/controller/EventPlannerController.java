package christmas.controller;

import christmas.domain.benefit.Benefits;
import christmas.domain.order.Order;
import christmas.domain.order.Reservation;
import christmas.domain.order.VisitDate;
import christmas.service.BadgeService;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {

    private final InputView inputView;
    private final OutputView outputView;
    private final OrderService orderService;
    private final EventService eventService;
    private final BadgeService badgeService;

    public EventPlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = new OrderService();
        this.eventService = new EventService();
        this.badgeService = new BadgeService();
    }

    public void run() {
        printStart();

        Reservation reservation = inputReservation();
        printReservationInfo(reservation);

        Benefits benefits = eventService.calculateBenefits(reservation);
        printBenefitsInfo(benefits, reservation);

        printBadge(benefits);
    }

    private void printBadge(Benefits benefits) {
        outputView.printBadge(badgeService.generateBadge(benefits.getTotalDiscount()));
    }

    private void printBenefitsInfo(Benefits benefits, Reservation reservation) {
        outputView.printGiftMenu(eventService.getGift(benefits));
        outputView.printBenefits(benefits);
        outputView.printTotalBenefitAmount(benefits.getTotalDiscount());
        outputView.printTotalOrderAmountAfterDiscount(
                eventService.getTotalOrderCostAfterDiscount(reservation, benefits));
    }

    private void printReservationInfo(Reservation reservation) {
        outputView.printVisitDate(reservation.visitDate());
        outputView.printOrderMenu(reservation.order());
        outputView.printTotalOrderAmountBeforeDiscount(reservation.order().getTotalOrderCostBeforeDiscount());
    }

    private void printStart() {
        outputView.printStartMessage();
    }

    private Reservation inputReservation() {
        VisitDate visitDate = inputVisitDate();
        Order order = inputOrder();
        return orderService.generateReservation(visitDate, order);
    }

    private Order inputOrder() {
        try {
            String inputValue = inputView.inputOrderMenu();
            return orderService.generateOrder(inputValue);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return inputOrder();
        }
    }

    private VisitDate inputVisitDate() {
        try {
            String inputValue = inputView.inputVisitDate();
            return orderService.generateVisitDate(inputValue);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return inputVisitDate();
        }
    }
}
