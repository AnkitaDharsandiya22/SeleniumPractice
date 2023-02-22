package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.LeftMenuBarComponents;
import com.pagecomponents.PIMButtonComponents;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import com.utils.VerificationUtils;
import org.testng.annotations.Test;

import static com.pages.LoginPage.useLoginPage;
import static com.pages.PIMPage.usePIMPage;
import static com.utils.VerificationUtils.validate;

public class PIMPageTests extends BaseTest {
    private PIMPageTests() {

    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to add employee successfully",
            author = {Author.NEELESH, Author.ANKITA}, category = {Category.SMOKE, Category.SANITY})
    void testPIMAddUser(TestDataSupplier dataSupplier) {
        useLoginPage()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword())
                .selectMenu(LeftMenuBarComponents.PIM);
        usePIMPage()
                .clickButton(PIMButtonComponents.ADD)
                .enterFirstname(dataSupplier.getFirstName())
                .enterLastname(dataSupplier.getLastName())
                .enterEmployeeId(dataSupplier.getEmployeeId())
                .clickSaveButton();
        boolean user = usePIMPage().isUserSuccessfullySaved();

        validate(user,true,"New Employee successfully saved");

    }
}
