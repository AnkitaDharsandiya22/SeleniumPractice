package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.HomeMenuBarComponents;
import com.pages.ProductStoreSignUp;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.ProductStoreSignUp.useProductStoreSignUp;

public class ProductStoreSignUpTests extends BaseTest {
    private ProductStoreSignUpTests(){

    }
    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to sign up successfully",
            author = Author.ANKITA, category = Category.SMOKE)
    void testSignUp(TestDataSupplier dataSupplier) {
        useProductStoreSignUp()
                .openSignUp(HomeMenuBarComponents.SIGNUP)
                .setUserName(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickOnSignUpButton();
    }

}
