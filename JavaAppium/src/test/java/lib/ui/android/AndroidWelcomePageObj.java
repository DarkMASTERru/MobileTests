package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWelcomePageObj extends WelcomePageObject {
    static {
        SKIP_BUTTON = "xpath://*[contains(@text,'Skip')]";
    }
    public AndroidWelcomePageObj(RemoteWebDriver driver){
        super((AppiumDriver) driver);
    }
}