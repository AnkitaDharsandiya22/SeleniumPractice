package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.google.common.util.concurrent.Uninterruptibles;
import com.pagecomponents.DistrictComponents;
import org.openqa.selenium.By;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final By BTN_LOGIN_WITH_CLEVER = By.id("openLoginModel");
    private final By TXT_USERNAME = By.id("username");
    private final By TXT_PASSWORD = By.id("password");
    private final By DROP_DOWN_DISTRICT = By.xpath("//div[@class='ng-input']");
    private final String DROP_DOWN_DISTRICT_LIST = "//span[contains(normalize-space(),'%s')]";
    private final By BTN_LOGIN = By.xpath("//button[@id='submitLogin']");
    private final By LBL_USER_NAME = By.xpath("//div[@class='media-body']/h5");
    private final By TXT_WELCOME = By.xpath("//button[@id='burgerMenuId']/following::h1[1]");

    public static LoginPage useLoginPage() {
        return new LoginPage();
    }

    public LoginPage clickOnCleverBtn() {
        click(BTN_LOGIN_WITH_CLEVER, Waits.CLICKABLE, "Login with Clever");
        return this;
    }

    public LoginPage setUserName(String userName) {
        sendKeys(TXT_USERNAME, Waits.PRESENCE, userName, "User Name");
        return this;
    }

    public LoginPage setPassword(String password) {
        sendKeys(TXT_PASSWORD, Waits.NONE, password, "Password");
        return this;
    }

    public LoginPage selectDistrict(DistrictComponents district) {
        click(DROP_DOWN_DISTRICT, Waits.NONE, "Rocketship Public Schools");
        click(generateDynamicByLocator(DROP_DOWN_DISTRICT_LIST, district.getDistrict()), Waits.CLICKABLE, district.getDistrict());
        return this;
    }

    public LoginPage clickLogin() {
        click(BTN_LOGIN, Waits.NONE, "Login");
        return this;
    }

    public HomePage performLogin(String userName, String password) {
        setUserName(userName).setPassword(password).selectDistrict(DistrictComponents.ROCKETSHIPPUBLICSCHOOLS).clickLogin();
        return new HomePage();
    }

    public boolean verifyLogin() {
        return isElementPresent(TXT_WELCOME, Waits.PRESENCE);
    }


}
