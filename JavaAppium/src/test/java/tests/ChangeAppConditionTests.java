package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests mobile app conditions")
public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "App Conditions")})
    @Tags(value = {@Tag(value = "Acceptance")})
    @DisplayName("Change orientation and return to original state")
    @Description("Open Wiki page; Perform search; Open article from search result; Get article title; Rotate app to lanscape; " +
            "Check article title hasn't changed; Rotate app to portrait; Check article title hasn't changed;")
    @Severity(value = SeverityLevel.CRITICAL)
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

        Assert.assertEquals(
                "Article title have been changed after screem rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screem rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value = "App Conditions")})
    @Tags(value = {@Tag(value = "Acceptance")})
    @DisplayName("Send app to background and return")
    @Description("Open Wiki page; Perform search; Open article from search result; Get article title; Send app to background and return; " +
            "Check article title hasn't changed")
    @Severity(value = SeverityLevel.CRITICAL)
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
