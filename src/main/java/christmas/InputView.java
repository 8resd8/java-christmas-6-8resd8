package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    // 주문할 메뉴와 개수를 입력
    public String inputMenuCount() {
        System.out.println("메뉴와 개수 입력해.");
        String inputMenuCount = Console.readLine();
        // 검증 코드 함수 작성
        return inputMenuCount;
    }
}