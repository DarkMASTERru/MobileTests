package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search controls"), @Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Check the search results has one result with expected description")
    @Description("Open Wiki; Enter search query in the search field; Check that one search result has expected description")
    @Step("Starting testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

//        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.waitForSearchREsult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search controls")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Check the cancel search button states")
    @Description("Open Wiki; Enter search query in the search field; Check that cancel search button is present; Click cancel button; Check that cancel button is not present")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");

        SearchPageObject.clearSearchArea();

        SearchPageObject.typeSearchLines("Java");

        SearchPageObject.pressClearAreaButton();
    }
    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search controls")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Check result search")
    @Description("Open Wiki; Enter search query in the search field; Check that find result is right")
    @Step("Starting testAssertElementHasText")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAssertElementHasText() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        MainPageObject.assertElementHasText(
                SearchPageObject.returnSearchFieldTitle(),
                "Search Wikipedia",
                "Can not find search field"
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Check that invalid search query has no results")
    @Description("Open Wiki; Enter invalid search query in the search field; Check that search results page has 'empty results' label")
    @Step("Starting testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelSearchWithResults_Ex3_Ex4() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");


        SearchPageObject.waitForSearchREsult("bject-oriented programming language");

        Assert.assertTrue("Not find search pages",
                MainPageObject.quantityElements("xpath//*[contains(@text, 'Java')]").size() > 1);

        SearchPageObject.pressClearAreaButton();

        SearchPageObject.articleNotPresent("Java");

        Assert.assertTrue("Results are present",
                MainPageObject.quantityElements("xpath//*[contains(@text, 'Java')]").size() == 0);

    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @Stories(value = {@Story(value = "Search results")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Check that search result more than 3")
    @Description("Open Wiki; Enter invalid search query in the search field; Check that search results more than 3")
    @Step("Starting testSearchBy2Substrings")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearchBy2Substrings() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.assertTheSearchArticleResultMoreThen(3);
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "bject-oriented programming language");
    }
}
