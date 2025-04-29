package com.kaio.superjumper.enums;

public enum JumperActionStateEnum {
    TURNING_LEFT(1),
    TURNING_RIGHT(2),
    SHOOTING(3);

    private final int state;

    JumperActionStateEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
