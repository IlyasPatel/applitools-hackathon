package com.applitools.hackathon.pageobjects;

import com.applitools.hackathon.domain.TransactionsTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class RecentTransactionsPage {

    private WebDriver webDriver;

    @FindBy(id = "amount")
    private WebElement amountHeading;

    @FindBy(css = "#transactionsTable > tbody > tr")
    private List<WebElement> recentTransactionsTable;

    @FindBy(css = "#transactionsTable > thead > tr > th")
    private List<WebElement> columns;

    @FindBy(css = "#transactionsTable > tbody > tr")
    private List<WebElement> rows;

    public RecentTransactionsPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(this.webDriver, this);
    }

    public void selectAmountHeader() {
        amountHeading.click();
    }

    public List<TransactionsTable> getRecentTransactionsData() {

        List<TransactionsTable> tableRows = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> td = row.findElements(By.tagName("td"));

            TransactionsTable staticRecentTransactionsTable = new TransactionsTable(
                td.get(0).getText(),
                td.get(1).getText(),
                td.get(2).getText(),
                td.get(3).getText(),
                td.get(4).getText()
            );

            tableRows.add(staticRecentTransactionsTable);
        }

        return tableRows;
    }
}
