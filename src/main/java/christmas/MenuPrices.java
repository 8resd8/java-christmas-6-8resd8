package christmas;

import java.util.HashMap;
import java.util.Map;

public class MenuPrices {
    private final Map<String, Integer> appetizer = new HashMap<>();
    private final Map<String, Integer> mainMenu = new HashMap<>();
    private final Map<String, Integer> dessert = new HashMap<>();
    private final Map<String, Integer> beverage = new HashMap<>();

    public MenuPrices() {
        initializeAppetizerPrices(); // 에피타이저
        initializeMainMenuPrices();  // 메인 메뉴
        initializeDessertPrices();   // 디저트
        initializeBeveragePrices();  // 음료수
    }

    private void initializeAppetizerPrices() {
        appetizer.put("양송이수프", 6_000);
        appetizer.put("타파스", 5_500);
        appetizer.put("시저샐러드", 8_000);
    }

    private void initializeMainMenuPrices() {
        mainMenu.put("티본스테이크", 55_000);
        mainMenu.put("바비큐립", 54_000);
        mainMenu.put("해산물파스타", 35_000);
        mainMenu.put("크리스마스파스타", 25_000);
    }

    private void initializeDessertPrices() {
        dessert.put("초코케이크", 15_000);
        dessert.put("아이스크림", 5_000);
    }

    private void initializeBeveragePrices() {
        beverage.put("제로콜라", 3_000);
        beverage.put("레드와인", 6_000);
        beverage.put("샴페인", 25_000);
    }

    // 모든 메뉴의 가격을 가져오는 메서드
    public int getPrice(String item) {
        int price = getAppetizerPrice(item);

        if (price == 0) {
            price = getMainMenuPrice(item);
        }
        if (price == 0) {
            price = getDessertPrice(item);
        }
        if (price == 0) {
            price = getBeveragePrice(item);
        }
        return price;
    }

    // 각 메뉴의 가격을 가져올 수 있는 메서드
    public int getAppetizerPrice(String item) {
        return appetizer.getOrDefault(item, 0);
    }

    public int getMainMenuPrice(String item) {
        return mainMenu.getOrDefault(item, 0);
    }

    public int getDessertPrice(String item) {
        return dessert.getOrDefault(item, 0);
    }

    public int getBeveragePrice(String item) {
        return beverage.getOrDefault(item, 0);
    }
}

