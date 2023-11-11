package christmas;

import christmas.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    InputView inputView = new InputView();
    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("존재하는 메뉴 테스트")
    void shouldReturnTrueAllMenus() {
        Menu[] allMenus = Menu.values();


        boolean result = true;
        for (String menuName : new String[]{"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"}) {
            result = result && inputView.MenuContains(menuName, allMenus);
        }

        assertTrue(result);
    }


    @Test
    @DisplayName("존재하지 않는 메뉴 테스트")
    void menuContainsTest() {
        // Given
        Menu[] allMenus = Menu.values();
        String nonExistentMenu = "사자샐러드";

        // When
        boolean result = inputView.MenuContains(nonExistentMenu, allMenus);

        // Then
        assertFalse(result);
    }
}