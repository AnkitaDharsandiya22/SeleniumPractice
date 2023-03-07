package com.pages;

import com.base.BasePage;
import com.driver.DriverManager;
import com.enums.Waits;
import com.google.common.util.concurrent.Uninterruptibles;
import com.pagecomponents.PIMDataTableComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.time.Duration;
import java.util.List;

import static com.factories.WaitFactory.waitForElement;

public class MyInfoPage extends BasePage {
    private final By FIRSTNAME = By.xpath("//input[@name='firstName']");
    private final By LASTNAME = By.xpath("//input[@name='lastName']");
    private final By MIDDLENAME = By.xpath("//input[@name='middleName']");
    private final By EMPLOYEE_ID = By.xpath("//label[text()='Employee Id']/following::input[1]");
    private final By SAVE_BUTTON = By.xpath("//*[normalize-space()='* Required']/following::button[1]");
    private final By TABLE_ID = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[2]");
    private final String TABLE_NAMES = "//div[@class='oxd-table-card']//div[%s]/div";

    static String emp_id = null;

    public static MyInfoPage useMyInfoPage() {
        return new MyInfoPage();
    }

    public MyInfoPage setFirstName(String value) {
        clearTextBox(FIRSTNAME, Waits.PRESENCE);
        sendKeys(FIRSTNAME, Waits.NONE, value, "First Name");
        return this;
    }
    public MyInfoPage setMiddleName() {
        clearTextBox(MIDDLENAME, Waits.NONE);
        return this;
    }

    public MyInfoPage setLastName(String value) {
        clearTextBox(LASTNAME, Waits.NONE);
        sendKeys(LASTNAME, Waits.NONE, value, "Last Name");
        return this;
    }

    public void setEmployee_ID_MyInfo_Page() {
        emp_id = getAttributeText(EMPLOYEE_ID, Waits.PRESENCE);
    }

    public String getEmployee_ID_MyInfo_Page() {
        return emp_id;
    }

    public MyInfoPage clickOnSave() {
        clickUsingAction(SAVE_BUTTON, Waits.NONE, "Save");
        return this;
    }

    public MyInfoPage searchEmployeeId() {
        sendKeysUsingAction(EMPLOYEE_ID, Waits.CLICKABLE, getEmployee_ID_MyInfo_Page(), "Employee ID");
        return this;
    }

    public boolean verifyEmployeeSearch() {
        List<WebElement> id = getElements(TABLE_ID, Waits.PRESENCE);
        String searchResult = id.get(0).getText();
        return searchResult.equals(getEmployee_ID_MyInfo_Page());
    }

    public String verifyEmployeeFirstName(PIMDataTableComponents pimDataTableComponents) {
        String firstNameText = getElement(generateDynamicByLocator(TABLE_NAMES, pimDataTableComponents.getName()), Waits.PRESENCE)
                .getText();
        return firstNameText;
    }

    public String verifyEmployeeLastName(PIMDataTableComponents pimDataTableComponents) {
        String lastNameText = getElement(generateDynamicByLocator(TABLE_NAMES, pimDataTableComponents.getName()), Waits.PRESENCE)
                .getText();
        return lastNameText;
    }
}
