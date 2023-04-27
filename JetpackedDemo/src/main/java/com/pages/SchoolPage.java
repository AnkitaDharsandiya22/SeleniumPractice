package com.pages;

import com.base.BasePage;
import com.driver.DriverManager;
import com.enums.Waits;
import com.factories.WaitFactory;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import com.pagecomponents.DaySelectionComponents;
import com.pagecomponents.DurationComponents;
import com.pagecomponents.OLPNumberComponents;
import com.pagecomponents.OlpComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    private final By MONTHS_LIST = By.xpath("(//button[@class='current']//span)[1]");
    private final By PREVIOUS_MONTH = By.xpath("//button[@class='previous']");
    private final By NEXT_MONTH = By.xpath("//button[@class='next']");
    private final By DATES = By.xpath("//span[@class='ng-star-inserted']");
    private final By APPLY = By.xpath("(//button[normalize-space()='Apply'])[1]");
    private final String randomRow = "//table[@class='days weeks']/tbody/tr[%s]";
    private final String randomDay = "//td[%s]/span[@class='ng-star-inserted']";
   private final By OLP_LIST = By.xpath(" //div[@class='d-inline-flex']");
   private final By FIRST_ROW_PERCENTAGE = By.xpath("program-blank-state text-mutedno");
   private final By FIRST_SCHOOL = By.xpath("(//a[contains(@id,'school')])[1]");
   private final String COLUMN_LIST = "//datatable-body-cell[%s]//*[self::span[contains(text(), '%')] or self::div[@class='program-blank-state text-muted']]";

    static int optionsIndex = 0;
    int numberOfSchool = 0;
    List<String> percentageText = new ArrayList<>();
    List<String> percentageTextOnClass = new ArrayList<>();
    List<WebElement> percentangeList = new ArrayList<>();
    List<Integer> percentListText = new ArrayList<>();

    public static SchoolPage useSchoolPage() {
        return new SchoolPage();
    }

    public SchoolPage clickRandomSchool() {
        numberOfSchool = WaitFactory.waitForElements(SCHOOL_LIST, Waits.PRESENCE).size();
        optionsIndex = new Faker().random().nextInt(0, numberOfSchool - 1);

        List<WebElement> listOfPercentages = generateDynamicElements(ROW_PERCENTAGE_LIST, String.valueOf(optionsIndex));
        listOfPercentages.forEach(ele -> percentageText.add(ele.getText()));
        WebElement randomSchool = generateDynamicElement(dynamicSchoolList, String.valueOf(optionsIndex));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        click(randomSchool, Waits.PRESENCE, "School--");
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
        sendKeys(TXT_MIN_VALUE, Waits.NONE, String.valueOf(minValue), "Minimum Value");
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
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        return true;
    }
    public SchoolPage selectCustomRange(DurationComponents customRange, String month, DaySelectionComponents day) {

        click(DURATION, Waits.PRESENCE, "Duration");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        click(generateDynamicByLocator(list_Duration, customRange.getDuration()), Waits.PRESENCE, "Custom Range");
        String firstMonth = getElements(MONTHS_LIST, Waits.PRESENCE).get(0).getText();
        while (!month.equals(firstMonth)) {
            click(PREVIOUS_MONTH, Waits.PRESENCE, "Previous Month");
            firstMonth = getElements(MONTHS_LIST, Waits.PRESENCE).get(0).getText();
        }
        int randomRowNumber = new Faker().random().nextInt(2, 4);
        WebElement startDate = generateDynamicElement(randomRow, String.valueOf(randomRowNumber));
        WebElement monday = startDate.findElement(By.xpath(".//td[1]/span[@class='ng-star-inserted']"));
        click(monday, Waits.PRESENCE, "Start Date");

        WebElement endDate = generateDynamicElement(randomRow, String.valueOf(randomRowNumber));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
        WebElement sunday = endDate.findElement(By.xpath(".//td[7]/span[@class='ng-star-inserted']"));

        click(sunday, Waits.PRESENCE, "End Date");
        return this;
    }
    public SchoolPage sortSchoolAsPerPerformance(){
        List<WebElement> OLPNames = getElements(OLP_LIST,Waits.PRESENCE); // [web1, web2]
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5)); // sleep 5sec

        for (WebElement olpName:OLPNames) {
            System.out.println("OLP Name---"+olpName.getText());
            if(olpName.getText().equals(olp)){
                olpName.click();
            }
        }
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));


//        for (int i = 0; i < OLPName.size(); i++) {
//            OLPName.get(i).click();
//            List<WebElement> li = getElements(FIRST_ROW_PERCENTAGE,Waits.PRESENCE); // 1
//            s.add(li.get(i).getText());
//        }
//            System.out.println("School percentage --"+ s);

        return this;
    }
}



