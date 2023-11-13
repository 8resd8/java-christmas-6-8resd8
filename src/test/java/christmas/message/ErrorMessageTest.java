package christmas.message;

import christmas.message.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorMessageTest {

    @Test
    @DisplayName("ErrorMessage Enum 테스트")
    public void testErrorMessageEnum() {
        // Arrange
        String expectedDayInputErrorMessage = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
        String expectedOrderInputErrorMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

        // Act
        String actualDayInputErrorMessage = ErrorMessage.INVALID_DAY_INPUT.getMessage();
        String actualOrderInputErrorMessage = ErrorMessage.INVALID_ORDER_INPUT.getMessage();

        // Assert Equals
        assertEquals(expectedDayInputErrorMessage, actualDayInputErrorMessage);
        assertEquals(expectedOrderInputErrorMessage, actualOrderInputErrorMessage);
    }
}