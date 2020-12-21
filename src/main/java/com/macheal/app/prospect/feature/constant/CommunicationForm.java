package com.macheal.app.prospect.feature.constant;

public enum CommunicationForm {
    EMAIL("E-Mail"), PHONE("Phone"), PHYSICAL("Physical"),
    SOCIAL_MEDIA_FACEBOOK("Social Media - Facebook"),
    SOCIAL_MEDIA_INSTAGRAM("Social Media - Instagram"),
    SOCIAL_MEDIA_LINKED_IN("Social Media - LinkedIn"),
    SOCIAL_MEDIA_OTHER("Social Media - Other"),
    SMS("SMS"), COMBINED("Combined");

    CommunicationForm(final String code) {
        this.code = code;
    }

    String code;

    public String getCode() {
        return code;
    }

}
