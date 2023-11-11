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



    }
}
