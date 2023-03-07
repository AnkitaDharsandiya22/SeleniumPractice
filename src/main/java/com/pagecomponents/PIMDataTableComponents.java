package com.pagecomponents;

public enum PIMDataTableComponents {

    FIRSTNAME("3"),
    LASTNAME("4");

    private final String names;

    public String getName() {
        return names;
    }

    PIMDataTableComponents(String names) {
        this.names=names;
    }
}
