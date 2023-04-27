package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.DaySelectionComponents;
import com.pagecomponents.DurationComponents;
import com.pagecomponents.OLPNumberComponents;
import com.pagecomponents.SidemenuComponents;
import com.pages.HomePage;
import com.pages.SchoolPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.HomePage.useHomePage;
import static com.pages.LoginPage.first;
import static com.pages.LoginPage.useLoginPage;
import static com.pages.SchoolPage.useSchoolPage;
import static com.utils.VerificationUtils.validate;

public class SchoolPageTests extends BaseTest {
    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test average percentage on school page and on each school page should be same ", author = Author.ANKITA, category = Category.SANITY)
    void testEachSchoolAvgPercentage(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
               .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);

        SchoolPage schoolPage = useSchoolPage();
        schoolPage.clickRandomSchool();
        boolean result = schoolPage.verifyPercentage();
        validate(result, true, "Percentage verified");
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test progress range is selected", author = Author.ANKITA, category = Category.SANITY)
    void testProgressRangeSelected(TestDataSupplier dataSupplier) {
        first().clickOnCleverBtn().then()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        useSchoolPage().selectDuration(DurationComponents.LASTWEEK).selectProgram(dataSupplier.getProgramName())
                .selectProgressRange(40,80).clickApply().getRangePercentage();

        boolean flag = useSchoolPage().getRangePercentage();
        validate(flag,true,"Progress range verified");
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to hover", author = Author.ANKITA, category = Category.SANITY)
    void testHover(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        useSchoolPage().hoverOverPercentage();

    }
    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user can choose custom range", author = Author.ANKITA, category = Category.SANITY)
    void testCustomRange(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        // Please pass month and year in this format --JANUARY 2023
        useSchoolPage().selectCustomRange(DurationComponents.CUSTOMRANGE, "JANUARY 2023", DaySelectionComponents.ONE);
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test top school in different olp", author = Author.ANKITA, category = Category.SANITY)
    void testOlpPerformance(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        useSchoolPage().selectDuration(DurationComponents.LASTWEEK);
       useSchoolPage().sortSchoolAsPerPerformance(OLPNumberComponents.LIGHTSAIL);
    }
}
