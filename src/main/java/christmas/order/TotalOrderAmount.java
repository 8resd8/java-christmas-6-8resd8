package christmas.order;

import christmas.menu.MenuPrices;

import java.util.HashMap;

public class TotalOrderAmount {
    MenuPrices menuPrices = new MenuPrices();
    private int totalOrderAmount;

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    // 주문한 값대로 총 주문 금액을 계산
    public void calculateOrderTotal(HashMap<String, Integer> userOrder) {
        for (String menu : userOrder.keySet()) {
            int price = menuPrices.getPrice(menu);
            int totalPrice = userOrder.get(menu);
            saveTotalAmount(price, totalPrice);
        }
    }

    // 총 주문 금액 저장
    public void saveTotalAmount(int price, int totalPrice) {
        totalOrderAmount += price * totalPrice;
    }

    // 증정 메뉴 확인, 총 구매 금액 넣어서 사용
    public String giftMenu(int totalOrderAmount) {
        EventDiscount eventDiscount = new EventDiscount();
        return eventDiscount.giftMenu(totalOrderAmount, 1);
    }


}
