package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.LoginPage.useLoginPage;
import static com.utils.VerificationUtils.validate;
public class LoginTest extends BaseTest {

    @Test(dataProvider = "getDataFromExcel",dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to Login",author = Author.ANKITA,category = Category.SANITY)
    void testLogin(TestDataSupplier dataSupplier){
       useLoginPage().clickOnCleverBtn()
               .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
       boolean flag =  useLoginPage().verifyLogin();
       validate(flag,true,"Welcome Verified");
    }
}
