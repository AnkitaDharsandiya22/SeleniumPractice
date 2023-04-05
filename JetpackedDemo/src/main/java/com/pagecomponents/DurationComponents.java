package com.pagecomponents;

public enum DurationComponents {
    THISWEEK("This Week"),
    LASTWEEK ("Last Week"),
    MONTHTILLDATE("Month Till Date"),
    YEARTILLDATE (" Year Till Date"),
    CUSTOMRANGE("Custom Range");
    public String getDuration() {
        return duration;
    }
    DurationComponents(String duration) {
        this.duration = duration;
    }
    private final String duration;
}
