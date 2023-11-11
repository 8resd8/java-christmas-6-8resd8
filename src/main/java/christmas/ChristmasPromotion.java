package christmas;

import christmas.menu.EventDay;
import christmas.menu.Menu;

import java.util.HashMap;

import static christmas.menu.Menu.MenuType.DESSERT;


public class ChristmasPromotion {

    public void eventStart() {
        InputView inputView = new InputView();
        ChangedDay changedDay = new ChangedDay();
        OutputView outputView = new OutputView();
        OrderData orderData = new OrderData();

        int userDay = inputView.visitDay(); // 현실 세계 날짜

        int visitDay = changedDay.getDay(userDay); // 날짜 변환

        // 해산물파스타-2,레드와인-1,초코케이크-1
        String userMenuCount = inputView.inputMenuCount(); // 메뉴와 개수 입력

        orderData.saveMenuCount(userMenuCount); // 메뉴를 저장한다.
        outputView.printShowBenefit(userDay);

//        System.out.println("<주문 메뉴>");
        HashMap<String, Integer> menuCount = orderData.getMenuCount();
        outputView.printOrderMenu(menuCount);


//        System.out.println("<할인 전 총주문 금액>");
        TotalOrderAmount totalOrderAmount = new TotalOrderAmount();
        totalOrderAmount.calculateOrderTotal(menuCount); // 값 계산 시키기
        int totalOrderCost = totalOrderAmount.getTotalOrderAmount(); // 총 주문 금액
        outputView.printTotalOrderPrice(totalOrderCost);

        EventDiscount eventDiscount = new EventDiscount();

        boolean eventTicket = eventDiscount.eventMinimumCondition(totalOrderCost); // 이벤트 최소 참여 조건

        // 만원 미만 구매시
//        if (!eventTicket) {
//
//        }

        // 증정 메뉴 = 샴페인 1개.
        int giftCount = 1;
        String giftMenu = eventDiscount.giftMenu(totalOrderCost, giftCount); // 증정 메뉴 수량 : 1개
        int giftPrice = eventDiscount.giftPrice(Menu.CHAMPAGNE, giftCount, totalOrderCost); // 증정 메뉴 가격
        outputView.printGiftMenu(giftMenu);


        // 1. 크리스마스 할인
        int christmasDiscount = eventDiscount.getChristmasDayDiscount(userDay);
        HashMap<String, Integer> menuTypeCount = orderData.getMenuTypeCount(); // 메뉴 타입별 개수 확인 가능

        // 2. 평일 = 디저트 메뉴 할인;
        int weekDiscount = eventDiscount.weekdayDiscount(menuTypeCount.get(DESSERT.toString()), visitDay);

        // 3. 주말 = 메인 메뉴 할인 (0원이라면 출력 X)
        int weekendDiscount = eventDiscount.weekendDiscount(menuTypeCount.get("MAIN_COURSE"), visitDay); // 각 메뉴마다 합을 저장해야해.

        // 4. 특별 할인 = 별이 있으면 총 주문 금액에서 1,000원 할인
        int specialDiscount = eventDiscount.getSpecialDiscount(userDay);

        // 5. 증정 이벤트 표시 = 총 주문 금액이 12만원 이상이라면 샴페인 1개


        outputView.printBenefitLists(christmasDiscount, weekDiscount, weekendDiscount, specialDiscount, giftPrice);

        // 총 혜택 금액
        int totalOrderDiscount = christmasDiscount + weekDiscount + weekendDiscount + specialDiscount;
        outputView.printTotalOrderDiscount(totalOrderDiscount + giftPrice);


        // 할인 후 에상 금액
        int finalOrderAmount = totalOrderCost - totalOrderDiscount;
        outputView.printBill(finalOrderAmount);


        // 증정 이벤트는 포함하지 않는 혜택 금액을 줘야함.
        String eventBadge = eventDiscount.eventBadge(totalOrderDiscount);
        outputView.printEventBadge(eventBadge);



    }
}
