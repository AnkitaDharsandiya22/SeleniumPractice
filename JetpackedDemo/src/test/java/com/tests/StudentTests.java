package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.pagecomponents.SidemenuComponents;
import com.pagecomponents.TopMenuComponents;
import com.pages.HomePage;
import com.pages.SchoolPage;
import com.pages.StudentPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.HomePage.useHomePage;
import static com.pages.LoginPage.useLoginPage;

public class StudentTests extends BaseTest {
    @Test(dataProvider = "getDataFromExcel",dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is click on student tab and redirect on student page",category = Category.SANITY,author = Author.ANKITA)
    void testclickOnStudents(TestDataSupplier dataSupplier) {
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        SchoolPage.useSchoolPage().clickRandomSchool();

        StudentPage.useStudentPage().selectStudentsTab(TopMenuComponents.STUDENTS);
        StudentPage.useStudentPage().selectRandomStudent();
    }
    @Test(dataProvider = "getDataFromExcel",dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test search functionality on student page",category = Category.SANITY,author = Author.ANKITA)
    void testSearchResult(TestDataSupplier dataSupplier){
        useLoginPage().clickOnCleverBtn()
                .performLogin(dataSupplier.getUsername(),dataSupplier.getPassword());
        useHomePage()
                .clickOnSchools(SidemenuComponents.SCHOOLS);
        SchoolPage.useSchoolPage().clickRandomSchool();
        StudentPage.useStudentPage().selectStudentsTab(TopMenuComponents.STUDENTS);
        StudentPage.useStudentPage().search("alia");
    }
}
