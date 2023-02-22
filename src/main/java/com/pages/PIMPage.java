package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.PIMButtonComponents;
import org.openqa.selenium.By;

public class PIMPage extends BasePage {

    private final String buttons = "//button[normalize-space()='%s']";
    private final By FIRST_NAME = By.name("firstName");
    private final By LAST_NAME = By.name("lastName");
    private final By EMPLOYEE_ID = By.xpath("//input[@name='lastName']/following::input[1]");
    private final By BUTTON_SUBMIT = By.xpath("//button[@type='submit']");
    private final By SUCCESS_MESSAGE = By.xpath("//p[text()='Successfully Saved']");


    public PIMPage clickButton(PIMButtonComponents button) {
        click(generateDynamicByLocator(buttons, button.getButton()), Waits.CLICKABLE, button.getButton());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public static PIMPage usePIMPage() {
        return new PIMPage();
    }

    public PIMPage enterFirstname(String value) {
        sendKeys(FIRST_NAME, Waits.NONE, value, "First Name");
        return this;
    }

    public PIMPage enterLastname(String value) {
        sendKeys(LAST_NAME, Waits.NONE, value, "Last Name");
        return this;
    }

    public PIMPage enterEmployeeId(String value) {
        sendKeys(EMPLOYEE_ID, Waits.NONE, value, "Employee Id");
        return this;
    }
    public PIMPage clickSaveButton(){
        click(BUTTON_SUBMIT,Waits.NONE,"Save");
        return this;
    }
    public boolean isUserSuccessfullySaved() {
        return isElementPresent(SUCCESS_MESSAGE,Waits.VISIBLITY);
    }

}
