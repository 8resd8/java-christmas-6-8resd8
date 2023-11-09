package christmas;

import java.util.HashMap;

public class TotalOrderAmount {
    MenuPrices menuPrices = new MenuPrices();
    private int totalOrderAmount;

    int getTotalOrderAmount() {
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
    private void saveTotalAmount(int price, int totalPrice) {
        totalOrderAmount += price * totalPrice;
    }

}
