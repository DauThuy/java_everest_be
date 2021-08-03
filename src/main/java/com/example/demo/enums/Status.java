package com.example.demo.enums;

import org.springframework.data.relational.core.sql.In;

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
