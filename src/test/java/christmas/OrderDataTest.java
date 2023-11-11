package christmas;

import christmas.menu.Menu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDataTest {


    OrderData orderData = new OrderData();

    @Test
    void shouldReturnMenuTypeForExistingMenu() {
        // Given
        String existingMenu = "시저샐러드";  // 기존에 존재하는 메뉴

        // When
        Menu.MenuType result = orderData.getMenuTypeByName(existingMenu);

        // Then
        assertEquals(Menu.MenuType.APPETIZER, result);
    }

    @Test
    void shouldReturnDefaultMenuTypeForNonExistentMenu() {
        // Given
        String nonExistentMenu = "라면";  // 존재하지 않는 메뉴명

        // When
        Menu.MenuType result = orderData.getMenuTypeByName(nonExistentMenu);

        // Then
        assertEquals(Menu.MenuType.BEVERAGE, result);
    }

}