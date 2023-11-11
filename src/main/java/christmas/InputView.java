package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.menu.ErrorMessage;
import christmas.menu.EventDay;
import christmas.menu.Menu;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class InputView {

    private final String introduceRestaurantEvent = "안녕하세요! 우테코 식당 " + EventDay.MONTH.getValue() + "월 이벤트 플래너입니다.";
    private final String askVisitDate = EventDay.MONTH.getValue() + "월 중 식당 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final String orderGuidance = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    // 방문할 날짜를 입력
    public int visitDay() {
        int userVisitDay = -1;

        System.out.println(introduceRestaurantEvent + "\n" + askVisitDate);
        String visitDay = Console.readLine();

        try {
            isNumeric(visitDay);
            isValidNumberRange(visitDay, 1, 31);
            userVisitDay = Integer.parseInt(visitDay);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return visitDay();
        }

        return userVisitDay;
    }

    // 1 - 31 사이 값인가?
    public void isValidNumberRange(String visitDay, int start, int end) {
        int day = Integer.parseInt(visitDay);
        if (day < start || day > end) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY_INPUT.getMessage());
        }
    }

    // 숫자인가?
    public void isNumeric(String visitDay) {
        if (!visitDay.matches("\\d+")) {
            throw new NumberFormatException(ErrorMessage.INVALID_DAY_INPUT.getMessage());
        }
    }


    // 2. 주문할 메뉴와 개수를 입력
    public String inputMenuCount() {
        System.out.println(orderGuidance);
        String inputOrder = Console.readLine();

        try {
            HashMap<String, Integer> checkOrder = new LinkedHashMap<>();
            validOrder(inputOrder, checkOrder);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenuCount();
        }

        return inputOrder;
    }

    public void validOrder(String inputOrder, HashMap<String, Integer> checkOrder) {
        String[] userOrder = inputOrder.split(",");

        for (String order : userOrder) {
            String[] menuAndCount = order.split("-");
            // 적어도 1개이상의 주문을 했는가
            if (menuAndCount.length != 2) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage());
            }

            String menuName = menuAndCount[0];
            String menuCount = menuAndCount[1];

            isCondition(menuName, menuCount, checkOrder);
        }
    }

    // 조건에 맞는 입력 확인
    public void isCondition(String menuName, String menuCount, HashMap<String, Integer> checkOrder) {
        int count;
        try {
            count = Integer.parseInt(menuCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage());
        }

        // 1이상의 숫자  || 메뉴가 존재하는가                        ||  중복 메뉴가 있는가
        if (count <= 0 || !MenuContains(menuName, Menu.values()) || checkOrder.containsKey(menuName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getMessage());
        }
        checkOrder.put(menuName, count);
    }

    public boolean MenuContains(String menuName, Menu[] allMenus) {
        // 1개의 메뉴라도 일치한다면
        for (Menu menu : allMenus) {
            String s = menu.getMenuName();
            if (s.equals(menuName)) {
                return true;
            }
        }
        return false;
    }
}