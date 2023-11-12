package christmas.menu;


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
        appetizer.put(Menu.YANGSONG_SOUP.getMenuName(), Menu.YANGSONG_SOUP.getPrice());
        appetizer.put(Menu.TAPAS.getMenuName(), Menu.TAPAS.getPrice());
        appetizer.put(Menu.CAESAR_SALAD.getMenuName(), Menu.CAESAR_SALAD.getPrice());
    }

    private void initializeMainMenuPrices() {
        mainMenu.put(Menu.T_BONE_STEAK.getMenuName(), Menu.T_BONE_STEAK.getPrice());
        mainMenu.put(Menu.BBQ_RIB.getMenuName(), Menu.BBQ_RIB.getPrice());
        mainMenu.put(Menu.SEAFOOD_PASTA.getMenuName(), Menu.SEAFOOD_PASTA.getPrice());
        mainMenu.put(Menu.CHRISTMAS_PASTA.getMenuName(), Menu.CHRISTMAS_PASTA.getPrice());
    }

    private void initializeDessertPrices() {
        dessert.put(Menu.CHOCO_CAKE.getMenuName(), Menu.CHOCO_CAKE.getPrice());
        dessert.put(Menu.ICE_CREAM.getMenuName(), Menu.ICE_CREAM.getPrice());
    }

    private void initializeBeveragePrices() {
        beverage.put(Menu.ZERO_COKE.getMenuName(), Menu.ZERO_COKE.getPrice());
        beverage.put(Menu.RED_WINE.getMenuName(), Menu.RED_WINE.getPrice());
        beverage.put(Menu.CHAMPAGNE.getMenuName(), Menu.CHAMPAGNE.getPrice());
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

//     각 메뉴의 가격을 가져올 수 있는 메서드
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

