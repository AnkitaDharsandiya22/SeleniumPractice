package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.OlpComponents;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class StudentOverviewPage extends BasePage {
    private final By LBL_STUDENT_AVG = By.xpath("//span[@class='pmd-dc-percent']");
    private final By ACTUAL_PROGRESS = By.xpath("//table//tbody//tr[2]//td[3]");
    private final By TARGET_USAGE = By.xpath("//table//tbody//tr[1]//td[2]");
    private final By OLP_LIST = By.xpath("//div[@class='media-body']//h3");
    private final By ACTUAL_USAGE = By.xpath("//table/tbody/tr[2]/td[2]");
    private final By olp_Box = By.xpath("//div[@class='card-wrapper d-flex']");
    private final By WEEKLY_OVERVIEW = By.xpath("(//a[@class='nav-link']/span)[1]");
    private final By NAVIGATION_LINK = By.xpath("//div[@class='pmd-breadcrumb-section has-subtitle']//li");
    private final By STUDENT_DASHBOARD = By.xpath("//span[normalize-space()='view student dashboard']");
    private final By PROGRESS_AVG = By.xpath("//h3[@class='mb-3']/following::div[1]");
    private final By PROGRESS = By.xpath("//table//tbody//tr//td[3]");
    private final By TARGET_PROGRESS = By.xpath("//table/tbody/tr[1]/td[3]");
    private final By STUDENT_AVERAGE_DAY_WISE = By.xpath("//ngx-datatable//datatable-body-cell[2]//span");
    private final By OLP_LIST_WEEKLY_OVERVIEW = By.xpath("//div[@class='d-inline-flex']/p");
    private final By OVERVIEW = By.xpath("//span[normalize-space()='Overview']");


    public static StudentOverviewPage useStudentOverviewPage() {
        return new StudentOverviewPage();
    }

    public boolean checkStudentAvgAndProgress(OlpComponents olp) {

        boolean result = true;
        List<WebElement> olpNameList = getElements(OLP_LIST, Waits.PRESENCE);
        List<WebElement> actualUsage = getElements(ACTUAL_USAGE, Waits.PRESENCE).stream()
                .filter(t -> StringUtils.isNotBlank(t.getText())).collect(Collectors.toList());
        List<WebElement> studentAvg = getElements(LBL_STUDENT_AVG, Waits.PRESENCE);
        List<WebElement> actualProgress = getElements(ACTUAL_PROGRESS, Waits.PRESENCE).stream()
                .filter(t -> StringUtils.isNotBlank(t.getText())).collect(Collectors.toList());
        actualUsage.forEach(t -> {
            System.out.println("Actual Usage--" + t.getText());
        });
        studentAvg.forEach(t -> {
            System.out.println("studentAvg" + t.getText());
        });
        actualProgress.forEach(t -> {
            System.out.println("actualProgress" + t.getText());
        });

        System.out.println("olpNameList: " + olpNameList.size());
        System.out.println("actualUsage: " + getElements(ACTUAL_USAGE, Waits.PRESENCE).size());
        System.out.println("studentAvg: " + studentAvg.size());
        System.out.println("actualProgress: " + getElements(ACTUAL_PROGRESS, Waits.PRESENCE).size());
        for (int i = 0; i < olpNameList.size(); i++) {
            if ("Lightsail ".equals(olpNameList.get(i).getText())) {
                System.out.println("studentAvg: " + studentAvg.get(i).getText() + "  actualUsages: " + actualUsage.get(i).getText().replaceAll("[^\\d.]", ""));
            } else {
                System.out.println("studentAvg: " + studentAvg.get(i).getText() + "  actualProgress: " + actualProgress.get(i).getText().replaceAll("[^\\d.]", ""));
            }
            if (studentAvg.get(i).getText().equals(actualUsage.get(i).getText().replaceAll("[^\\d.]", "")) || studentAvg.get(i).getText().equals(actualProgress.get(i).getText().replaceAll("[^\\d.]", ""))) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }
    public StudentOverviewPage clickStudentDashboard() {
        click(STUDENT_DASHBOARD, Waits.PRESENCE, "Student Dashboard");
        return this;
    }
    public boolean checkProgressAverageWithStudentDashboard() {
        List<String> actualUsage = getElements(ACTUAL_USAGE, Waits.PRESENCE).stream()
                .filter(t -> StringUtils.isNotBlank(t.getText())).map(t -> t.getText().replaceAll("[^\\d.]", "")).collect(Collectors.toList());
        List<String> targetUsage = getElements(TARGET_USAGE, Waits.PRESENCE).stream()
                .filter(t -> StringUtils.isNotBlank(t.getText())).map(t -> t.getText().replaceAll("[^\\d.]", "")).collect(Collectors.toList());
        List<String> targetProgress = getElements(TARGET_PROGRESS, Waits.PRESENCE).stream()
                .filter(t -> StringUtils.isNotBlank(t.getText())).map(t -> t.getText().replaceAll("[^\\d.]", "")).collect(Collectors.toList());
        List<String> actualProgress = getElements(ACTUAL_PROGRESS, Waits.PRESENCE).stream()
                .filter(t -> StringUtils.isNotEmpty(t.getText())).map(t -> t.getText().replaceAll("[^\\d.]", "")).collect(Collectors.toList());
        List<String> olpNameList = getElements(OLP_LIST, Waits.PRESENCE).stream().map(t -> t.getText()).collect(Collectors.toList());


        click(STUDENT_DASHBOARD, Waits.PRESENCE, "Student Dashboard");
        switchToTab(2);
        List<WebElement> progressAvg = getElements(PROGRESS_AVG, Waits.VISIBLITY);

        for (int i = 0; i < olpNameList.size(); i++) {
            System.out.println("Size of Average progress-- " + progressAvg.size());
            System.out.println("Usage Target - " + targetUsage.get(i));
            System.out.println("Usage actual - " + actualUsage.get(i));
            System.out.println("Progress Actual - " + actualProgress.get(i));
            System.out.println("Progress Target - " + targetProgress.get(i));
            System.out.println("Compare res -- " + actualProgress.get(i) + "/" + targetProgress.get(i));
            System.out.println("Progress average- - " + progressAvg.get(i).getText());
            if ("Lightsail".equals(olpNameList.get(i))) {
                if (!progressAvg.get(i).getText().equals(actualUsage.get(i).strip() + "/" + targetUsage.get(i).strip())) {
                    return false;
                }
            } else {
                if (!progressAvg.get(i).getText().equals(actualProgress.get(i).strip() + "/" + targetProgress.get(i).strip())) {
                    return false;
                }
            }
        }
        return true;
    }

    public StudentOverviewPage clickOnWeeklyOverview() {
        click(WEEKLY_OVERVIEW, Waits.PRESENCE, "Weekly Overview");
        return this;
    }

//    public Integer verifyStudentAverage(OlpComponents olpName) {
//
//        List<WebElement> dayAverage = getElements(STUDENT_AVERAGE_DAY_WISE, Waits.PRESENCE).stream().filter(t->StringUtils.isNotBlank(t.getText())).mapToInt(Integer::intValue).sum();
//        List<String> total = new ArrayList<>();
//        for (WebElement perDay : dayAverage) {
//            perDay.getText();
//            total.add(perDay.getText());
//        }
//
//        System.out.println("Day wise Data-" + total);
//        int sum = 0;
//        for (int i = 0; i < total.size(); i++) {
//            sum += Integer.parseInt(total.get(i));
//        }
//        System.out.println("Sum of day wise data:" + sum);
//        System.out.println(getElementText(OLP_LIST_WEEKLY_OVERVIEW, Waits.PRESENCE));
//        click(OVERVIEW, Waits.PRESENCE, "Overview");
//       String olp = getElementText(OLP_LIST_WEEKLY_OVERVIEW, Waits.PRESENCE);
//       selectOptionFromList(OLP_LIST,Waits.PRESENCE,olp);
//
//        String text = getElementText(LEXIA_ACTUAL_PROGRESS, Waits.PRESENCE).replaceAll("[^\\d.]", "");
//        System.out.println("Overview page data-" + text);
//        System.out.println("Weekly Overview page data-" + (String.valueOf(sum)));
//        if (text.equals(String.valueOf(sum))) {
//
//        }
//        return 0;
//    }
//

}




