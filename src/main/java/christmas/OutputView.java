package christmas;

import christmas.menu.EventDay;

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




}
