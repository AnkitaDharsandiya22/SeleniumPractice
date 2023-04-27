package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.google.common.util.concurrent.Uninterruptibles;
import com.pagecomponents.TopMenuComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;

public class StudentPage extends BasePage {
    private String top_Menu = "//a[contains(normalize-space(),'%s')]";
    private final By LIST_OF_STUDENTS = By.xpath("//div[@class='media-body']//a");
    private final By TXT_SEARCH = By.xpath("(//input[@type='text'])[3]");
    private final By SEARCH_RESULT_LIST = By.xpath("//div[@class='media-body']//a");
    int optionsIndex = 0;

    public static StudentPage useStudentPage() {
        return new StudentPage();
    }

    public StudentPage selectStudentsTab(TopMenuComponents menu) {
        clickUsingAction(generateDynamicByLocator(top_Menu, menu.getTopMenu()), Waits.PRESENCE, menu.getTopMenu());
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }

    public StudentPage selectRandomStudent() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        selectOption(LIST_OF_STUDENTS, Waits.PRESENCE);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }
    public StudentPage search(String value) {
        click(TXT_SEARCH, Waits.PRESENCE, "Search");
        sendKeys(TXT_SEARCH, Waits.NONE, value, "Search");
        List<WebElement> searchResults = getElements(SEARCH_RESULT_LIST, Waits.PRESENCE);

        for (WebElement searchResult : searchResults) {
            if (searchResult.getText().equals(searchResults)) {
                searchResult.click();
                break;
            }
            else {
                System.out.println("Student not found:");
            }
        }
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }
}
