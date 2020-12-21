package com.macheal.app.prospect.feature.constant;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EventType {

    CREATE_EVENT("Create Event"),
    CONFIRM_EVENT("Confirm Event");

    EventType(final String code) {
        this.code = code;
    }

    String code;

    public String getCode() {
        return code;
    }


}
