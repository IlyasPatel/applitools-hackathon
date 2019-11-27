package com.applitools.hackathon.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class LoginFormAssert extends AbstractAssert<LoginFormAssert, String> {

    public LoginFormAssert(String actual) {
        super(actual, LoginFormAssert.class);
    }

    public static LoginFormAssert assertThat(String actual) {
        return new LoginFormAssert(actual);
    }

    public void loginFormHeadingIs(String expected) {

        Assertions.assertThat(actual)
                .as("Login Form Heading")
                .isEqualTo(expected);
    }

    public void usernameLabelIs(String expected) {
        Assertions.assertThat(actual)
                .as("Username label")
                .isEqualTo(expected);
    }

    public void usernameInputFieldIsEmpty() {
        Assertions.assertThat(actual)
                .as("Username input field")
                .isEqualTo("");
    }

    public void usernameFieldIsTextField() {
        Assertions.assertThat(actual)
                .as("Username input field is a textbox")
                .isEqualTo("text");
    }

    public void passwordLabelIs(String expected) {
        Assertions.assertThat(actual)
                .as("Password label")
                .isEqualTo(expected);
    }

    public void passwordInputFieldIsEmpty() {
        Assertions.assertThat(actual)
                .as("Password input field")
                .isEqualTo("");
    }

    public void passwordFieldIsTextField() {
        Assertions.assertThat(actual)
                .as("Password input field is a textbox")
                .isEqualTo("password");
    }

    public void loginButtonSays(String expected) {
        Assertions.assertThat(actual)
                .as("Login button text")
                .isEqualTo(expected);
    }

    public void rememberMeTextIs(String expected) {
        Assertions.assertThat(actual)
                .as("Remember Me label")
                .isEqualTo(expected);
    }

    public void rememberMeFieldIsCheckbox() {
        Assertions.assertThat(actual)
                .as("Remember Me checkbox")
                .isEqualTo("checkbox");
    }

    public void usernameIconIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Username Icon 'IsDisplayed()'")
                .isEqualTo("true");
    }

    public void passwordIconIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Password Icon 'IsDisplayed()'")
                .isEqualTo("true");
    }

    public void twitterImageIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Twitter Icon 'IsDisplayed()'")
                .isEqualTo("true");
    }

    public void facebookImageIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Facebook Icon 'IsDisplayed()'")
                .isEqualTo("true");
    }

    public void linkedInImageIsDisplayed() {
        Assertions.assertThat(actual)
                .as("LinkedIn Icon 'IsDisplayed()'")
                .isEqualTo("true");
    }

    public void usernamePlaceholderTextIs(String expected) {
        Assertions.assertThat(actual)
                .as("Username placeholder text")
                .isEqualTo(expected);
    }

    public void passwordPlaceholderTextIs(String expected) {
        Assertions.assertThat(actual)
                .as("Password placeholder text")
                .isEqualTo(expected);
    }
}
