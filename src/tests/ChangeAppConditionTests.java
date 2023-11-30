package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeOrientationOnSearchResult()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screem rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screem rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testChechSearchArticleBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.waitForSearchREsult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchREsult("Object-oriented programming language");
    }
}
