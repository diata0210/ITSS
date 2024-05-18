package app.models;

public enum OrderStatus {
    PENDING_CONFIRMATION(1),
    PICKING_UP(2),
    DELIVERING(3),
    RECEIVED(4),
    CANCELED(5);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
