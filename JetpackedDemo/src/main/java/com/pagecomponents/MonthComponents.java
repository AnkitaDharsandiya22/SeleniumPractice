package com.pagecomponents;

public enum MonthComponents {
    JANUARY2023("January 2023"),
    FEBRUARY2023("February 2023"),
    MARCH2023("March 2023"),
    APRIL2023("April 2023"),
    MAY2022("May 2022"),
    JUNE2022("June 2022"),
    JULY2022("July 2022"),
    AUGUST2022("August 2022"),
    SEPTEMBER2022("September 2022"),
    OCTOBER2022("October 2022"),
    NOVEMBER2022("November 2022"),
    DECEMBER2022("December 2022");

    MonthComponents(String month) {
        this.month=month;
    }
    private final String month;

    public String getMonth() {
        return month;
    }
}
