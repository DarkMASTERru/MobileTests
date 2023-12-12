package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.ArticlePageObjectFactory;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeOrientationOnSearchResult()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screem rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

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
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.waitForSearchREsult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchREsult("Object-oriented programming language");
    }
}
