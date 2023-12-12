package lib.ui.ios;

import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUi {

    static {
        NAVIGATE_TO_MAIN_PAGE_BUTTON = "xpath://XCUIElementTypeStaticText[`label == 'Cancel'`]";
        SAVED_LISTS_BUTTON = "id:Saved";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}