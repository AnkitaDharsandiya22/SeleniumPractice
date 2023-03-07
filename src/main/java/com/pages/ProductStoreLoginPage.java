package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.HomeMenuBarComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.pages.ProductStoreSignUp.useProductStoreSignUp;

public class ProductStoreLoginPage extends BasePage {
    private final By TXT_USERNAME1 = By.id("loginusername");
    private final By TXT_PASSWORD1 = By.id("loginpassword");
    private final By BTN_LOGIN = By.xpath("//button[normalize-space()='Log in']");
    private final By BTN_CLOSE = By.xpath("(//button[normalize-space()='Close'])[3]");

    public static ProductStoreLoginPage useProductStoreLoginPage() {
        return new ProductStoreLoginPage();
    }

    public ProductStoreLoginPage setUsername(String value) {
        sendKeys(TXT_USERNAME1, Waits.PRESENCE, value, "User Name");
        return this;
    }

    public ProductStoreLoginPage setPassword(String value) {
        sendKeys(TXT_PASSWORD1, Waits.NONE, value, "Password");
        return this;
    }

    public ProductStoreLoginPage clickOnLoginButton() {
        click(BTN_LOGIN, Waits.NONE, "Login");
        return this;
    }

    public boolean verifyLogIn(HomeMenuBarComponents menuItems) {
        return isElementPresent(generateDynamicByLocator(useProductStoreSignUp().getHOME_MENU(), menuItems.getMenuItems()), Waits.PRESENCE);
    }
}
