package tests;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.qameta.allure.junit4.Tags;
import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for managing saved articles lists")
public class MyListsTests extends CoreTestCase
{
    private static final String
    login = "Wikifortestsauto",
    password = "Wikifortestsauto1";

    @Test
    @Features(value = {@Feature(value = "Saving articles")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Save one article to the list")
    @Description("Open Wiki; Enter valid query and perform search; Open first article; Click Save article button; Return to search result page; " +
            "Return to main page; Open Saved lists; Check article is present in Saved list")
    @Step("Starting testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        String name_of_folder = "Test_list";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLines("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            articlePageObject.waitArticleAddMW();

            articlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    articlePageObject.getArticleTitle()
            );


        }
        if (Platform.getInstance().isAndroid()) {
        articlePageObject.closeArticle();

        NavigationUi navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyList();
        }
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);


        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title);
        }
    }

    @Test
    @Features(value = {@Feature(value = "Saving articles")})
    @Tags(value = {@Tag(value = "Regression")})
    @DisplayName("Save two articles, delete one article")
    @Description("Open Wiki; Enter valid query and perform search; Open first article; Click Save article button; Return to search result page; " +
            "Open second article; Click Save article button; Return to search result page" +
            "Return to main page; Open Saved lists; Check two articles are present in Saved list" +
            "Delete 1st article from Saved list; Check 1st article is not present in Saved list" +
            "Check 2nd article still present in Saved list")
    @Step("testSaveTwoArticlesDeleteOneArticle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticleAndDeleteOne() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_query = "meme";
        SearchPageObject.typeSearchLines(search_query);
        String article_title_1 = "nto (film)";
        String name_of_folder = "Test_list";
        SearchPageObject.clickByArticleWithTitle(article_title_1);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);;
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else  {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            //wait for redirect from authorization form
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", "Meme" + article_title_1, ArticlePageObject.getArticleTitle());
        }
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLines(search_query);
        }

        String article_title_2 = "nto mori";
        SearchPageObject.clickByArticleWithTitle(article_title_2);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyExistingList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }


        NavigationUi NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            NavigationUI.returnFromSearchResultsToMainPage();
        } else {
            NavigationUI.openNavigation();
        }

        NavigationUI.clickSavedLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeSyncOverlay();
        }
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_1);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_2);
        if (driver instanceof AppiumDriver) {
            MyListsPageObject.swipeByArticleToDelete(article_title_1);
        } else {
            MyListsPageObject.deleteArticleFromList(article_title_1);
            MyListsPageObject.refreshPage();
        }

        MyListsPageObject.waitForArticleToDisappearByTitle(article_title_1);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_2);
    }

}
