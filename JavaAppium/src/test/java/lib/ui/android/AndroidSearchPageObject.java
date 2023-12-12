package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='pcs-edit-section-title-description'][contains(@text,'{SUBSTRING}')]')]";
        SEARCH_TITLE_DESCRIPTION_ELEMENT = "xpath://*[android.widget.TextView[@index=0 and @text='{TITLE}'] and android.widget.TextView[@index=1 and @text='{DESCRIPTION}']]";
        SEARCH_ARTICLES_RETURNED_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULT_TITLE_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
    }


    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}