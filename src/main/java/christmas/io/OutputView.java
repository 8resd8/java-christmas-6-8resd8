package christmas.io;


import christmas.EventDay;

import java.text.NumberFormat;
import java.util.HashMap;

public class OutputView {

    NumberFormat nf = NumberFormat.getInstance();
    private final String introduceEventBenefits = EventDay.MONTH.getValue() + "월 %d" + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public OutputView() {
        nf.setGroupingUsed(true);

    }

    public void printShowBenefit(int day) {
        System.out.println(String.format(introduceEventBenefits, day));
    }

    public void printOrderMenu(HashMap<String, Integer> menuCount) {
        System.out.println("\n<주문 메뉴>");
        for (String menu : menuCount.keySet()) {
            int count = menuCount.get(menu);
            System.out.printf("%s %d개\n", menu, count);
        }
        System.out.println();
    }

    public void printTotalOrderPrice(int totalOrderCost) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(nf.format(totalOrderCost) + "원");
        System.out.println();
    }

    public void printGiftMenu(String giftMenu) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftMenu);
        System.out.println();
    }

    public void printBenefitLists(int christmasDiscount, int weekDiscount, int weekendDiscount, int specialDiscount, int giftPrice) {
        System.out.println("<혜택 내역>");

        String[] benefitNames = {"크리스마스 디데이 할인", "평일 할인", "주말 할인", "특별 할인", "증정 이벤트"};
        int[] benefitDiscounts = {christmasDiscount, weekDiscount, weekendDiscount, specialDiscount, giftPrice};
        boolean anyDiscount = false;

        for (int i = 0; i < benefitNames.length; i++) {
            int discount = benefitDiscounts[i];
            if (discount != 0) {
                System.out.println(benefitNames[i] + ": " + nf.format(discount) + "원");
                anyDiscount = true;
            }
        }
        if (!anyDiscount) {
            System.out.println("없음");
        }
        System.out.println();
    }

    public void printTotalOrderDiscount(int totalOrderDiscount) {
        System.out.println("<총 혜택 금액>");
        System.out.println(nf.format(totalOrderDiscount) + "원"); // 증정 이벤트의 가격도 포함
        System.out.println();
    }

    public void printBill(int finalOrderAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(nf.format(finalOrderAmount) + "원");
        System.out.println();
    }

    public void printEventBadge(String eventBadge) {
        System.out.println("<" + EventDay.MONTH.getValue() + "월 이벤트 배지>");
        System.out.println(eventBadge);
    }




}
