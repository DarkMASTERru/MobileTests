package lib.ui.factories;

import lib.Platform;
import lib.ui.NavigationUi;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.IOSNavigationUI;
import lib.ui.mobileweb.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {

    public static NavigationUi get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isAndroid()) {
            return new IOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}