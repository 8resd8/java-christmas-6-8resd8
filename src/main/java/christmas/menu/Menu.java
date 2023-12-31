package christmas.menu;

public enum Menu {
    // 애피타이저
    YANGSONG_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIB("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),

    // 디저트
    CHOCO_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),

    // 음료
    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String menuName;
    private final int price;

    Menu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public enum MenuType {
        APPETIZER, MAIN_COURSE, DESSERT, BEVERAGE
    }

    public MenuType getType() {
        String menuName = this.getMenuName();
        if (menuName.contains("양송이수프") || menuName.contains("타파스") || menuName.contains("시저샐러드")) {
            return MenuType.APPETIZER;
        } else if (menuName.contains("티본스테이크") || menuName.equals("바비큐립") || menuName.contains("해산물파스타") || menuName.contains("크리스마스파스타")) {
            return MenuType.MAIN_COURSE;
        } else if (menuName.contains("초코케이크") || menuName.contains("아이스크림")) {
            return MenuType.DESSERT;
        } else if (menuName.contains("샴페인") || menuName.contains("레드와인") || menuName.contains("제로콜라")) {
            return MenuType.BEVERAGE;
        }
        return null;
    }

}