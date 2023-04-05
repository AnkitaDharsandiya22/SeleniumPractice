package com.pagecomponents;

public enum SidemenuComponents {
    DASHBOARD("Dashboard"),
    SCHOOLS("Schools"),
    FEEDBACK("Feedback"),
   NOTIFICATIONS ("Notifications");

    SidemenuComponents(String sideMenu) {
        this.sideMenu = sideMenu;
    }

    public String getSideMenu() {
        return sideMenu;
    }

    private final String sideMenu;

}
