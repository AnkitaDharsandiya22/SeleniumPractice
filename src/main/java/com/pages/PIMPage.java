package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.PIMButtonComponents;
import com.pagecomponents.PIMNameComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PIMPage extends BasePage {

    static String employee_id;
    private final String buttons = "//button[normalize-space()='%s']";

    private final By EMPLOYEE_ID = By.xpath("//input[@name='lastName']/following::input[1]");
    private final By BUTTON_SUBMIT = By.xpath("//button[@type='submit']");
    private final By SUCCESS_MESSAGE = By.xpath("//p[text()='Successfully Saved']");
    private final String COMPLETE_NAME = "//input[@name='%s']";
    private final By SEARCH_EMPLOYEE_ID = By.xpath("//label[text()='Employee Id']/following::input[1]");
    private final By SEARCH_RESULT = By.xpath("//span[text()='(1) Record Found']");
    private final By TABLE_DATA_ID = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[2]");

    public PIMPage clickButton(PIMButtonComponents button) {

        click(generateDynamicByLocator(buttons, button.getButton()), Waits.CLICKABLE, button.getButton());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;

    }

    public void setEmployeeId() {
        employee_id = getAttributeText(EMPLOYEE_ID, Waits.PRESENCE);
    }

    public String getEmployeeId() {
        return employee_id;
    }

    public static PIMPage usePIMPage() {
        return new PIMPage();
    }

    public PIMPage enterEmployeeId(String value) {
        sendKeys(EMPLOYEE_ID, Waits.NONE, value, "Employee Id");
        return this;
    }

    public PIMPage clickSaveButton() {
        click(BUTTON_SUBMIT, Waits.NONE, "Save");
        return this;
    }

    public boolean isUserSuccessfullySaved() {
        return isElementPresent(SUCCESS_MESSAGE, Waits.VISIBLITY);
    }

    public PIMPage setFirstName(PIMNameComponents name, String value) {
        sendKeys(generateDynamicByLocator(COMPLETE_NAME, name.getName()), Waits.NONE, value, name.getName());
        return this;
    }

    public PIMPage setLastName(PIMNameComponents name, String value) {
        sendKeys(generateDynamicByLocator(COMPLETE_NAME, name.getName()), Waits.NONE, value, name.getName());
        return this;
    }

    public PIMPage enterEmployeeName(String firstName, String lastName) {
        setFirstName(PIMNameComponents.FIRSTNAME, firstName)
                .setLastName(PIMNameComponents.LASTNAME, lastName);
        return this;
    }

    public PIMPage searchValidation() {
        sendKeys(SEARCH_EMPLOYEE_ID, Waits.PRESENCE, getEmployeeId(), "Employee Id");
        return this;
    }

    public String searchResultValidation() {
        return getElementText(SEARCH_RESULT, Waits.PRESENCE);
    }

    public boolean searchUsingId() {
        List<WebElement> li = getElements(TABLE_DATA_ID, Waits.PRESENCE);
        String searchIdText = li.get(0).getText();
        return searchIdText.equals(getEmployeeId());
    }



}
