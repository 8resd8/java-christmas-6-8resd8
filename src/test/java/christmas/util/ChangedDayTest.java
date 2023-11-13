package christmas.util;

import christmas.util.ChangedDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedDayTest {

    @ParameterizedTest
    @CsvSource({"24, 1", "25, 2", "26, 3", "27, 4", "28, 5", "29, 6", "30, 7"})
    @DisplayName("특정 일자의 요일 가져오기 테스트")
    public void testGetDayOfWeek(int userDay, int expected) {
        // Arrange
        ChangedDay changedDay = new ChangedDay();

        // 2023년 12월의 요일을 가져온다. 일요일 : 1 토요일 : 7
        int dayOfWeek = changedDay.getDay(userDay);

        // Assert
        assertEquals(expected, dayOfWeek);
    }
}
