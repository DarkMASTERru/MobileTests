package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        CLOSE_SYNC_OVERLAY_BUTTON = "id:Close";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}