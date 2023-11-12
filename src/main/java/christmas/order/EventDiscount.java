package christmas.order;

import christmas.EventDay;
import christmas.menu.Menu;

import java.util.List;

public class EventDiscount {

    private final int[] christmasDayDiscount = new int[32];
    private final int[] specialDiscount = new int[32];
    private final int giftRole = 120_000;


    public EventDiscount() {
        initializeChristmasDayDiscount();
        initializeSpecialDiscount();
    }

    // 이벤트 최소 참여 조건
    public boolean eventMinimumCondition(int totalOrderCost) {
        int minimumAmount = 10_000;
        return totalOrderCost >= minimumAmount;
    }


    // 크리스마스 D-day 이벤트 할인값 저장
    private void initializeChristmasDayDiscount() {
        int discount = -1000;
        for (int i = 1; i <= 25; i++) {
            christmasDayDiscount[i] = discount + ((i - 1) * (-100));
        }
    }

    // 특별 할인 (별 표시되어있는 날짜)
    private void initializeSpecialDiscount() {
        List<Integer> days = List.of(3, 10, 17, 24, 25);
        for (int day : days) {
            specialDiscount[day] = -1000;
        }
    }

    // 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
    public String giftMenu (int totalAmount, int count) {
        if (totalAmount >= giftRole) {
            String giftMenu = Menu.CHAMPAGNE.getMenuName();
            return giftMenu + " " + count + "개";
        }
        return "없음";
    }

    // 증정 이벤트 가격
    public int giftPrice (Menu menuName, int count, int totalAmount) {
        if (totalAmount < giftRole) {
            return 0;
        }
        return -1 * menuName.getPrice() * count;
    }


    // 크리스마스 디데이 할인 (1일 ~ 25일), 나머지 할인은 모두 31일까지
    public int getChristmasDayDiscount(int day) {
        return christmasDayDiscount[day];
    }

    // 평일 할인(일요일 ~ 목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    // 일요일 = 1 ~ 토요일 = 7
    public int weekdayDiscount(int dessertCount, int day) {
        if (day <= 5) {
            return -1 * dessertCount * EventDay.YEAR.getValue();
        }
        return 0;
    }

    // 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    public int weekendDiscount(int mainCount, int day) {
        if (day <= 7 && day >= 6) {
            return -1 * mainCount * EventDay.YEAR.getValue();
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
