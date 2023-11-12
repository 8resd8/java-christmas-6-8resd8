package christmas;

import christmas.menu.Menu;

import java.util.HashMap;

import static christmas.menu.Menu.MenuType.*;


public class ChristmasPromotion {

    private final InputView inputView = new InputView();
    private final ChangedDay changedDay = new ChangedDay();
    private final OutputView outputView = new OutputView();
    private final OrderData orderData = new OrderData();
    private final EventDiscount eventDiscount = new EventDiscount();
    private final TotalOrderAmount totalOrderAmount = new TotalOrderAmount();

    public void eventStart() {
        int userDay = inputView.visitDay(); // 현실 세계 날짜
        int visitDay = changedDay.getDay(userDay); // 날짜 변환


//        String userMenuCount = inputView.inputMenuCount(); // 메뉴와 개수 입력
//        orderData.saveMenuCount(userMenuCount); // 메뉴를 저장
//        HashMap<String, Integer> menuCount = orderData.getMenuCount(); // 메뉴 저장
//        HashMap<String, Integer> menuTypeCount = orderData.getMenuTypeCount(); // 메뉴 타입별 개수 확인 가능

        String userMenuCount = inputView.inputMenuCount(); // 메뉴와 개수 입력

        orderData.saveMenuCount(userMenuCount); // 메뉴를 저장
        HashMap<String, Integer> menuCount = orderData.getMenuCount(); // 메뉴 저장
        HashMap<String, Integer> menuTypeCount = orderData.getMenuTypeCount(); // 메뉴 타입별 개수 확인 가능

        boolean onlyBeverage = false;
        boolean menuOverLength = false;
        int beverageCount = menuTypeCount.get(BEVERAGE.toString()); // 음료의 개수
        int allmenu = 0;

        for (String menu : menuCount.keySet()) {
            allmenu += menuCount.get(menu);
        }
        if (allmenu == beverageCount) {
            onlyBeverage = true;
        }
        if (allmenu > 20) {
            menuOverLength = true;
        }


//        onlyBeverageCheck(onlyBeverage, menuOverLength);




        // 메뉴는 최대 20개 까지만 주문 가능
        outputView.printShowBenefit(userDay); // 혜택 소개


        outputView.printOrderMenu(menuCount); // 메뉴 출력


        // 총주문 금액 계산
        int totalOrderCost = calculateTotalOrderCost(menuCount);

        boolean eventTicket = eventDiscount.eventMinimumCondition(totalOrderCost); // 이벤트 최소 참여 조건 체크

        // 만원 미만 구매시
        if (noEventCase(eventTicket, totalOrderCost)) {
            return;
        }

        // 증정 메뉴 = 샴페인 1개.
        int giftCount = 1; //  // 증정 메뉴 수량 : 1개 설정(요구 사항)
        String giftMenu = eventDiscount.giftMenu(totalOrderCost, giftCount);
        int giftPrice = eventDiscount.giftPrice(Menu.CHAMPAGNE, giftCount, totalOrderCost); // 증정 메뉴 가격
        outputView.printGiftMenu(giftMenu); // 증명 메뉴 출력


        // 1. 크리스마스 할인
        int christmasDiscount = eventDiscount.getChristmasDayDiscount(userDay);

        // 2. 평일 = 디저트 메뉴 할인
        int weekDiscount = eventDiscount.weekdayDiscount(menuTypeCount.get(DESSERT.toString()), visitDay);

        // 3. 주말 = 메인 메뉴 할인
        int weekendDiscount = eventDiscount.weekendDiscount(menuTypeCount.get(MAIN_COURSE.toString()), visitDay); // 각 메뉴마다 합을 저장해야해.

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

    // 만원 미만 구입시
    public boolean noEventCase(boolean eventTicket, int totalOrderCost) {
        if (!eventTicket) {
            outputView.printGiftMenu("없음"); // 증정메뉴
            outputView.printBenefitLists(0, 0, 0, 0, 0); // 혜택 내역
            outputView.printTotalOrderDiscount(0); // 총혜택 금액
            outputView.printBill(totalOrderCost);
            outputView.printEventBadge("없음");
            return true;
        }
        return false;
    }

    // 총 주문금액 계산
    public int calculateTotalOrderCost(HashMap<String, Integer> menuCount) {
        totalOrderAmount.calculateOrderTotal(menuCount); // 총 주문 금액 계산
        int totalOrderCost = totalOrderAmount.getTotalOrderAmount(); // 총 주문 금액
        outputView.printTotalOrderPrice(totalOrderCost); // 할인 전 총주문 금액 출력
        return totalOrderCost;
    }
}
