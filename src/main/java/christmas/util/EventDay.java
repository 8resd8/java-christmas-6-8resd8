package christmas.util;

public enum EventDay {
    YEAR(2023),
    MONTH(12);

    private final int value;

    EventDay(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
