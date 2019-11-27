package com.applitools.hackathon.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinancialOverview {

    private WebDriver webDriver;

    @FindBy(css = "#flashSale img")
    private WebElement adOne;

    @FindBy(css = "#flashSale2 img")
    private WebElement adTwo;

    public FinancialOverview(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(this.webDriver, this);
    }

    public boolean getAdOneDisplayStatus() {
        boolean isDisplayed;

        try {
            isDisplayed = adOne.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        }

        return isDisplayed;
    }

    public boolean getAdTwoDisplayStatus() {
        boolean isDisplayed;

        try {
            isDisplayed = adTwo.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        }

        return isDisplayed;
    }
}
