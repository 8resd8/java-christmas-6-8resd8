package christmas;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class OrderData {

    private final HashMap<String, Integer> saveMenuAndCount = new LinkedHashMap<>();

    public HashMap<String, Integer> getMenuCount() {
        return saveMenuAndCount;
    }

    // 입력된 메뉴와 가격을 저장
    public void saveMenuCount(String inputMenuCount) {
        String[] menuCounts = inputMenuCount.split(",");

        for (String values : menuCounts) {
            String[] menuCount = values.split("-");
            String menu = menuCount[0];
            int count = Integer.parseInt(menuCount[1]);
            saveMenuAndCount.put(menu, count);
        }

    }
}
