package com.pagecomponents;

public enum TopMenuComponents {
    CLASSES("Classes"),
    TEACHERS("Teachers"),
    STUDENTS("Students"),
    GRADES("Grades");


    TopMenuComponents(String topMenu) {
       this.topMenu=topMenu;
    }
    private final String topMenu;

    public String getTopMenu() {
        return topMenu;
    }
}
