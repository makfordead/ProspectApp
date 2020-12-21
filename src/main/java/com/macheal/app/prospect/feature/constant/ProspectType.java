package com.macheal.app.prospect.feature.constant;

public enum ProspectType {
    BusinessPartner("Business partner"), Coworker("Coworker"),
    Family("Family"), Friend("Friend"), Other("Other");

    ProspectType(final String code) {
        this.code = code;
    }

    String code;

    public String getCode() {
        return code;
    }

}
