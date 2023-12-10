package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_TITLE_DESCRIPTION_ELEMENT,
            SEARCH_ARTICLES_RETURNED_ELEMENT,
            SEARCH_CANCEL_BUTTON;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METODS */
    private static String getResultSearcgElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementBy (String title, String description) {

        return SEARCH_TITLE_DESCRIPTION_ELEMENT.replace("{TITLE}", title).replace( "{DESCRIPTION}", description);
    }
    /* TEMPLATES METODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void clickSkipButton()
    {
        this.waitForElementAndClick(
                "id:org.wikipedia:id/fragment_onboarding_skip_button",
                "Cannot find skip button",
                5);
    }

    public void typeSearchLines(String search_lines)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_lines, "Cannot find and type into search input", 5);
    }

    public void waitForSearchREsult(String substring)
    {
        String search_result_xpath = getResultSearcgElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearcgElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void clearSearchArea()
    {
        this.waitForElementAndClear(
                "id:org.wikipedia:id/search_src_text",
                "Cannot find search field",
                5
        );
    }

    public void pressClearAreaButton()
    {
        this.waitForElementAndClick(
                "id:org.wikipedia:id/search_close_btn",
                "Cannot find X to cancel search",
                5
        );

        this.waitForElementNotPresent(
                "id:org.wikipedia:id/search_close_btn",
                "X is still present on page",
                5
        );
    }

    public WebElement returnSearchFieldTitle()
    {
        WebElement search_element = this.waitForElementPresent(
                "xpath://*[@resource-id = 'org.wikipedia:id/search_container']//*[@class = 'android.widget.TextView']",
                "Cannot find search field",
                15
        );
        return search_element;
    }
    public void articleNotPresent(String article_name)
    {
        this.waitForElementNotPresent(
                "id://*[contains(@text, '" + article_name + "')]",
                article_name + " pages is still present on page",
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_title_desc_xpath = getResultSearchElementBy(title, description);
        this.waitForElementPresent(
                search_title_desc_xpath,
                "Cannot find and click searched result with title " + title + "and description  " + description,
                15);
    }
    public void assertTheSearchArticleResultMoreThen(int count) {

        int actual_number_of_articles_returned = this.getAmmountOfElements(SEARCH_ARTICLES_RETURNED_ELEMENT);
        Assert.assertTrue("less than " + count + " articles found",actual_number_of_articles_returned >= count == true);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5);
    }
}
