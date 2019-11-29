package com.applitools.hackathon.V2;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.hackathon.steps.ApplicationSteps;
import com.applitools.hackathon.steps.LoginPageSteps;
import com.applitools.hackathon.utils.DriverServiceExecutable;
import com.applitools.hackathon.utils.StringUtils;
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

public class V2_VisualAITests {

    private static final String URL_V2 = "https://demo.applitools.com/hackathonV2.html";
    private static final String URL_V2_WITH_ADS = "https://demo.applitools.com/hackathonV2.html?showAd=true";
    private static final String APPLITOOLS_API_KEY = "zDUulJg6ZRGIOihhTg5P102YhFicTH110aYiyvYUHiodD84110";

    private static final String APPLITOOLS_APPLICATION_NAME = "Hackathon App";
    private static final RectangleSize APPLITOOLS_VIEW_PORT_SIZE = new RectangleSize(1200, 830);

    private static final String VALID_USERNAME = "anyUsername";
    private static final String VALID_PASSWORD = "anyPassword";

    private EyesRunner runner;
    private Eyes eyes;

    private WebDriver webDriver;

    private LoginPageSteps loginPageSteps;
    private ApplicationSteps applicationSteps;

    private StringUtils stringUtils;

    private static BatchInfo batchHackathon;

    @BeforeAll
    static void beforeAll() {
        File chromedriver = DriverServiceExecutable.called("chromedriver").asAFile();
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());

        batchHackathon = new BatchInfo("Hackathon");
    }

    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();

        loginPageSteps = new LoginPageSteps(webDriver);
        applicationSteps = new ApplicationSteps(webDriver);

        stringUtils = new StringUtils();

        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(APPLITOOLS_API_KEY);
        eyes.setBatch(batchHackathon);
    }

    @AfterEach
    void tearDown() {
        webDriver.close();

        eyes.closeAsync();
        eyes.abortIfNotClosed();
    }


    @Test
    @DisplayName("Scenario One")
    void shouldConfirmUserInterfaceElementsAreCorrect_when_onLoginPage() {

        // Given
        eyes.open(webDriver, APPLITOOLS_APPLICATION_NAME, "Scenario One - Login Page", APPLITOOLS_VIEW_PORT_SIZE);

        // When
        loginPageSteps.openLoginForm(URL_V2);

        // Then
        eyes.checkWindow("Login Page");
    }


    @ParameterizedTest
    @CsvSource(value = {
            "emptyString : emptyString : Scenario Two - Both Username and Password must be present",
            "emptyString : g0mez       : Scenario Two - Username must be present",
            "Jack        : emptyString : Scenario Two - Password must be present",
            "Jack        : g0mez       : Scenario Two - Successful Login"
    }, delimiter = ':')
    @DisplayName("Scenario Two")
    void shouldConfirmAnErrorIsDisplayedToTheCustomer_when_credentialsAreNotEnteredCorrectly(
            String username, String password, String testName) {

        // Given
        eyes.open(webDriver, APPLITOOLS_APPLICATION_NAME, testName, APPLITOOLS_VIEW_PORT_SIZE);

        String usernameInput = stringUtils.makeStringEmpty(username);
        String passwordInput = stringUtils.makeStringEmpty(password);

        // When
        loginPageSteps.openLoginForm(URL_V2);
        loginPageSteps.submitCredentials(usernameInput, passwordInput);

        // Then
        eyes.checkWindow(testName);
    }


    @Test
    @DisplayName("Scenario Three - Transaction Amounts in ascending order")
    void shouldConfirmTheTransactionAmountsAreInAscendingOrder_when_selectingTheAmountsHeaderFromRecentTransactions() {

        // Given
        eyes.setForceFullPageScreenshot(true);
        eyes.open(webDriver, APPLITOOLS_APPLICATION_NAME, "Scenario Three - Transaction amounts in ascending order", APPLITOOLS_VIEW_PORT_SIZE);

        loginPageSteps.login(URL_V2, VALID_USERNAME, VALID_PASSWORD);

        // When
        applicationSteps.arrangeAndGetRecentTransactionsByAmount();

        // Then
        eyes.checkWindow("Recent Transactions Table");
    }


    @Test
    @DisplayName("Scenario Four - Compare chart data")
    void shouldConfirmTheChartDataIsVisuallyCorrect_when_comparingChartData() {
        /*
            Ideally I would like to wait for HTML Canvas to finish loading which doesn't seem to be possible.

            eyes.setWaitBeforeScreenshots(); // I could use this but seems to work fine without for now.
         */

        // Given
        eyes.open(webDriver, APPLITOOLS_APPLICATION_NAME, "Scenario Four - Compare chart data", APPLITOOLS_VIEW_PORT_SIZE);

        loginPageSteps.login(URL_V2, VALID_USERNAME, VALID_PASSWORD);

        applicationSteps.selectCompareExpenses();

        // Then
        eyes.checkWindow("Expenses and Forecasts Comparison");
    }


    @Test
    @DisplayName("Scenario Four - Compare chart data when adding data for next year")
    void shouldConfirmTheChartDataFor2019IsAdded_when_selectingShowDataForNextYear() {

        // Given
        eyes.open(webDriver, APPLITOOLS_APPLICATION_NAME, "Scenario Four - Compare chart data when adding data for next year", APPLITOOLS_VIEW_PORT_SIZE);

        loginPageSteps.login(URL_V2, VALID_USERNAME, VALID_PASSWORD);

        applicationSteps.selectCompareExpenses();

        // Then
        applicationSteps.showDataForNextYear();

        eyes.checkWindow("Expenses and Forecasts Comparison For Next Year");
    }


    @Test
    @DisplayName("Scenario Five")
    void shouldConfirmTwoAdsAreDisplayed_when_loggedIn() {

        // Given
        eyes.open(webDriver, APPLITOOLS_APPLICATION_NAME, "Scenario Five - Dynamic content", APPLITOOLS_VIEW_PORT_SIZE);

        // When
        loginPageSteps.openLoginFormWithAds(URL_V2_WITH_ADS);
        loginPageSteps.submitCredentials(VALID_USERNAME, VALID_PASSWORD);

        // Then
        eyes.checkWindow("Dynamic content");
    }
}