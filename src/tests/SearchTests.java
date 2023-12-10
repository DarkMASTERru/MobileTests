package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase
{
    private lib.ui.MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.waitForSearchREsult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");

        SearchPageObject.clearSearchArea();

        SearchPageObject.typeSearchLines("Java");

        SearchPageObject.pressClearAreaButton();
    }
    @Test
    public void testAssertElementHasText() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        MainPageObject.assertElementHasText(
                SearchPageObject.returnSearchFieldTitle(),
                "Search Wikipedia",
                "Can not find search field"
        );
    }

    @Test
    public void testCancelSearchWithResults_Ex3_Ex4() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");


        SearchPageObject.waitForSearchREsult("Object-oriented programming language");

        assertTrue("Not find search pages",
                MainPageObject.quantityElements("xpath//*[contains(@text, 'Java')]").size() > 1);

        SearchPageObject.pressClearAreaButton();

        SearchPageObject.articleNotPresent("Java");

        assertTrue("Results are present",
                MainPageObject.quantityElements("xpath//*[contains(@text, 'Java')]").size() == 0);

    }

    @Test
    public void testSearchBy2Substrings() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.assertTheSearchArticleResultMoreThen(3);
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }
}
