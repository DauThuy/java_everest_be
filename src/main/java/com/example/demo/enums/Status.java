package com.example.demo.enums;

public enum Status {
    ACTIVE(1), INACTIVE(0);
    private int value;

    private Status(int value) {
        this.value = value;
    }
    public Integer getValueActive() {
        return ACTIVE.value;
    }
    public Integer getValueInactive() {
        return INACTIVE.value;
    }
}
