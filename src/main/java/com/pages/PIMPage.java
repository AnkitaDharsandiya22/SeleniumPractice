package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.PIMButtonComponents;
import com.pagecomponents.PIMNameComponents;
import org.openqa.selenium.By;

public class PIMPage extends BasePage {
    ////input[@name='lastName']
    private final String buttons = "//button[normalize-space()='%s']";

    private final By EMPLOYEE_ID = By.xpath("//input[@name='lastName']/following::input[1]");
    private final By BUTTON_SUBMIT = By.xpath("//button[@type='submit']");
    private final By SUCCESS_MESSAGE = By.xpath("//p[text()='Successfully Saved']");
    private final String COMPLETE_NAME = "//input[@name='%s']";


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

    public PIMPage setFirstName(PIMNameComponents name,String value) {
        sendKeys(generateDynamicByLocator(COMPLETE_NAME, name.getName()), Waits.NONE,value, name.getName());
        return this;
    }

    public PIMPage setLastName(PIMNameComponents name,String value) {
        sendKeys(generateDynamicByLocator(COMPLETE_NAME, name.getName()), Waits.NONE,value, name.getName());
        return this;
    }
    public PIMPage enterEmployeeName(String firstName,String lastName ){
        setFirstName(PIMNameComponents.FIRSTNAME,firstName)
                .setLastName(PIMNameComponents.LASTNAME,lastName);
        return this;
    }

}
