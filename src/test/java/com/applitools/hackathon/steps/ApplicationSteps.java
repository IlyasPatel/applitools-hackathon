package com.applitools.hackathon.steps;

import com.applitools.hackathon.pageobjects.ExpensesPage;
import com.applitools.hackathon.pageobjects.FinancialOverview;
import com.applitools.hackathon.pageobjects.RecentTransactionsPage;
import com.applitools.hackathon.domain.TransactionsTable;
import com.applitools.hackathon.pageobjects.TopHeaderPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ApplicationSteps {

    private WebDriver webDriver;

    private RecentTransactionsPage recentTransactionsPage;
    private FinancialOverview financialOverview;
    private TopHeaderPage topHeaderPage;
    private ExpensesPage expensesPage;

    public ApplicationSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.recentTransactionsPage = new RecentTransactionsPage(webDriver);
        this.financialOverview = new FinancialOverview(webDriver);
        this.topHeaderPage = new TopHeaderPage(webDriver);
        this.expensesPage = new ExpensesPage(webDriver);
    }

    public List<TransactionsTable> arrangeAndGetRecentTransactionsByAmount() {
        recentTransactionsPage.selectAmountHeader();
        return recentTransactionsPage.getRecentTransactionsData();
    }

    public boolean isAdOneDisplayed() {
        return financialOverview.getAdOneDisplayStatus();
    }

    public boolean isAdTwoDisplayed() {
        return financialOverview.getAdTwoDisplayStatus();
    }

    public List<TransactionsTable> getRecentTransactions() {
        return recentTransactionsPage.getRecentTransactionsData();
    }

    public void selectCompareExpenses() {
        topHeaderPage.selectCompareExpenses();
    }

    public void showDataForNextYear() {
        expensesPage.showDataForNextYear();
    }
}
