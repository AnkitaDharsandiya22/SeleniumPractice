package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.SidemenuComponents;
import com.pages.HomePage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;
import static com.utils.VerificationUtils.validate;

import static com.pages.LoginPage.useLoginPage;


public class HomeTests extends BaseTest {
    @Test(dataProvider = "getDataFromExcel",dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test school is clicked",author = Author.ANKITA,category = Category.SANITY)
    void testClickOnSchools(TestDataSupplier dataSupplier){
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        HomePage.useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        boolean flag = HomePage.useHomePage().verifySchoolClick();
        validate(flag,true,"Redirected on school page verified");
    }

    @Test(dataProvider = "getDataFromExcel",dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test navigation path",author = Author.ANKITA,category = Category.SANITY)
    void testVerifySchoolNavigation(TestDataSupplier dataSupplier){
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        HomePage.useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        boolean flag = HomePage.useHomePage().verifySchoolsNavigation(SidemenuComponents.SCHOOLS);
        validate(flag,true,"Navigation Shows correctly");
    }
}
