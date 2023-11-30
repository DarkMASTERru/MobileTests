package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.waitForSearchREsult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");

        SearchPageObject.clearSearchArea();

        SearchPageObject.typeSearchLines("Java");

        SearchPageObject.pressClearAreaButton();
    }
    @Test
    public void testAssertElementHasText() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        MainPageObject.assertElementHasText(
                SearchPageObject.returnSearchFieldTitle(),
                "Search Wikipedia",
                "Can not find search field"
        );
    }

    @Test
    public void testCancelSearchWithResults_Ex3_Ex4() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");


        SearchPageObject.waitForSearchREsult("Object-oriented programming language");

        assertTrue("Not find search pages",
                MainPageObject.quantityElements(By.xpath("//*[contains(@text, 'Java')]")).size() > 1);

        SearchPageObject.pressClearAreaButton();

        SearchPageObject.articleNotPresent("Java");

        assertTrue("Results are present",
                MainPageObject.quantityElements(By.xpath("//*[contains(@text, 'Java')]")).size() == 0);

    }

    @Test
    public void testSearchBy2Substrings() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.assertTheSearchArticleResultMoreThen(3);
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }
}
