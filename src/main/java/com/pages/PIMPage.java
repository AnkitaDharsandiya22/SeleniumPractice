package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import org.openqa.selenium.By;

public class PIMPage extends BasePage {

    private final String buttons = "//button[normalize-space()='%s']";
    private final By firstName = By.name("firstName");
    private final By middleName = By.name("middleName");
    private final By lastName = By.name("lastName");


    public PIMPage clickButton(PIMButtonComponents button) {
        String xpath = String.format(buttons, button.getButton());

        click(getElement(By.xpath(xpath), Waits.CLICKABLE), Waits.NONE, button.getButton());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public static PIMPage usePIMPage(){
        return new PIMPage();
    }
}
