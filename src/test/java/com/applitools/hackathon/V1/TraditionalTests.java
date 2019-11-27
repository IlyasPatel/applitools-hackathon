package com.applitools.hackathon.V1;

import com.applitools.hackathon.utils.DriverServiceExecutable;
import com.applitools.hackathon.assertions.LoginFormAssert;
import com.applitools.hackathon.assertions.LoginFormIconAssert;
import com.applitools.hackathon.assertions.RecentTransactionsAssert;
import com.applitools.hackathon.domain.TransactionsTable;
import com.applitools.hackathon.steps.ApplicationSteps;
import com.applitools.hackathon.steps.LoggedInSteps;
import com.applitools.hackathon.steps.LoginPageSteps;
import com.applitools.hackathon.utils.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TraditionalTests {

    private static final String URL_V1 = "https://demo.applitools.com/hackathon.html";
    private static final String URL_V1_WITH_ADS = "https://demo.applitools.com/hackathon.html?showAd=true";

    private WebDriver webDriver;

    private LoginPageSteps loginPageSteps;
    private LoggedInSteps loggedInSteps;
    private ApplicationSteps applicationSteps;

    private StringUtils stringUtils;

    @BeforeAll
    static void beforeAll() {
        File chromedriver = DriverServiceExecutable.called("chromedriver").asAFile();
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
    }

    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();

        loginPageSteps = new LoginPageSteps(webDriver);
        loggedInSteps = new LoggedInSteps(webDriver);
        applicationSteps = new ApplicationSteps(webDriver);

        stringUtils = new StringUtils();
    }

    @AfterEach
    void tearDown() {
        webDriver.close();
    }


    @Test
    @DisplayName("Scenario One")
	void shouldConfirmUserInterfaceElementsAreCorrect_when_onLoginPage() {

        // Given
        loginPageSteps.openLoginForm(URL_V1);

        // When
        String actualNameOfForm = loginPageSteps.getNameOfForm();

        String actualUsernameLabel = loginPageSteps.getUsernameLabel();
        String actualUsernameInputField = loginPageSteps.getUsernameInputField();
        String actualUsernameInputType = loginPageSteps.getUsernameInputFieldType();
        String actualUsernamePlaceholderText = loginPageSteps.getUsernamePlaceholderText();

        String actualPasswordLabel = loginPageSteps.getPasswordLabel();
        String actualPasswordInputField = loginPageSteps.getPasswordInputField();
        String actualPasswordInputType = loginPageSteps.getPasswordInputFieldType();
        String actualPasswordPlaceholderText = loginPageSteps.getPasswordPlaceholderText();

        String actualLoginButtonText = loginPageSteps.getLoginButtonText();

        String actualRememberMeLabel = loginPageSteps.getRememberMeLabel();
        String actualRememberMeFieldType = loginPageSteps.getRememberMeFieldType();

        boolean actualUsernameIcon = loginPageSteps.getUsernameIcon();
        boolean actualPasswordIcon = loginPageSteps.getPasswordIcon();

        boolean actualTwitterImageDisplayed = loginPageSteps.isTwitterIconDisplayed();
        boolean actualFacebookImageDisplayed = loginPageSteps.isFacebookIconDisplayed();
        boolean actualLinkedInImageDisplayed = loginPageSteps.isLinkedInIconDisplayed();

        // Then
        assertAll(
            () -> LoginFormAssert.assertThat(actualNameOfForm).loginFormHeadingIs("Login Form"),

            () -> LoginFormAssert.assertThat(actualUsernameLabel).usernameLabelIs("Username"),
            () -> LoginFormAssert.assertThat(actualUsernameInputField).usernameInputFieldIsEmpty(),
            () -> LoginFormAssert.assertThat(actualUsernameInputType).usernameFieldIsTextField(),
            () -> LoginFormAssert.assertThat(actualUsernamePlaceholderText).usernamePlaceholderTextIs("Enter your username"),

            () -> LoginFormAssert.assertThat(actualPasswordLabel).passwordLabelIs("Password"),
            () -> LoginFormAssert.assertThat(actualPasswordInputField).passwordInputFieldIsEmpty(),
            () -> LoginFormAssert.assertThat(actualPasswordInputType).passwordFieldIsTextField(),
            () -> LoginFormAssert.assertThat(actualPasswordPlaceholderText).passwordPlaceholderTextIs("Enter your password"),

            () -> LoginFormAssert.assertThat(actualLoginButtonText).loginButtonSays("Log In"),
            () -> LoginFormAssert.assertThat(actualRememberMeLabel).rememberMeTextIs("Remember Me"),
            () -> LoginFormAssert.assertThat(actualRememberMeFieldType).rememberMeFieldIsCheckbox(),


            () -> LoginFormIconAssert.assertThat(actualUsernameIcon).usernameIconIsDisplayed(),
            () -> LoginFormIconAssert.assertThat(actualPasswordIcon).passwordIconIsDisplayed(),
            () -> LoginFormIconAssert.assertThat(actualTwitterImageDisplayed).twitterImageIsDisplayed(),
            () -> LoginFormIconAssert.assertThat(actualFacebookImageDisplayed).facebookImageIsDisplayed(),
            () -> LoginFormIconAssert.assertThat(actualLinkedInImageDisplayed).linkedInImageIsDisplayed()
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "emptyString : emptyString : Both Username and Password must be present",
            "emptyString : g0mez       : Username must be present",
            "Jack        : emptyString : Password must be present"
    }, delimiter = ':')
    @DisplayName("Scenario Two - Failure outcome")
    void shouldConfirmAnErrorIsDisplayedToTheCustomer_when_credentialsAreNotEnteredCorrectly(
            String username, String password, String expected) {

        // Given
        String usernameInput = stringUtils.makeStringEmpty(username);
        String passwordInput = stringUtils.makeStringEmpty(password);

        loginPageSteps.openLoginForm(URL_V1);

        // When
        loginPageSteps.submitCredentials(usernameInput, passwordInput);

        // Then
        String actual = loginPageSteps.getErrorMessageDisplayedToUser();

        Assertions.assertThat(actual)
                  .as("Providing invalid combinations to login page")
                  .isEqualTo(expected);
    }

    @Test
    @DisplayName("Scenario Two - Success outcome")
    void shouldConfirmCustomerCanLogin_when_correctCredentialsAreEntered() {

        // Given
        loginPageSteps.openLoginForm(URL_V1);

        // When
        loginPageSteps.submitCredentials("Jack", "g0mez");
        loggedInSteps.waitForCustomerNameToBeDisplayed();

        // Then
        String actual = loggedInSteps.getCustomerName();
        String expected = "Jack Gomez";

        Assertions.assertThat(actual)
                .as("Users name after successful login")
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Scenario Three - Transaction Amounts in ascending order")
    void shouldConfirmTheTransactionAmountsAreInAscendingOrder_when_selectingTheAmountsHeaderFromRecentTransactions() {

        // Given
        loginPageSteps.openLoginForm(URL_V1);
        loginPageSteps.submitCredentials("Jack", "g0mez");
        loggedInSteps.waitForCustomerNameToBeDisplayed();

        // When
        List<TransactionsTable> actual = applicationSteps.arrangeAndGetRecentTransactionsByAmount();

        RecentTransactionsAssert.assertThat(actual).amountsColumnIsInAscendingOrder();
    }

    @Test
    @DisplayName("Scenario Three - Table data in order after ordering by amounts")
    void shouldConfirmTheTableDataIsIntact_when_selectingTheAmountsHeaderFromRecentTransactions() {

        // Given
        loginPageSteps.openLoginForm(URL_V1);
        loginPageSteps.submitCredentials("Jack", "g0mez");
        loggedInSteps.waitForCustomerNameToBeDisplayed();

        List<TransactionsTable> originalRecentTransactions = applicationSteps.getRecentTransactions();

        // When
        List<TransactionsTable> arrangedTransactions = applicationSteps.arrangeAndGetRecentTransactionsByAmount();

        RecentTransactionsAssert.assertThat(arrangedTransactions).isIntact(originalRecentTransactions);
    }

    @Test
    @DisplayName("Scenario Four")
    void shouldConfirmTheChartDataFor2019IsAdded_when_selectingShowDataForNextYear() {
        /*
            Unable to automate this as it is not possible to access the Canvas data.

            Searching for answers, seems like visual validation is the only way without access to the backend APIs.
         */
    }

    @Test
    @DisplayName("Scenario Five")
    void shouldConfirmTwoAdsAreDisplayed_when_loggedIn() {

        // When
        loginPageSteps.openLoginFormWithAds(URL_V1_WITH_ADS);
        loginPageSteps.submitCredentials("anyUsername", "anyPassword");

        // Then
        boolean isAdOneDisplayed = applicationSteps.isAdOneDisplayed();
        boolean isAdTwoDisplayed = applicationSteps.isAdTwoDisplayed();

        assertAll(
                () -> Assertions.assertThat(isAdOneDisplayed).as("Ad One is not displayed").isTrue(),
                () -> Assertions.assertThat(isAdTwoDisplayed).as("Ad Two is not displayed").isTrue()
        );
    }


}
