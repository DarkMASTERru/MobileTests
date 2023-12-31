package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    protected static String
            STEP_LEARN_MORE_LINK,
            STEP_NEW_WAYS_TO_EXPLORE_STEPS,
            STEP_SEARCH_IN_NEARLY_300_LANGUAGES,
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
            NEXT_LINK,
            GET_STARTED_BUTTON,
            SKIP_BUTTON;

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Checking link MoreLearn is present ")
    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10);
    }

    @Step("Clicking Next button")
    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK,
                "Cannot find 'Next' button",
                10);
    }

    @Step("Checking for new way to explore text")
    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_STEPS,
                "Cannot find 'New ways to explore' text",
                10);
    }

    @Step("Checking for language text")
    public void waitForLanguageText() {
        this.waitForElementPresent(STEP_SEARCH_IN_NEARLY_300_LANGUAGES,
                "Cannot find 'Search in nearly 300 languages' text",
                10);
    }

    @Step("Checking link LearnMoreAboutDataCollectedText is present")
    public void waitForLearnMoreAboutDataCollectedLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Cannot find 'Learn more about data collected' link",
                10);
    }

    @Step("Clicking Get Started button")
    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find 'Get started' button",
                10);
    }

    @Step("Clicking Skip button")
    public void clickSkip() {
        this.waitForElementAndClick(SKIP_BUTTON,
                "Cannot find Skip button",
                10);
    }
}