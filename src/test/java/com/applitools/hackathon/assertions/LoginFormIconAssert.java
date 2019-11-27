package com.applitools.hackathon.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class LoginFormIconAssert extends AbstractAssert<LoginFormIconAssert, Boolean> {

    public LoginFormIconAssert(Boolean actual) {
        super(actual, LoginFormIconAssert.class);
    }

    public static LoginFormIconAssert assertThat(Boolean actual) {
        return new LoginFormIconAssert(actual);
    }

    public void usernameIconIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Username Icon 'IsDisplayed()'")
                .isEqualTo(true);
    }

    public void passwordIconIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Password Icon 'IsDisplayed()'")
                .isEqualTo(true);
    }

    public void twitterImageIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Twitter Icon 'IsDisplayed()'")
                .isEqualTo(true);
    }

    public void facebookImageIsDisplayed() {
        Assertions.assertThat(actual)
                .as("Facebook Icon 'IsDisplayed()'")
                .isEqualTo(true);
    }

    public void linkedInImageIsDisplayed() {
        Assertions.assertThat(actual)
                .as("LinkedIn Icon 'IsDisplayed()'")
                .isEqualTo(true);
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
