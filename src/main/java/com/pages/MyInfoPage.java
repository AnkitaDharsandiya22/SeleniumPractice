package com.pages;

import com.base.BasePage;
import com.driver.DriverManager;
import com.enums.Waits;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.time.Duration;

public class MyInfoPage extends BasePage {
    private final By FIRSTNAME = By.xpath("//input[@name='firstName']");
    private final By LASTNAME = By.xpath("//input[@name='lastName']");
    private final By EMPLOYEE_ID = By.xpath("//label[text()='Employee Id']/following::div[1]");
    private final By SAVE_BUTTON = By.xpath("//*[normalize-space()='* Required']/following::button[1]");
    static String emp_id;

    public static MyInfoPage useMyInfoPage() {
        return new MyInfoPage();
    }

    public MyInfoPage setFirstName(String value) {
        getElement(FIRSTNAME, Waits.PRESENCE).clear();
        sendKeys(FIRSTNAME, Waits.NONE, value, "First Name");
        return this;

    }

    public MyInfoPage setLastName(String value) {
        getElement(LASTNAME, Waits.PRESENCE).clear();
        sendKeys(LASTNAME, Waits.PRESENCE, value, "Last Name");
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

}
