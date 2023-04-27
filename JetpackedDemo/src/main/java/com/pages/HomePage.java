package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.SidemenuComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    private final String SIDE_MENU = "//li[@pmdtooltip='%s']";
    private final By LBL_SCHOOL = By.xpath("//button[@id='burgerMenuId']/following::h1[1]");
    private final By NAVIGATION_LINK = By.xpath("//div[@class='pmd-breadcrumb-section has-subtitle']//li");

    public static HomePage useHomePage() {
        return new HomePage();
    }

    public HomePage clickOnSchools(SidemenuComponents sideMenu) {
       click(generateDynamicByLocator(SIDE_MENU,sideMenu.getSideMenu()),Waits.PRESENCE,"Schools");
        return this;
    }

    public boolean verifySchoolClick() {
        return isElementPresent(LBL_SCHOOL, Waits.PRESENCE);
    }

    public boolean verifySchoolsNavigation(SidemenuComponents value) {
        List<WebElement> dropdownOptions = getElements(NAVIGATION_LINK, Waits.PRESENCE);
        for (WebElement dropdownOption : dropdownOptions) {
            if (dropdownOption.getText().equals(value)) {
                isElementPresent(NAVIGATION_LINK, Waits.PRESENCE);
            }
        }
        return true;
    }
}
