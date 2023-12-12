package lib.ui.mobileweb;

import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUi {
    static {
        MY_LIST_LINK = "css:a[data-event-name='menu.unStar']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        SAVED_LISTS_BUTTON = "xpath://*[text() = 'Watchlist']";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}