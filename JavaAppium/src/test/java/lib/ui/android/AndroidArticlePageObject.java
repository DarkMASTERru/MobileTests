package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']";
        FOOTER_ELEMENT = "xpath://android.widget.TextView[@text='View article in browser']";
        PAGE_SAVE = "id:org.wikipedia:id/page_save";
        SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc = 'Navigate up']";
        DESCRIPTION = "xpath://*[@resource-id='pcs-edit-section-title-description']";
        SAVE_TO_MY_LIST_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/article_menu_bookmark']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super((AppiumDriver) driver);
    }

}