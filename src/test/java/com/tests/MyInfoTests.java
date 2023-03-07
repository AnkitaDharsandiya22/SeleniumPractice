package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.LeftMenuBarComponents;
import com.pagecomponents.PIMButtonComponents;
import com.pagecomponents.PIMDataTableComponents;
import com.pages.MyInfoPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.HomePage.useHomePage;
import static com.pages.LoginPage.useLoginPage;
import static com.pages.MyInfoPage.useMyInfoPage;
import static com.pages.PIMPage.usePIMPage;
import static com.utils.VerificationUtils.validate;

public class MyInfoTests extends BaseTest {
    private MyInfoTests() {

    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to update employee detail successfully",
            author = {Author.ANKITA}, category = {Category.SMOKE})
    void testUpdateEmployeeDetails(TestDataSupplier dataSupplier) {
        String firstName_Text = dataSupplier.getFirstName();
        String lastName_Text = dataSupplier.getLastName();
        useLoginPage()
                .performLogin(dataSupplier.getUsername(), dataSupplier.getPassword())
                .selectMenu(LeftMenuBarComponents.MY_INFO);

        useMyInfoPage()
                .setEmployee_ID_MyInfo_Page();
        useMyInfoPage()
                .setFirstName(dataSupplier.getFirstName()).setMiddleName()
                .setLastName(dataSupplier.getLastName());
        useMyInfoPage().clickOnSave();

        useHomePage()
                .selectMenu(LeftMenuBarComponents.PIM);
        useMyInfoPage().searchEmployeeId();
        usePIMPage()
                .clickButton(PIMButtonComponents.SEARCH);
        boolean verifyResult = useMyInfoPage().verifyEmployeeSearch();
        validate(verifyResult, true, "Employee search successfully");

        boolean flag = false;
        if (firstName_Text.equals(useMyInfoPage().verifyEmployeeFirstName(PIMDataTableComponents.FIRSTNAME)) &&
                (lastName_Text.equals(useMyInfoPage().verifyEmployeeLastName(PIMDataTableComponents.LASTNAME))))  {
            flag = true;
        }
        validate(flag, true, "Search Name validated successfully");
    }
}
