package com.applitools.hackathon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopHeaderPage {

    private WebDriver webDriver;

    @FindBy(id = "showExpensesChart")
    private WebElement compareExpenses;

    public TopHeaderPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(this.webDriver, this);
    }

    public void selectCompareExpenses() {
        compareExpenses.click();
    }
}
