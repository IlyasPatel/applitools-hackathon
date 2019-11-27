package com.applitools.hackathon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExpensesPage {

    private WebDriver webDriver;

    @FindBy(id = "addDataset")
    private WebElement showDataForNextYear;

    public ExpensesPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(this.webDriver, this);
    }

    public void showDataForNextYear() {
        showDataForNextYear.click();
    }
}
