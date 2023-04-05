package com.pages;

import com.base.BasePage;
import com.driver.DriverManager;
import com.enums.Waits;
import com.factories.WaitFactory;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import com.pagecomponents.DurationComponents;
import org.apache.commons.lang3.Range;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.testng.IRetryAnalyzer;


import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class SchoolPage extends BasePage {
    private final By SCHOOL_LIST = By.xpath("//a[contains(@id,'school')]");
    private final String ROW_PERCENTAGE_LIST = "(//div[@class='datatable-row-center datatable-row-group'])[%s]//span";
    private String dynamicSchoolList = "(//a[contains(@id,'school')])[%s]";
    private String school_Percentage_List = "//div[@class='pmd-chart']//span[@class='pmd-dc-percent']";
    private String filter_By = "(//div[@role='combobox'])[2]/following::pmd-checkbox[%s]";
    private final By PROGRAM_NAME = By.xpath("(//input[@id='programNameInputCheckBox'])[1]");
    private final By CHECKBOX_PROGRESS_RANGE = By.xpath("(//input[@id='progressRangeInputCheckBox'])[1]");
    private final By DRP_PROGRAM_LIST = By.xpath("//div[@class='ng-option' or @class='ng-option ng-option-marked']//p");
    private final By TXT_MIN_VALUE = By.xpath("//input[@id='sliderValueMin']");
    private final By TXT_MAX_VALUE = By.xpath("//input[@id='sliderValueMax']");
    private final By BTN_APPLY = By.xpath("//button[@type='submit']");
    private final By LEFT_SLIDER = By.xpath("(//div[@class='noUi-tooltip'])[1]");
    private final By RIGHT_SLIDER = By.xpath("(//div[@class='noUi-tooltip'])[2]");
    private static final By SLIDER = By.xpath("//div[@class='noUi-connect']");
    private final By SEARCH_RESULT = By.xpath("(//span[@class='ng-value-label'])[3]");
    private static final By LIST_OF_RANGE_PERCENTAGE = By.xpath("//ngx-datatable//div[@class='pmd-donut-chart']//span");
    private final By DURATION = By.id("datePickerId");
    private final String list_Duration = "//a[normalize-space()='%s']";
    private final By LIST_OF_PERCENT = By.xpath("//div[@class='pmd-donut-chart']/span");
    private final By Percent = By.xpath("//div[@class='pmd-chart']//span");
    private final By TOOLTIP = By.xpath("//div[@class='tooltip-inner']");
    private final By WEEKLY_OVERVIEW = By.xpath("(//a[@class='nav-link']/span)[1]");

    public static SchoolPage useSchoolPage() {
        return new SchoolPage();
    }

    static int optionsIndex = 0;
    int numberOfSchool = 0;
    List<String> percentageText = new ArrayList<>();
    List<String> percentageTextOnClass = new ArrayList<>();

    public SchoolPage clickRandomSchool() {
        numberOfSchool = WaitFactory.waitForElements(SCHOOL_LIST, Waits.PRESENCE).size();
        optionsIndex = new Faker().random().nextInt(1, numberOfSchool);
        System.out.println("Random school number --" + optionsIndex);
        List<WebElement> listOfPercentages = generateDynamicElements(ROW_PERCENTAGE_LIST, String.valueOf(optionsIndex));
        listOfPercentages.forEach(ele -> percentageText.add(ele.getText()));
        WebElement randomSchool = generateDynamicElement(dynamicSchoolList, String.valueOf(optionsIndex));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        click(randomSchool, Waits.PRESENCE, "School--");
        System.out.println("Random School Name : " + randomSchool);
        return this;
    }

    public SchoolPage selectDuration(DurationComponents duration) {
        click(DURATION, Waits.CLICKABLE, "Duration");
        click(generateDynamicByLocator(list_Duration, duration.getDuration()), Waits.CLICKABLE, duration.getDuration());
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }

    public boolean verifyPercentage() {
        List<WebElement> listOfPercentageOnClass = generateDynamicElements(school_Percentage_List, String.valueOf(optionsIndex));
        listOfPercentageOnClass.forEach(ele -> percentageTextOnClass.add(ele.getText()));
        return percentageText.equals(percentageTextOnClass);
    }

    public SchoolPage selectProgram(String program) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
        clickUsingAction(PROGRAM_NAME, Waits.PRESENCE, "Program Name");
        selectOptionFromList(DRP_PROGRAM_LIST, Waits.VISIBLITY, program);
        return this;
    }

    public SchoolPage selectProgressRange(int minValue, int maxValue) {

        clickUsingAction(CHECKBOX_PROGRESS_RANGE, Waits.NONE, "Progress Range");
        clearTextBox(TXT_MIN_VALUE, Waits.CLICKABLE);
        sendKeys(TXT_MIN_VALUE, Waits.NONE, String.valueOf(minValue), "Minimun Value");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        clearTextBox(TXT_MAX_VALUE, Waits.NONE);
        sendKeys(TXT_MAX_VALUE, Waits.NONE, String.valueOf(maxValue), "Maximum Value");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        return this;
    }

    public SchoolPage selectRangeUsingSlider() {

        clickUsingAction(CHECKBOX_PROGRESS_RANGE, Waits.NONE, "Progress Range");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

        Actions move = new Actions(DriverManager.getDriver());
        int amount = getPixelsToMove(getElement(LEFT_SLIDER, Waits.CLICKABLE), 40, 99, 0);

        System.out.println("Amount " + amount);
        Action action = move.dragAndDropBy(getElement(LEFT_SLIDER, Waits.CLICKABLE), amount, 0).build();
        action.perform();


        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        int amountRight = getPixelsToMoveRight(getElement(RIGHT_SLIDER, Waits.NONE), 30, 100, 1);
        Actions moveRight = new Actions(DriverManager.getDriver());
        Action actionRight = moveRight.dragAndDropBy(getElement(RIGHT_SLIDER, Waits.CLICKABLE), -amountRight, 0).build();
        actionRight.perform();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return this;
    }


    public static int getPixelsToMove(WebElement slider1, long amount, long SliderMax, long SliderMin) {
        WebElement slider = getElement(SLIDER, Waits.NONE);
        return (int) (slider.getSize().getWidth() * amount / 99);

    }

    public static int getPixelsToMoveRight(WebElement slider2, long amountRight, long SliderMax, long SliderMin) {
        WebElement slider = getElement(SLIDER, Waits.NONE);
        return (int) (slider.getSize().getWidth() * amountRight / 100);
    }

    public SchoolPage clickApply() {
        click(BTN_APPLY, Waits.CLICKABLE, "Apply");
        return this;
    }

    List<WebElement> percentangeList = new ArrayList<>();
    List<Integer> percentListText = new ArrayList<>();

    public boolean getRangePercentage() {
        percentangeList = getElements(LIST_OF_RANGE_PERCENTAGE, Waits.PRESENCE);
        percentListText = new ArrayList<>();
        for (WebElement percent : percentangeList) {
            percentListText.add(Integer.parseInt(percent.getText().substring(0, 2)));
        }
        percentListText.sort(Comparator.naturalOrder());
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        if (percentListText.get(0) >= 40 && percentListText.get(percentListText.size() - 1) < 80) {
            return true;
        }
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return true;
    }

    public boolean hoverOverPercentage() {

        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        Actions act = new Actions(DriverManager.getDriver());
        System.out.println("----" + getElement(Percent, Waits.VISIBLITY).getText());
        List<WebElement> listOfPercentage = getElements(Percent, Waits.VISIBLITY);
        for (WebElement e : listOfPercentage) {
            act.moveToElement(e).perform();
            System.out.println(" " + e.getText());
            List<WebElement> listOfTooltip = getElements(TOOLTIP, Waits.PRESENCE);
            for (WebElement w : listOfTooltip) {
                String str = w.getText().replaceAll("[^0-9]+", " ");
                List<String> li = Arrays.asList(str.trim().split(" "));

                long l1 = Integer.parseInt(li.get(1)) * 100 / Integer.parseInt(li.get(2));
                System.out.println("Percentage---" + l1);
                System.out.println(Arrays.asList(str.trim().split(" ")));
                if (l1 == Integer.parseInt(li.get(0))) {
                    return true;
                } else {
                    return false;
                }
            }

        }
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(12));
        return true;
    }
}


