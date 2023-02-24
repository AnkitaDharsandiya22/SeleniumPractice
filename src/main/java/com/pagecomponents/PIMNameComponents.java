package com.pagecomponents;

public enum PIMNameComponents {
    FIRSTNAME("firstName"),
    MIDDLENAME("middleName"),
    LASTNAME("lastName");

    public String getName() {
        return name;
    }
    private final String name;
    PIMNameComponents(String name) {
        this.name = name;
    }

}
