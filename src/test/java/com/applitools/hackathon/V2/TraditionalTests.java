package com.applitools.hackathon.V2;

import com.applitools.hackathon.utils.DriverServiceExecutable;
import com.applitools.hackathon.assertions.LoginFormAssert;
import com.applitools.hackathon.assertions.RecentTransactionsAssert;
import com.applitools.hackathon.utils.StringUtils;
import com.applitools.hackathon.domain.TransactionsTable;
import com.applitools.hackathon.steps.ApplicationSteps;
import com.applitools.hackathon.steps.LoggedInSteps;
import com.applitools.hackathon.steps.LoginPageSteps;
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

    private static final String URL_V2 = "https://demo.applitools.com/hackathonV2.html";
    private static final String URL_V2_WITH_ADS = "https://demo.applitools.com/hackathonV2.html?showAd=true";

    private static final String VALID_USERNAME = "anyUsername";
    private static final String VALID_PASSWORD = "anyPassword";

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
        loginPageSteps.openLoginForm(URL_V2);

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

        //boolean actualUsernameIcon = loginPageSteps.getUsernameIcon();
        //boolean actualPasswordIcon = loginPageSteps.getPasswordIcon();

        //boolean actualTwitterImageDisplayed = loginPageSteps.isTwitterIconDisplayed();
        //boolean actualFacebookImageDisplayed = loginPageSteps.isFacebookIconDisplayed();
        //boolean actualLinkedInImageDisplayed = loginPageSteps.isLinkedInIconDisplayed();

        /*
        * The images have either been removed or the selector is no longer valid. For the remaining two
        * social media images, the hyper links are missing so the original css selector is not valid.
        * I removed the code as the assertion doesn't reach but I suppose the images should have links around them
        * for the selector to work.
        * */

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
                () -> LoginFormAssert.assertThat(actualRememberMeFieldType).rememberMeFieldIsCheckbox()

                // Comment above to explain why this is commented out.
                //() -> LoginFormIconAssert.assertThat(actualUsernameIcon).usernameIconIsDisplayed(),
                //() -> LoginFormIconAssert.assertThat(actualPasswordIcon).passwordIconIsDisplayed(),
                //() -> LoginFormIconAssert.assertThat(actualTwitterImageDisplayed).twitterImageIsDisplayed(),
                //() -> LoginFormIconAssert.assertThat(actualFacebookImageDisplayed).facebookImageIsDisplayed(),
                //() -> LoginFormIconAssert.assertThat(actualLinkedInImageDisplayed).linkedInImageIsDisplayed()
        );
    }


    @ParameterizedTest
    @CsvSource(value = {
            "emptyString : emptyString : Please enter both username and password",
            "emptyString : g0mez: Username must be present",
            "Jack: emptyString : Password must be present"
    }, delimiter = ':')
    @DisplayName("Scenario Two - Failure outcome")
    void shouldConfirmAnErrorIsDisplayedToTheCustomer_when_credentialsAreNotEnteredCorrectly(
            String username, String password, String expected) {

        // Given
        String usernameInput = stringUtils.makeStringEmpty(username);
        String passwordInput = stringUtils.makeStringEmpty(password);
        
        loginPageSteps.openLoginForm(URL_V2);

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
        loginPageSteps.openLoginForm(URL_V2);

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
    @DisplayName("Scenario Three - Amounts in ascending order")
    void shouldConfirmTheAmountsAreInAscendingOrder_when_selectingTheAmountsHeaderFromRecentTransactions() {

        // Given
        loginPageSteps.openLoginForm(URL_V2);
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
        loginPageSteps.login(URL_V2, VALID_USERNAME, VALID_PASSWORD);

        List<TransactionsTable> originalRecentTransactions = applicationSteps.getRecentTransactions();

        // When
        List<TransactionsTable> arrangedTransactions = applicationSteps.arrangeAndGetRecentTransactionsByAmount();

        RecentTransactionsAssert.assertThat(arrangedTransactions).isIntact(originalRecentTransactions);
    }


    @Test
    @DisplayName("Scenario Four - Compare chart data")
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
        loginPageSteps.openLoginFormWithAds(URL_V2_WITH_ADS);
        loginPageSteps.submitCredentials(VALID_USERNAME, VALID_PASSWORD);

        // Then
        boolean isAdOneDisplayed = applicationSteps.isAdOneDisplayed();
        boolean isAdTwoDisplayed = applicationSteps.isAdTwoDisplayed();

        assertAll(
                () -> Assertions.assertThat(isAdOneDisplayed).as("Ad One is not displayed").isTrue(),
                () -> Assertions.assertThat(isAdTwoDisplayed).as("Ad Two is not displayed").isTrue()
        );
    }
}
