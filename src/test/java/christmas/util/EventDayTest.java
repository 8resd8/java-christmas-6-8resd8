package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventDayTest {

    @Test
    @DisplayName("이벤트 2023년도 12월 가져오기 테스트")
    public void testGetEventDate() {
        EventDay year = EventDay.YEAR;
        EventDay month = EventDay.MONTH;

        int eventYear = year.getValue();
        int eventMonth = month.getValue();

        assertEquals(2023, eventYear);
        assertEquals(12, eventMonth);
    }
}
