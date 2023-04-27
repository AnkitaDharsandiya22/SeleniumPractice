package com.pagecomponents;

public enum DaySelectionComponents {
    ONE("1"),
    SEVEN("7");

    DaySelectionComponents(String day) {
        this.day=day;
    }
    private final String day;

    public String getDay() {
        return day;
    }
}
