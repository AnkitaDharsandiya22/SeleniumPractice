package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.LeftMenuBarComponents;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.HomePage.useHomePage;
import static com.pages.LoginPage.useLoginPage;
import static com.pages.MyInfoPage.useMyInfoPage;

public class MyInfoTests extends BaseTest {
    private MyInfoTests() {

    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to update employee detail successfully",
            author = {Author.ANKITA}, category = {Category.SMOKE})
    void testUpdateEmployeeDetails(TestDataSupplier dataSupplier) {
        useLoginPage()
                .performLogin(dataSupplier.getUsername(), dataSupplier.getPassword())
                .selectMenu(LeftMenuBarComponents.MY_INFO);

        useMyInfoPage()
                .setEmployee_ID_MyInfo_Page();
        useMyInfoPage()
                .setFirstName(dataSupplier.getFirstName())
                .setLastName(dataSupplier.getLastName());
        useMyInfoPage()
                .clickOnSave();
//        useHomePage()
//                .selectMenu(LeftMenuBarComponents.PIM);

    }
}
