package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.ProfileComponents;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.LoginPage.useLoginPage;
import static com.utils.VerificationUtils.validate;

public final class HomePageTests extends BaseTest {
    private HomePageTests() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test whether user is able to successfully logout",
            author = {Author.CHINMAY, Author.ANKITA}, category = {Category.SMOKE, Category.SANITY})
    void testLogout(TestDataSupplier dataSupplier) {
        useLoginPage()
                .setEmailAddress(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickLogin()
                .selectProfileOption(ProfileComponents.LOGOUT);

        validate(useLoginPage().isLoginPageElementPresent(), true, "User is redirected to Login page when clicks Logout");
    }

}
