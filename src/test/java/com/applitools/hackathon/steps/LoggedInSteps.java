package com.applitools.hackathon.steps;

import com.applitools.hackathon.pageobjects.LeftHandPanelPage;
import org.openqa.selenium.WebDriver;

public class LoggedInSteps {

    private final WebDriver webDriver;

    private LeftHandPanelPage leftHandPanelPage;

    public LoggedInSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.leftHandPanelPage = new LeftHandPanelPage(this.webDriver);
    }

    public String getCustomerName() {
        return leftHandPanelPage.getCustomerName();
    }

    public void waitForCustomerNameToBeDisplayed() {
        leftHandPanelPage.isCustomerNameToBeDisplayed();
    }
}
