package christmas.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class MenuPricesTest {

    @Test
    void getPrice() {
        MenuPrices menuPrices = new MenuPrices();

        assertEquals(6_000, menuPrices.getPrice("양송이수프"));
        assertEquals(55_000, menuPrices.getPrice("티본스테이크"));
        assertEquals(15_000, menuPrices.getPrice("초코케이크"));
        assertEquals(3_000, menuPrices.getPrice("제로콜라"));

        // 가격이 없는 메뉴
        assertEquals(0, menuPrices.getPrice("없는메뉴"));
    }

    @Test
    void getAppetizerPrice() {
        MenuPrices menuPrices = new MenuPrices();

        assertEquals(6_000, menuPrices.getAppetizerPrice("양송이수프"));
        assertEquals(5_500, menuPrices.getAppetizerPrice("타파스"));
        assertEquals(8_000, menuPrices.getAppetizerPrice("시저샐러드"));

        // 가격이 없는 메뉴
        assertEquals(0, menuPrices.getAppetizerPrice("없는메뉴"));
    }

    @Test
    void getMainMenuPrice() {
        MenuPrices menuPrices = new MenuPrices();

        assertEquals(55_000, menuPrices.getMainMenuPrice("티본스테이크"));
        assertEquals(54_000, menuPrices.getMainMenuPrice("바비큐립"));
        assertEquals(35_000, menuPrices.getMainMenuPrice("해산물파스타"));

        // 가격이 없는 메뉴
        assertEquals(0, menuPrices.getMainMenuPrice("없는메뉴"));
    }

    @Test
    void getDessertPrice() {
        MenuPrices menuPrices = new MenuPrices();

        assertEquals(15_000, menuPrices.getDessertPrice("초코케이크"));
        assertEquals(5_000, menuPrices.getDessertPrice("아이스크림"));

        // 가격이 없는 메뉴
        assertEquals(0, menuPrices.getDessertPrice("없는메뉴"));
    }

    @Test
    void getBeveragePrice() {
        MenuPrices menuPrices = new MenuPrices();

        assertEquals(3_000, menuPrices.getBeveragePrice("제로콜라"));
        assertEquals(60_000, menuPrices.getBeveragePrice("레드와인"));
        assertEquals(25_000, menuPrices.getBeveragePrice("샴페인"));

        // 가격이 없는 메뉴
        assertEquals(0, menuPrices.getBeveragePrice("없는메뉴"));
    }
}
