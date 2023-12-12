package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.ios.IOSWelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import lib.ui.android.AndroidWelcomePageObj;

public class WelcomePageObjectFactory {
    public static WelcomePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWelcomePageObj((AppiumDriver) driver);
        } else {
            return new IOSWelcomePageObject((AppiumDriver) driver);
        }
    }
}