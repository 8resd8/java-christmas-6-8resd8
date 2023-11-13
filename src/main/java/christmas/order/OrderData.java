package christmas.order;

import christmas.menu.Menu;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class OrderData {

    private final HashMap<String, Integer> saveMenuAndCount = new LinkedHashMap<>();
    private final HashMap<String, Integer> menuTypeCount = new HashMap<>();

    public OrderData() {
        // 필드 초기화
        menuTypeCount.put(Menu.MenuType.APPETIZER.toString(), 0);
        menuTypeCount.put(Menu.MenuType.MAIN_COURSE.toString(), 0);
        menuTypeCount.put(Menu.MenuType.DESSERT.toString(), 0);
        menuTypeCount.put(Menu.MenuType.BEVERAGE.toString(), 0);
    }

    public HashMap<String, Integer> getMenuCount() {
        return saveMenuAndCount;
    }

    public HashMap<String, Integer> getMenuTypeCount() {
        return menuTypeCount;
    }


    // 입력된 메뉴와 가격, 개수를 저장
    public void saveMenuCount(String inputMenuCount) {
        String[] menuCounts = inputMenuCount.split(",");

        for (String values : menuCounts) {
            String[] menuCount = values.split("-");
            String menu = menuCount[0];

            int count = Integer.parseInt(menuCount[1]);
            saveMenuAndCount.put(menu, count); // 메뉴 값 저장
            Menu.MenuType menuType = getMenuTypeByName(menu);
            saveMenuTypeCount(menuType, count);
        }
    }

    // 메뉴 별 합계를 저장
    public void saveMenuTypeCount(Menu.MenuType menuType, int count) {
        String menu = menuType.toString();
        menuTypeCount.put(menu, menuTypeCount.getOrDefault(menu, 0) + count);
    }

    // 메뉴 별 종류를 얻기
    public Menu.MenuType getMenuTypeByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getMenuName().equals(menuName)) {
                return menu.getType();
            }
        }
        return null; // 메뉴가 없는 경우
    }
}