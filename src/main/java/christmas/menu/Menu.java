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

    // 메뉴의 종류를 반환하는 메서드
    public MenuType getType() {
        if (this.name().contains("양송이수프") || this.name().contains("타파스") || this.name().contains("시저샐러드")) {
            return MenuType.APPETIZER;
        } else if (this.name().equals("티본스테이크") || this.name().equals("바비큐립") || this.name().contains("해산물파스타")) {
            return MenuType.MAIN_COURSE;
        } else if (this.name().contains("초코케이크") || this.name().contains("아이스크림")) {
            return MenuType.DESSERT;
        } else if (this.name().contains("샴페인") || this.name().contains("레드와인") || this.name().contains("제로콜라")) {
            return MenuType.BEVERAGE;
        }
        return null;
    }

    // 메뉴의 종류를 문자열로 반환하는 메서드
    public static String getMenuType(String menuName) {
        Menu menu = Menu.valueOf(menuName);
        return menu.getType().toString();
    }

}