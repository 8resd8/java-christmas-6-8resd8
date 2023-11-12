package christmas; // util 클래스 1개이므로 패키지 분리 x

import java.util.Calendar;

public class ChangedDay {
    Calendar calendar;

    public int getDay(int userDay) {

        calendar = Calendar.getInstance();
        int year = EventDay.YEAR.getValue();
        int month = EventDay.MONTH.getValue();
        calendar.set(year, month - 1, userDay); // 설정 : 2023년 12월 사용자가 입력한 일
        return getDayOfWeek(calendar);
    }

    private int getDayOfWeek(Calendar calendar) {
        // 요일을 숫자로 가져오고, 숫자를 1부터 7로 변환
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
