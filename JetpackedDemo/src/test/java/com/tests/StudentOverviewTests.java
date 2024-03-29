package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.DurationComponents;
import com.pagecomponents.OlpComponents;
import com.pagecomponents.SidemenuComponents;
import com.pagecomponents.TopMenuComponents;
import com.pages.HomePage;
import com.pages.SchoolPage;
import com.pages.StudentOverviewPage;
import com.pages.StudentPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.HomePage.useHomePage;
import static com.pages.LoginPage.useLoginPage;
import static com.pages.SchoolPage.useSchoolPage;
import static com.pages.StudentOverviewPage.useStudentOverviewPage;
import static com.pages.StudentPage.useStudentPage;
import static com.utils.VerificationUtils.validate;

public class StudentOverviewTests extends BaseTest {

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test student average is matched with actual", author = Author.ANKITA, category = Category.SANITY)
    void testStudentAvg(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(), dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        useSchoolPage().selectDuration(DurationComponents.LASTWEEK).clickRandomSchool();
        useStudentPage().selectStudentsTab(TopMenuComponents.STUDENTS).selectRandomStudent();
        useStudentOverviewPage().checkStudentAvgAndProgress(OlpComponents.LEXIA);
        boolean result = useStudentOverviewPage().checkStudentAvgAndProgress(OlpComponents.LEXIA);
        validate(result, true, "Student average is matched with actual progress");
    }

    //    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
//    @TestDescription(description = "To test navigation bar shows clicked names", author = Author.ANKITA, category = Category.SANITY)
//    void testNavigationLink(TestDataSupplier dataSupplier) {
//        useLoginPage().clickOnCleverBtn()
//                .performLogin(dataSupplier.getUsername(), dataSupplier.getPassword());
//        HomePage.useHomePage()
//                .clickOnSchools(dataSupplier.getSchools());
//        SchoolPage.useSchoolPage().selectDuration(DurationComponents.LASTWEEK);
//        SchoolPage.useSchoolPage().clickRandomSchool();
//        StudentPage.useStudentPage().selectStudentsTab(TopMenuComponents.STUDENTS).selectRandomStudent();
//        StudentOverviewPage.useStudentOverviewPage().selectOlp(OlpComponents.LIGHTSAIL);
//    }
    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test progress on Student dashboard page", author = Author.ANKITA, category = Category.SANITY)
    void testAverageOnStudentDashboard(TestDataSupplier dataSupplier) throws Exception {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(), dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        useSchoolPage().selectDuration(DurationComponents.LASTWEEK);
        useSchoolPage().clickRandomSchool();
        useStudentPage().selectStudentsTab(TopMenuComponents.STUDENTS).selectRandomStudent();
        boolean result = useStudentOverviewPage().checkProgressAverageWithStudentDashboard();
        validate(result, true, "Progress average verified");
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test student weekly average", author = Author.ANKITA, category = Category.SANITY)
    void testStudentAvgOnWeeklyOverview(TestDataSupplier dataSupplier) throws Exception {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(), dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        useSchoolPage().selectDuration(DurationComponents.LASTWEEK).clickRandomSchool();
        useStudentPage().selectStudentsTab(TopMenuComponents.STUDENTS).selectRandomStudent();
        // StudentOverviewPage.useStudentOverviewPage().clickOnWeeklyOverview().verifyStudentAverage(OlpComponents.LEXIA);
    }
}
