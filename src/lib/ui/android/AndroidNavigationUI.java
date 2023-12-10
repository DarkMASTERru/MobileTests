package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class AndroidNavigationUI extends NavigationUi {

    static {
        PAGE_SAVE = "id:org.wikipedia:id/page_save";
        SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action";
        BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        REMOVE_FROM_ELEMENT = "xpath://*[contains(@text, 'Remove from')]";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }

}