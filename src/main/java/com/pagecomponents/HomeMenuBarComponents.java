package com.pagecomponents;

public enum HomeMenuBarComponents {

    HOME("Home "),
    CONTACT("Contact"),
    ABOUTUS("About us"),
    CART("Cart"),
    LOGIN("Log in"),
    LOGOUT("Log out"),
    SIGNUP("Sign up");

    private final String menuItems;

    public String getMenuItems() {
        return menuItems;
    }

    HomeMenuBarComponents(String menuItems) {
        this.menuItems = menuItems;
    }
}
