package com.kaio.superjumper.enums;

public enum WorldStateEnum {
    RUNNING(0),
    NEXT_LEVEL(1),
    GAME_OVER(2);

    private final int state;

    WorldStateEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}
