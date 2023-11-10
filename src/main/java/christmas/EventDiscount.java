package christmas;

import christmas.menu.EventDay;
import christmas.menu.Menu;

import java.util.List;

public class EventDiscount {

    private final int[] christmasDayDiscount = new int[26];
    private final int[] specialDiscount = new int[32];
    private int totalAmount;

    public EventDiscount(int totalAmount) {
        this.totalAmount = totalAmount;
        initializeChristmasDayDiscount();
        initializeSpecialDiscount();
    }

    // 크리스마스 D-day 이벤트 할인값 저장
    private void initializeChristmasDayDiscount() {
        int discount = 1000;
        for (int i = 1; i <= 25; i++) {
            christmasDayDiscount[i] = discount + ((i - 1) * 100);
        }
    }

    // 특별 할인 (별표시)
    private void initializeSpecialDiscount() {
        List<Integer> days = List.of(3, 10, 17, 24, 25);
        for (int day : days) {
            specialDiscount[day] = 1000;
        }
    }

    // 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
    public String giftMenu (int totalAmount, int count) {
        if (totalAmount >= 120_000) {
            String giftmenu = Menu.CHAMPAGNE.getMenuName();
            return giftmenu + " " + count + "개";
        }
        return "없음";
    }

    // 증정 이벤트 가격
    public int giftPrice (Menu menuName, int count) {
        int price = menuName.getPrice() * count;
        return price;
    }


    // 크리스마스 디데이 할인 (1일 ~ 25일), 나머지 할인은 모두 31일까지
    public int getChristmasDayDiscount(int day) {
        return christmasDayDiscount[day];
    }

    // 평일 할인(일요일 ~ 목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    // 일요일 = 1 ~ 토요일 = 7
    public int weekdayDiscount(int dessertCount, int day) {
        if (day <= 5) {
            return dessertCount * EventDay.YEAR.getValue();
        }
        return 0;
    }

    // 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    public int weekendDiscount(int mainCount, int day) {
        if (day <= 7 && day >= 6) {
            return mainCount * EventDay.YEAR.getValue();
        }
        return 0;
    }

    // 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
    public int getSpecialDiscount(int day) {
        return specialDiscount[day];
    }

    // 총 혜택 금액에 따라 12월 이벤트 배지 부여
    public String eventBadge (int discountTotal) {
        if (discountTotal >= 20_000) {
            return "별";
        }
        if (discountTotal >= 10_000) {
            return "트리";
        }
        if (discountTotal >= 5_000) {
            return "산타";
        }
        return "없음";
    }
}
