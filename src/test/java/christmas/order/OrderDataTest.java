package christmas.order;

import christmas.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDataTest {

    OrderData orderData;

    @BeforeEach
    void setUp() {
        orderData = new OrderData();
    }

    @Test
    @DisplayName("주문 메뉴와 개수 저장 테스트")
    public void testSaveMenuCount() {
        orderData.saveMenuCount("제로콜라-2,샴페인-1,레드와인-5");

        HashMap<String, Integer> saveMenuAndCount = orderData.getMenuCount();
        assertEquals(2, saveMenuAndCount.get("제로콜라"));
        assertEquals(1, saveMenuAndCount.get("샴페인"));
        assertEquals(5, saveMenuAndCount.get("레드와인"));
    }

    @Test
    @DisplayName("메뉴 별 합계 저장 테스트")
    public void testSaveMenuTypeCount() {
        orderData.saveMenuCount("제로콜라-2,샴페인-1,레드와인-5,티본스테이크-3,아이스크림-3,시저샐러드-1");

        // Assert
        HashMap<String, Integer> menuTypeCount = orderData.getMenuTypeCount();
        assertEquals(8, menuTypeCount.get(Menu.MenuType.BEVERAGE.toString()));
        assertEquals(1, menuTypeCount.get(Menu.MenuType.APPETIZER.toString()));
        assertEquals(3, menuTypeCount.get(Menu.MenuType.MAIN_COURSE.toString()));
        assertEquals(3, menuTypeCount.get(Menu.MenuType.DESSERT.toString()));
    }

    @Test
    @DisplayName("에피타이저 종류 확인 테스트")
    public void appetizerTypeTest() {
        List<String> menuNames = List.of("양송이수프", "타파스", "시저샐러드");
        menuTypeCheck(menuNames, Menu.MenuType.APPETIZER);
    }

    @Test
    @DisplayName("메인 종류 확인 테스트")
    public void mainTypeTest() {
        List<String> menuNames = List.of("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타");
        menuTypeCheck(menuNames, Menu.MenuType.MAIN_COURSE);
    }

    @Test
    @DisplayName("디저트 종류 확인 테스트")
    public void dessertTypeTest() {
        List<String> menuNames = List.of("초코케이크", "아이스크림");
        menuTypeCheck(menuNames, Menu.MenuType.DESSERT);
    }

    @Test
    @DisplayName("음료 종류 확인 테스트")
    public void beverageTypeTest() {
        List<String> menuNames = List.of("제로콜라", "레드와인", "샴페인");
        menuTypeCheck(menuNames, Menu.MenuType.BEVERAGE);
    }

    private void menuTypeCheck(List<String> menuNames, Menu.MenuType menuType) {
        for (String menuName : menuNames) {
            assertEquals(menuType, orderData.getMenuTypeByName(menuName));
        }
    }
}
