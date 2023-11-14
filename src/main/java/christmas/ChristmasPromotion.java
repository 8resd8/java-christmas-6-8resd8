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
        int userDay = getUserDay(); // 현실 세계 날짜
        int computerDay = getVisitDay(userDay); // 날짜 변환
        HashMap<String, Integer> menuCount = processUserMenuCount(); // 메뉴와 개수 입력

        outputView.printShowBenefit(userDay); // 혜택 소개
        outputView.printOrderMenu(menuCount); // 메뉴 출력

        // 총주문 금액 계산
        int totalOrderCost = calculateTotalOrderCost(menuCount);

        boolean eventTicket = eventDiscount.eventMinimumCondition(totalOrderCost); // 이벤트 최소 참여 조건 체크

        // 1만원 이상이면 이벤트 진행
        if (eventTicket) {
            noEventCase((totalOrderCost));
            return;
        }

        processEvent(totalOrderCost, userDay, computerDay);
    }

    private void processEvent(int totalOrderCost, int userDay, int computerDay) {
        HashMap<String, Integer> menuTypeCount = orderData.getMenuTypeCount();

        int giftCount = 1; // 증정 메뉴 수량 : 1개 설정(요구 사항)
        String giftMenu = eventDiscount.giftMenu(totalOrderCost, giftCount);
        int giftPrice = eventDiscount.giftPrice(Menu.CHAMPAGNE, giftCount, totalOrderCost); // 증정 메뉴 가격
        outputView.printGiftMenu(giftMenu); // 증명 메뉴 출력

        // 1. 크리스마스 할인
        int christmasDiscount = eventDiscount.getChristmasDayDiscount(userDay);

        // 2. 평일 = 디저트 메뉴 할인
        int weekDiscount = eventDiscount.weekdayDiscount(menuTypeCount.get(DESSERT.toString()), computerDay);

        // 3. 주말 = 메인 메뉴 할인
        int weekendDiscount = eventDiscount.weekendDiscount(menuTypeCount.get(MAIN_COURSE.toString()), computerDay); // 각 메뉴마다 합을 저장해야해.

        // 4. 특별 할인 = 별이 있으면 총 주문 금액에서 1,000원 할인
        int specialDiscount = eventDiscount.getSpecialDiscount(userDay);

        // 5. 증정 이벤트 표시 = 총 주문 금액이 12만원 이상이라면 샴페인 1개
        outputView.printBenefitLists(christmasDiscount, weekDiscount, weekendDiscount, specialDiscount, giftPrice);

        // 총 혜택 금액
        int totalOrderDiscount = christmasDiscount + weekDiscount + weekendDiscount + specialDiscount;
        outputView.printTotalOrderDiscount(totalOrderDiscount + giftPrice);

        // 할인 후 에상 금액, 혜택금액은 (-)값
        int finalOrderAmount = totalOrderCost + totalOrderDiscount;
        outputView.printBill(finalOrderAmount);

        // 증정 이벤트는 포함하지 않는 혜택 금액을 줘야함.
        String eventBadge = eventDiscount.eventBadge(totalOrderDiscount);
        outputView.printEventBadge(eventBadge);

    }


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
