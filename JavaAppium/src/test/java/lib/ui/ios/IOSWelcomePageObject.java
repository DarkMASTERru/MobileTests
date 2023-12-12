package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSWelcomePageObject extends WelcomePageObject {
    static {
        STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[`label == 'Learn more about Wikipedia'`]";
//        STEP_NEW_WAYS_TO_EXPLORE = "xpath://XCUIElementTypeStaticText[`label == 'New ways to explore'`]";
//        STEP_ADD_AND_EDIT_PREF_LANG = "xpath://XCUIElementTypeStaticText[`label == 'Add or edit preferred languages'`]";
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "xpath://XCUIElementTypeStaticText[`label == 'Learn more about data collected'`]";
//        NEXT_BUTTON = "xpath://XCUIElementTypeStaticText[`label == 'Next'`]";
        GET_STARTED_BUTTON = "xpath://XCUIElementTypeStaticText[`label == 'Get started'`]";
        SKIP_BUTTON = "xpath://XCUIElementTypeButton[`label == 'Skip'`]";
    }

    public IOSWelcomePageObject(RemoteWebDriver driver){
        super((AppiumDriver) driver);
    }
}