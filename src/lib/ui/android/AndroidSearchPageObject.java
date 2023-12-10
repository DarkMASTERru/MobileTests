package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']";
        SEARCH_TITLE_DESCRIPTION_ELEMENT = "xpath://*[android.widget.TextView[@index=0 and @text='{TITLE}'] and android.widget.TextView[@index=1 and @text='{DESCRIPTION}']]";
        SEARCH_ARTICLES_RETURNED_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
    }


    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}