package com.applitools.hackathon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver webDriver;

    @FindBy(css = "h4.auth-header")
    private WebElement loginFormName;

    @FindBy(css = "form > div.form-group > label")
    private WebElement usernameLabel;

    @FindBy(css = "form > div.form-group:nth-child(2) > label")
    private WebElement passwordLabel;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "log-in")
    private WebElement loginButton;

    @FindBy(css = "form > div.buttons-w > div.form-check-inline > label")
    private WebElement rememberMeLabel;

    @FindBy(css = "form > div.buttons-w > div.form-check-inline > label > input")
    private WebElement rememberMeCheckbox;

    @FindBy(css = "form > div:nth-child(1) > div")
    private WebElement usernameIcon;

    @FindBy(css = "form > div:nth-child(2) > div")
    private WebElement passwordIcon;

    @FindBy(css = "form > div.buttons-w > div:nth-child(3) > a:nth-child(1) > img")
    private WebElement twitterIcon;

    @FindBy(css = "form > div.buttons-w > div:nth-child(3) > a:nth-child(2) > img")
    private WebElement facebookIcon;

    @FindBy(css = "form > div.buttons-w > div:nth-child(3) > a:nth-child(3) > img")
    private WebElement linkedInIcon;

    @FindBy(css = "div.alert-warning")
    private WebElement alertWarning;


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(this.webDriver, this);
    }

    public void openLoginPage(String url) {
        webDriver.get(url);
    }

    public String gotFormName() {
        return loginFormName.getText();
    }

    public String getUsernameLabel() {
        return usernameLabel.getText();
    }

    public String getPasswordLabel() {
        return passwordLabel.getText();
    }

    public String getUsernameInputField() {
        return usernameField.getText();
    }

    public String getUsernameInputType() {
        return usernameField.getAttribute("type");
    }

    public String getPasswordInputField() {
        return passwordField.getText();
    }

    public String getPasswordInputType() {
        return passwordField.getAttribute("type");
    }

    public String getLoginButtonText() {
        return loginButton.getText();
    }

    public String getRememberMeLabel() {
        return rememberMeLabel.getText();
    }

    public String getRememberMeFieldType() {
        return rememberMeCheckbox.getAttribute("type");
    }

    public boolean getUsernameIcon() {
        return usernameIcon.isDisplayed();
    }

    public boolean getPasswordIcon() {
        return passwordIcon.isDisplayed();
    }

    public boolean getTwitterImageDisplayStatus() {
        return twitterIcon.isDisplayed();
    }

    public boolean getFacebookImageDisplayStatus() {
        return facebookIcon.isDisplayed();
    }

    public boolean getLinkedInImageDisplayStatus() {
        return linkedInIcon.isDisplayed();
    }

    public String getUserNamePlaceholderAttributeValue() {
        return usernameField.getAttribute("placeholder");
    }

    public String getPasswordPlaceholderAttributeValue() {
        return passwordField.getAttribute("placeholder");
    }

    public void enterUsernameAndPassword(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    public void submitLoginForm() {
        loginButton.click();
    }

    public String getLoginErrorMessage() {
        return alertWarning.getText();
    }
}
