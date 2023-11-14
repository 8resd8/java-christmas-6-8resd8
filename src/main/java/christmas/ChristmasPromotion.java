package christmas;

import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.menu.Menu;
import christmas.order.EventDiscount;
import christmas.order.OrderData;
import christmas.order.TotalOrderAmount;
import christmas.util.ChangeDay;

import java.util.HashMap;

import static christmas.menu.Menu.MenuType.DESSERT;
import static christmas.menu.Menu.MenuType.MAIN_COURSE;


public class ChristmasPromotion {

    private final InputView inputView;
    private final ChangeDay changeDay;
    private final OutputView outputView;
    private final OrderData orderData;
    private final EventDiscount eventDiscount;
    private final TotalOrderAmount totalOrderAmount;

    public ChristmasPromotion(InputView inputView, ChangeDay changedDay, OutputView outputView, OrderData orderData, EventDiscount eventDiscount, TotalOrderAmount totalOrderAmount) {
        this.inputView = inputView;
        this.changeDay = changedDay;
        this.outputView = outputView;
        this.orderData = orderData;
        this.eventDiscount = eventDiscount;
        this.totalOrderAmount = totalOrderAmount;
    }

    public void eventStart() {
        int userDay = getUserDay(); // 사용자 방문 날짜
        int computerDay = getVisitDay(userDay); // 날짜 변환 (일요일 : 1)
        HashMap<String, Integer> menuCount = processUserMenuCount(); // 메뉴와 개수 입력

        printGuideAndMenu(userDay, menuCount);
        int totalOrderCost = calculateTotalOrderCost(menuCount); // 총주문 금액 계산

        boolean eventTicket = eventDiscount.eventMinimumCondition(totalOrderCost); // 이벤트 최소 참여 조건 체크
        if (!eventTicket) { // 1만원 미만 혜택 없음
            noEventCase((totalOrderCost));
            return;
        }

        processEvent(totalOrderCost, userDay, computerDay, orderData.getMenuTypeCount());
    }

    private void printGuideAndMenu(int userDay, HashMap<String, Integer> menuCount) {
        outputView.printShowBenefit(userDay); // 혜택 소개
        outputView.printOrderMenu(menuCount); // 메뉴 출력
    }

    private void processEvent(int totalOrderCost, int userDay, int computerDay, HashMap<String, Integer> menuTypeCount) {
        int giftCount = 1; // 증정 메뉴 수량 : 1개 설정(요구 사항)
        String giftMenu = eventDiscount.giftMenu(totalOrderCost, giftCount);
        int giftPrice = eventDiscount.giftPrice(Menu.CHAMPAGNE, giftCount, totalOrderCost); // 증정 메뉴 가격

        // 크리스마스D-day, 평일, 주말, 특별 할인
        int christmasDiscount = eventDiscount.getChristmasDayDiscount(userDay);
        int weekDiscount = eventDiscount.weekdayDiscount(menuTypeCount.get(DESSERT.toString()), computerDay);
        int weekendDiscount = eventDiscount.weekendDiscount(menuTypeCount.get(MAIN_COURSE.toString()), computerDay); // 각 메뉴마다 합을 저장해야해.
        int specialDiscount = eventDiscount.getSpecialDiscount(userDay);
        int totalOrderDiscount = christmasDiscount + weekDiscount + weekendDiscount + specialDiscount;

        String eventBadge = eventDiscount.eventBadge(totalOrderDiscount);

        printEventResults(giftMenu, christmasDiscount, weekDiscount, weekendDiscount, specialDiscount, giftPrice, totalOrderCost, totalOrderDiscount, eventBadge);
    }

    private void printEventResults(String giftMenu, int christmasDiscount, int weekDiscount, int weekendDiscount, int specialDiscount, int giftPrice, int totalOrderCost, int totalOrderDiscount, String eventBadge) {
        outputView.printGiftMenu(giftMenu); // 증정 메뉴
        outputView.printBenefitLists(christmasDiscount, weekDiscount, weekendDiscount, specialDiscount, giftPrice); // 혜택 내역
        outputView.printTotalOrderDiscount(totalOrderDiscount + giftPrice); // 총 혜택 금액
        outputView.printBill(totalOrderCost + totalOrderDiscount); // 할인 후 예상 결제 금액
        outputView.printEventBadge(eventBadge); // 이벤트 배지
    }

    // 메뉴 이름 가격 저장
    private HashMap<String, Integer> processUserMenuCount() {
        String userMenuCount = inputView.inputMenuCount();
        orderData.saveMenuCount(userMenuCount);
        return orderData.getMenuCount();
    }

    private int getVisitDay(int userDay) {
        return changeDay.getDay(userDay);
    }

    private int getUserDay() {
        return inputView.visitDay();
    }

    // 만원 미만 구입시
    public void noEventCase(int totalOrderCost) {
        outputView.printGiftMenu("없음"); // 증정메뉴
        outputView.printBenefitLists(0, 0, 0, 0, 0); // 혜택 내역
        outputView.printTotalOrderDiscount(0); // 총혜택 금액
        outputView.printBill(totalOrderCost);
        outputView.printEventBadge("없음");
    }

    // 총 주문 금액 계산
    public int calculateTotalOrderCost(HashMap<String, Integer> menuCount) {
        totalOrderAmount.calculateOrderTotal(menuCount); // 총 주문 금액 계산
        int totalOrderCost = totalOrderAmount.getTotalOrderAmount(); // 총 주문 금액
        outputView.printTotalOrderPrice(totalOrderCost); // 할인 전 총주문 금액 출력
        return totalOrderCost;
    }
}
