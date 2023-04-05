package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.DurationComponents;
import com.pagecomponents.SidemenuComponents;
import com.pages.HomePage;
import com.pages.SchoolPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.utils.VerificationUtils.validate;


import static com.pages.LoginPage.useLoginPage;

public class SchoolPageTests extends BaseTest {
    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test random school is clicked", author = Author.ANKITA, category = Category.SANITY)
    void testRandomSchoolclicked(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
               .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        HomePage.useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);

        SchoolPage schoolPage = SchoolPage.useSchoolPage();
        schoolPage.clickRandomSchool();
        boolean result = schoolPage.verifyPercentage();
        validate(result, true, "Percentage verified");
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test progress range is selected", author = Author.ANKITA, category = Category.SANITY)
    void testProgressRangeSelected(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        HomePage.useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        SchoolPage.useSchoolPage().selectDuration(DurationComponents.LASTWEEK).selectProgram(dataSupplier.getProgramName())
                .selectProgressRange(40,80).clickApply().getRangePercentage();

        boolean flag = SchoolPage.useSchoolPage().getRangePercentage();
        validate(flag,true,"Progress range verified");
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to hover", author = Author.ANKITA, category = Category.SANITY)
    void testHover(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        HomePage.useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        SchoolPage.useSchoolPage().hoverOverPercentage();

    }
}
