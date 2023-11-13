package christmas.order;

import christmas.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventDiscountTest {

    EventDiscount eventDiscount;

    @BeforeEach
    void setUp() {
        eventDiscount = new EventDiscount();
    }

    @Test
    @DisplayName("이벤트 최소 참여 조건 테스트")
    public void testEventMinimumCondition() {
        assertTrue(eventDiscount.eventMinimumCondition(10_000));
        assertTrue(eventDiscount.eventMinimumCondition(15_000));
        assertFalse(eventDiscount.eventMinimumCondition(9_000));
    }

    @Test
    @DisplayName("크리스마스 D-day 이벤트 할인값 저장 테스트")
    public void testInitializeChristmasDayDiscount() {
        for (int i = 1; i <= 25; i++) {
            assertEquals(-1000 + ((i - 1) * (-100)), eventDiscount.getChristmasDayDiscount(i));
        }
    }

    @Test
    @DisplayName("특별 할인값 저장 테스트")
    public void testInitializeSpecialDiscount() {
        List<Integer> specialDiscountDays = List.of(3, 10, 17, 24, 25);
        for (int day : specialDiscountDays) {
            assertEquals(-1000, eventDiscount.getSpecialDiscount(day));
        }
    }

    @Test
    @DisplayName("증정 이벤트 - 샴페인 증정 여부 테스트")
    public void testGiftMenu() {
        assertEquals("없음", eventDiscount.giftMenu(119_999, 1));
        assertEquals("샴페인 1개", eventDiscount.giftMenu(120_000, 1));
        assertEquals("샴페인 2개", eventDiscount.giftMenu(120_000, 2));
    }

    @Test
    @DisplayName("증정 이벤트  - 샴페인 가격 계산 테스트")
    public void testGiftPrice() {
        assertEquals(0, eventDiscount.giftPrice(Menu.CHAMPAGNE, 2, 119_000));
        assertEquals(-25_000, eventDiscount.giftPrice(Menu.CHAMPAGNE, 1, 120_000));
        assertEquals(-50_000, eventDiscount.giftPrice(Menu.CHAMPAGNE, 2, 121_000));
    }

    @Test
    @DisplayName("평일 할인 계산 테스트")
    public void testWeekdayDiscount() {
        assertEquals(-2023, eventDiscount.weekdayDiscount(1, 2));  // 월요일
        assertEquals(-4046, eventDiscount.weekdayDiscount(2, 3));  // 화요일
        assertEquals(0, eventDiscount.weekdayDiscount(1, 6));  // 토요일
    }

    @Test
    @DisplayName("주말 할인 계산 테스트")
    public void testWeekendDiscount() {
        assertEquals(0, eventDiscount.weekendDiscount(2, 2));  // 화요일
        assertEquals(-2023, eventDiscount.weekendDiscount(1, 6));  // 토요일
        assertEquals(-4046, eventDiscount.weekendDiscount(2, 7));  // 일요일
    }

    @Test
    @DisplayName("특별 할인 계산 테스트")
    public void testGetSpecialDiscount() {
        assertEquals(0, eventDiscount.getSpecialDiscount(1));  // 월요일
        assertEquals(0, eventDiscount.getSpecialDiscount(2));  // 화요일
        assertEquals(-1000, eventDiscount.getSpecialDiscount(3));  // 수요일
        assertEquals(0, eventDiscount.getSpecialDiscount(8));  // 일요일
    }

    @Test
    @DisplayName("이벤트 배지 부여 테스트")
    public void testEventBadge() {
        assertEquals("없음", eventDiscount.eventBadge(0));
        assertEquals("산타", eventDiscount.eventBadge(5_000));
        assertEquals("트리", eventDiscount.eventBadge(10_000));
        assertEquals("별", eventDiscount.eventBadge(20_000));
    }
}