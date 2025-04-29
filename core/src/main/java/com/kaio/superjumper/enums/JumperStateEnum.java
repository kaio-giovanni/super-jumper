package com.kaio.superjumper.enums;

public enum JumperStateEnum {
    JUMPING(0),
    FALLING(1),
    HITTING_PLATFORM(2),
    DIED(3);

    private final int state;

    JumperStateEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}
