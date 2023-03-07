package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.HomeMenuBarComponents;
import org.openqa.selenium.By;

public class ProductStoreSignUp extends BasePage {


    private final String HOME_MENU = "//a[contains(normalize-space(),'%s')]";
    static private final By USER_NAME = By.id("sign-username");
    private static final By PASSWORD = By.id("sign-password");
    private static final By SIGNUP = By.xpath("//button[text()='Sign up']");

    public static ProductStoreSignUp useProductStoreSignUp(){
        return new ProductStoreSignUp();
    }
    public ProductStoreSignUp openSignUp(HomeMenuBarComponents menuItems){
        click(generateDynamicByLocator(HOME_MENU, menuItems.getMenuItems()),Waits.CLICKABLE,menuItems.getMenuItems());
        return this;
    }
    public ProductStoreSignUp setUserName(String value){
        sendKeys(USER_NAME, Waits.PRESENCE,value,"User Name");
        return this;
    }
    public ProductStoreSignUp setPassword(String value){
        sendKeys(PASSWORD,Waits.NONE,value,"Password");
        return this;
    }
    public ProductStoreSignUp clickOnSignUpButton(){
        click(SIGNUP,Waits.NONE,"Sign Up");
        //handleAlert(true);
        return this;
    }
    public String getHOME_MENU() {
        return HOME_MENU;
    }

}
