package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Tags(value = {@Tag(value = "Acceptance"), @Tag(value = "Regression")})
    @DisplayName("Compare article title with expected value")
    @Description("We open 'Object-oriented programming language' article and make sure the title is expected")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

//        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        String article_title = ArticlePageObject.getArticleTitle();

        MainPageObject.assertElementHasText(
                ArticlePageObject.descriptionOfArticle(),
                article_title,
                "Take another text");
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer")
    @Step("Starting testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Appium");

        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Tags(value = { @Tag(value = "Regression")})
    @DisplayName("Asserting article has title with expected value")
    @Description("Open Wiki; Check search input; Enter search query and perform search; Open the article from the search result page. Check article has title with expected value.")
    @Step("Starting testArticleHasTitleElement")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAssertTittle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String article_title = ArticlePageObject.getArticleTitle();

        MainPageObject.assertElementHasText(
                ArticlePageObject.descriptionOfArticle(),
                article_title,
                "Take another text");
    }

}
