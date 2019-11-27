package com.applitools.hackathon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class LeftHandPanelPage {

    private final WebDriver webDriver;
    private String loggedInUsername = "div.logged-user-w > div.logged-user-i > div.logged-user-info-w > div.logged-user-name";

    public LeftHandPanelPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(this.webDriver, this);
    }

    public String getCustomerName() {
        return webDriver.findElement(By.cssSelector(loggedInUsername)).getText();
    }

    public boolean isCustomerNameToBeDisplayed() {
        Wait<WebDriver> wait = new FluentWait<>(webDriver);

        WebElement loggedInElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loggedInUsername)));

        return loggedInElement.isDisplayed();
    }
}
