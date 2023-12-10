package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://android.widget.TextView[@text='View article in browser']";
        PAGE_SAVE = "id:org.wikipedia:id/page_save";
        SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc = 'Navigate up']";
        DESCRIPTION = "xpath://*[@resource-id='pcs-edit-section-title-description']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}