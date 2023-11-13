package christmas.order;

import christmas.menu.Menu;
import christmas.order.TotalOrderAmount;
import christmas.menu.MenuPrices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalOrderAmountTest {

    TotalOrderAmount totalOrderAmount;

    @BeforeEach
    void setUp() {
        totalOrderAmount = new TotalOrderAmount();
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프, 2, 타파스, 1, 시저샐러드, 5",
            "티본스테이크, 1, 바리큐립, 4, 해산물파스타, 5, 크리스마스파스타, 3",
            "초코케이크, 3, 아이스크림, 12",
            "제로콜라, 3, 레드와인, 10, 샴페인, 3"})
    @DisplayName("종류별 주문 금액 계산 테스트")
    public void testCalculateOrderTotal(String menuCount) {
        HashMap<String, Integer> userOrder = createUserOrder(menuCount);
        totalOrderAmount.calculateOrderTotal(userOrder);

        int expected = calculateExpected(userOrder);

        assertEquals(expected, totalOrderAmount.getTotalOrderAmount());
    }

    // 유저 입력값대로 계산 해보기
    private int calculateExpected(HashMap<String, Integer> userOrder) {
        MenuPrices menuPrices = new MenuPrices();
        return userOrder.entrySet().stream()
                .mapToInt(entry -> menuPrices.getPrice(entry.getKey()) * entry.getValue())
                .sum();
    }

    // HashMap에 유저 입력값 넣기
    private HashMap<String, Integer> createUserOrder(String menuCount) {
        HashMap<String, Integer> userOrder = new HashMap<>();
        String[] menuQuantities = menuCount.split(", ");

        for (int i = 0; i < menuQuantities.length; i += 2) {
            String menu = menuQuantities[i];
            int quantity = Integer.parseInt(menuQuantities[i + 1]);
            userOrder.put(menu, quantity);
        }

        return userOrder;
    }
}
