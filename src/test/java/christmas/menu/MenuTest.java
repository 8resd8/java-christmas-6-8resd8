package christmas.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void getMenuName() {
        assertEquals("양송이수프", Menu.YANGSONG_SOUP.getMenuName());
        assertEquals("타파스", Menu.TAPAS.getMenuName());
        assertEquals("시저샐러드", Menu.CAESAR_SALAD.getMenuName());
        assertEquals("티본스테이크", Menu.T_BONE_STEAK.getMenuName());
        assertEquals("바비큐립", Menu.BBQ_RIB.getMenuName());
        assertEquals("해산물파스타", Menu.SEAFOOD_PASTA.getMenuName());
        assertEquals("크리스마스파스타", Menu.CHRISTMAS_PASTA.getMenuName());
        assertEquals("초코케이크", Menu.CHOCO_CAKE.getMenuName());
        assertEquals("아이스크림", Menu.ICE_CREAM.getMenuName());
        assertEquals("제로콜라", Menu.ZERO_COKE.getMenuName());
        assertEquals("레드와인", Menu.RED_WINE.getMenuName());
        assertEquals("샴페인", Menu.CHAMPAGNE.getMenuName());
    }

    @Test
    void getPrice() {
        assertEquals(6_000, Menu.YANGSONG_SOUP.getPrice());
        assertEquals(5_500, Menu.TAPAS.getPrice());
        assertEquals(8_000, Menu.CAESAR_SALAD.getPrice());
        assertEquals(55_000, Menu.T_BONE_STEAK.getPrice());
        assertEquals(54_000, Menu.BBQ_RIB.getPrice());
        assertEquals(35_000, Menu.SEAFOOD_PASTA.getPrice());
        assertEquals(25_000, Menu.CHRISTMAS_PASTA.getPrice());
        assertEquals(15_000, Menu.CHOCO_CAKE.getPrice());
        assertEquals(5_000, Menu.ICE_CREAM.getPrice());
        assertEquals(3_000, Menu.ZERO_COKE.getPrice());
        assertEquals(60_000, Menu.RED_WINE.getPrice());
        assertEquals(25_000, Menu.CHAMPAGNE.getPrice());
    }

    @Test
    void getType() {
        assertEquals(Menu.MenuType.APPETIZER, Menu.YANGSONG_SOUP.getType());
        assertEquals(Menu.MenuType.APPETIZER, Menu.TAPAS.getType());
        assertEquals(Menu.MenuType.APPETIZER, Menu.CAESAR_SALAD.getType());
        assertEquals(Menu.MenuType.MAIN_COURSE, Menu.T_BONE_STEAK.getType());
        assertEquals(Menu.MenuType.MAIN_COURSE, Menu.BBQ_RIB.getType());
        assertEquals(Menu.MenuType.MAIN_COURSE, Menu.SEAFOOD_PASTA.getType());
        assertEquals(Menu.MenuType.MAIN_COURSE, Menu.CHRISTMAS_PASTA.getType());
        assertEquals(Menu.MenuType.DESSERT, Menu.CHOCO_CAKE.getType());
        assertEquals(Menu.MenuType.DESSERT, Menu.ICE_CREAM.getType());
        assertEquals(Menu.MenuType.BEVERAGE, Menu.ZERO_COKE.getType());
        assertEquals(Menu.MenuType.BEVERAGE, Menu.RED_WINE.getType());
        assertEquals(Menu.MenuType.BEVERAGE, Menu.CHAMPAGNE.getType());
    }
}
