package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import com.pagecomponents.TopMenuComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class StudentPage extends BasePage {
    private String top_Menu = "//a[contains(normalize-space(),'%s')]";
    private final By LIST_OF_STUDENTS = By.xpath("//div[@class='media-body']//a");

    public static StudentPage useStudentPage() {
        return new StudentPage();
    }

    public StudentPage selectStudentsTab(TopMenuComponents menu) {
        clickUsingAction(generateDynamicByLocator(top_Menu, menu.getTopMenu()), Waits.PRESENCE, menu.getTopMenu());
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }

    int optionsIndex = 0;

    public StudentPage selectRandomStudent() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

//        List<WebElement> ele = getElements(LIST_OF_STUDENTS, Waits.PRESENCE);
//        int optionsIndex = new Faker().random().nextInt(1, ele.size());
//        WebElement element = ele.stream().filter(e -> ele.indexOf(e) == optionsIndex)
//                .findFirst().orElseThrow(() -> new Exception("Element not found"));
//        element.click();

        selectOption(LIST_OF_STUDENTS, Waits.PRESENCE);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }

}
