package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.google.common.base.Supplier;
import com.pagecomponents.HomeMenuBarComponents;
import com.pages.ProductStoreLoginPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.ProductStoreLoginPage.useProductStoreLoginPage;
import static com.pages.ProductStoreSignUp.useProductStoreSignUp;
import static com.utils.VerificationUtils.validate;

public class ProductStoreLoginTests extends BaseTest {
    private ProductStoreLoginTests(){

    }
    @Test (dataProvider = "getDataFromExcel",dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test use is able to login successfully",author = Author.ANKITA,category = Category.SANITY)
    void testLoginProductStore(TestDataSupplier dataSupplier){
        useProductStoreSignUp()
                .openSignUp(HomeMenuBarComponents.LOGIN);
        useProductStoreLoginPage()
                .setUsername(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickOnLoginButton();
        boolean flag = useProductStoreLoginPage().verifyLogIn(HomeMenuBarComponents.LOGOUT);
        validate(flag,true,"Login verified");
    }

}
