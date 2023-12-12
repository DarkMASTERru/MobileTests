package lib.ui.android;

import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUi {

    static {
        PAGE_SAVE = "id:org.wikipedia:id/page_save";
        SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action";
        BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        REMOVE_FROM_ELEMENT = "xpath://*[contains(@text, 'Remove from')]";
        NAVIGATE_TO_MAIN_PAGE_BUTTON = "xpath://*[@resource-id = 'org.wikipedia:id/search_toolbar']//*[@class = 'android.widget.ImageButton']";
        SAVED_LISTS_BUTTON = "xpath://*[contains(@content-desc, 'Saved')]";
    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}