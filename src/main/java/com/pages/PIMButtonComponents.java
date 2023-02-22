package com.pages;

import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;

public enum PIMButtonComponents {
    ADD("Add"),
    SEARCH("Search"),
    RESET("Reset");

    public String getButton(){
        return button;
    }
    private final String button;
    PIMButtonComponents(String button) {
        this.button = button;
    }

}
