package christmas.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    InputView inputView;

    @BeforeEach
    void setUp() {
        this.inputView = new InputView();
    }

    @BeforeEach


    @Test
    @DisplayName("유효한 숫자")
    public void isNumeric_validInputTest() {
        inputView.isNumeric("123");
        inputView.isNumeric("0");
    }

    @Test
    @DisplayName("비정상 숫자")
    public void isNumeric_InvalidInputTest() {
        assertThrows(NumberFormatException.class, () -> inputView.isNumeric("abcde"));
        assertThrows(NumberFormatException.class, () -> inputView.isNumeric(""));
        assertThrows(NumberFormatException.class, () -> inputView.isNumeric("5.1"));
    }

    @DisplayName("방문 날짜 정상  테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "10", "15", "30", "31"})
    public void isNumberRangeTest(String visitDay) {
        assertDoesNotThrow(() -> inputView.isValidNumberRange(visitDay, 1, 31));
    }

    @Test
    @DisplayName("21개 메뉴 초과 테스트")
    void validEventOverLengthTest() {
        String inputOrder = "양송이수프-10,제로콜라-9,아이스크림-3";
        assertThrows(IllegalStateException.class, () -> inputView.validEventOverLength(inputOrder));
    }

    @Test
    @DisplayName("20개 이하 메뉴 테스트")
    void invalidEventOverLengthTest() {
        String inputOrder = "양송이수프-3,제로콜라-2,아이스크림-1";
        assertDoesNotThrow(() -> inputView.validEventOverLength(inputOrder));
    }


    @DisplayName("음료만 시킨 경우 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-2,샴페인-1,레드와인-5", "제로콜라-1", "샴페인-2", "레드와인-12"})
    void validEventOnlyBeverageTest(String inputOrder) {
        assertThrows(IllegalStateException.class, () -> inputView.validEventOnlyBeverage(inputOrder));
    }

    @Test
    void isConditionTest() {
        HashMap<String, Integer> checkOrder = new LinkedHashMap<>();
        assertThrows(IllegalArgumentException.class, () -> inputView.isCondition("티본스테이크", "0", checkOrder));
        assertThrows(IllegalArgumentException.class, () -> inputView.isCondition("한라봉주스", "312", checkOrder));
        assertThrows(IllegalArgumentException.class, () -> inputView.isCondition("한라봉주스", "1", checkOrder));
    }

    @Test
    @DisplayName("존재하는 메뉴 테스트")
    void tureMenuContains() {
        boolean result = true;
        for (String menuName : new String[]{"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"}) {
            result = result && inputView.MenuContains(menuName);

        }
        assertTrue(result);
    }


    @DisplayName("존재하지 않는 메뉴 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"사자", "", "제로콜루", "레드외인", "타본스테이크", "초코바"})
    void falseMenuContainsTest(String nonExistentMenu) {
        boolean result = inputView.MenuContains(nonExistentMenu);

        assertFalse(result);
    }
}