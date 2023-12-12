package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.ArticlePageObjectFactory;

public class ArticleTests extends CoreTestCase
{
    private lib.ui.MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }
    @Test
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

//        assertEquals(
//                "We see unexpected title",
//                "Java (programming language)",
//                article_title
//        );
    }

    @Test
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
