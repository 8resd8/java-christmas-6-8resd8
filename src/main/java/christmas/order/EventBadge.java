package christmas.order;

public enum EventBadge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String badgeName;
    private final int threshold;

    EventBadge(String badgeName, int threshold) {
        this.badgeName = badgeName;
        this.threshold = threshold;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public static EventBadge getEventBadge(int discountTotal) {
        for (EventBadge badge : EventBadge.values()) {
            if (discountTotal >= badge.threshold) {
                return badge;
            }
        }
        return NONE;
    }
}
