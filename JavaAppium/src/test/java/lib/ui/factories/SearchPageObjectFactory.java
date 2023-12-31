package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSSearchPageObject;
import lib.ui.mobileweb.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject((AppiumDriver) driver);
        } else if (Platform.getInstance().isIOS()){
            return new IOSSearchPageObject((AppiumDriver) driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }

}