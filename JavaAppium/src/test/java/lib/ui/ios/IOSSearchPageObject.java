package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
//        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCollectionView//XCUIElementTypeCell";
//        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
//        SEARCH_FIELD_PLACEHOLDER = "id:Search Wikipedia";
//        SEARCH_RESULT_TITLE = "xpath://XCUIElementTypeCell//XCUIElementTypeStaticText[1]";
//        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCell[.//XCUIElementTypeStaticText[1][@value='{TITLE}']][.//XCUIElementTypeStaticText[2][@value = '{DESC}']]";
        SEARCH_RESULT_TITLE_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')][@index = '0' or @index = '1']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}