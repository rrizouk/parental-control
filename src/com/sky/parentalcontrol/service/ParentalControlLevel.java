package com.sky.parentalcontrol.service;

public enum ParentalControlLevel {
	
    UNIVERSAL("U"),
    PARENTAL_GUIDANCE("PG"),
    TWELVE("12"),
    FIFTEEN("15"),
    EIGHTEEN("18");

    private String code;

    ParentalControlLevel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

