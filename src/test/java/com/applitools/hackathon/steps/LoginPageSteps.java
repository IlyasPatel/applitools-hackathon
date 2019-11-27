package com.applitools.hackathon.steps;

import com.applitools.hackathon.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginPageSteps {

    private final WebDriver webDriver;

    private LoginPage loginPage;
    private LoggedInSteps loggedInSteps;

    public LoginPageSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.loginPage = new LoginPage(this.webDriver);
        this.loggedInSteps = new LoggedInSteps(this.webDriver);
    }

    public void openLoginForm(String url) {
        loginPage.openLoginPage(url);
    }

    public void openLoginFormWithAds(String url) {
        loginPage.openLoginPage(url);
    }

    public String getNameOfForm() {
        return loginPage.gotFormName();
    }

    public String getUsernameLabel() {
        return loginPage.getUsernameLabel();
    }

    public String getPasswordLabel() {
        return loginPage.getPasswordLabel();
    }

    public String getUsernameInputField() {
        return loginPage.getUsernameInputField();
    }

    public String getUsernameInputFieldType() {
        return loginPage.getUsernameInputType();
    }

    public String getPasswordInputField() {
        return loginPage.getPasswordInputField();
    }

    public String getPasswordInputFieldType() {
        return loginPage.getPasswordInputType();
    }

    public String getLoginButtonText() {
        return loginPage.getLoginButtonText();
    }

    public String getRememberMeLabel() {
        return loginPage.getRememberMeLabel();
    }

    public String getRememberMeFieldType() {
        return loginPage.getRememberMeFieldType();
    }

    public boolean getUsernameIcon() {
        return loginPage.getUsernameIcon();
    }

    public boolean getPasswordIcon() {
        return loginPage.getPasswordIcon();
    }

    public boolean isTwitterIconDisplayed() {
        return loginPage.getTwitterImageDisplayStatus();
    }

    public boolean isFacebookIconDisplayed() {
        return loginPage.getFacebookImageDisplayStatus();
    }

    public boolean isLinkedInIconDisplayed() {
        return loginPage.getLinkedInImageDisplayStatus();
    }

    public String getUsernamePlaceholderText() {
        return loginPage.getUserNamePlaceholderAttributeValue();
    }

    public String getPasswordPlaceholderText() {
        return loginPage.getPasswordPlaceholderAttributeValue();
    }

    public void submitCredentials(String username, String password) {
        loginPage.enterUsernameAndPassword(username, password);
        loginPage.submitLoginForm();
    }

    public String getErrorMessageDisplayedToUser() {
        return loginPage.getLoginErrorMessage();
    }

    public void login(String url, String username, String password) {
        openLoginForm(url);
        submitCredentials(username, password);
        loggedInSteps.waitForCustomerNameToBeDisplayed();
    }
}
