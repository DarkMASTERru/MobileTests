package tests;

import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String
    login = "Wikifortestsauto",
    password = "Wikifortestsauto1";

    @Test
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
            assertEquals("We are not on the same page after login",
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
    public void testSaveTwoArticleAndDeleteOne() {
        String searchWord = "Java";
        String name_of_folder = "Test_list";
        String firstArticleTitle = "bject-oriented programming language";
        String secondArticleTitle = "Island in Indonesia";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines(searchWord);
        SearchPageObject.clickByArticleWithSubstring(firstArticleTitle);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleElement(firstArticleTitle);
            ArticlePageObject.addArticleToMySaved();
        }

        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.backButton();
        SearchPageObject.clickByArticleWithSubstring(secondArticleTitle);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyExistingList(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleElement(secondArticleTitle);
            ArticlePageObject.addArticleToMySaved();
        }
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        MyListsPageObject.waitForArticleToAppearByTitle(firstArticleTitle);
        MyListsPageObject.swipeByArticleToDelete(firstArticleTitle);

        MyListsPageObject.waitForArticleToAppearByTitle(searchWord);
        MyListsPageObject.clickArticleToMyList(searchWord);

        if (Platform.getInstance().isAndroid()) {
            MainPageObject.assertElementHasText(
                    ArticlePageObject.descriptionOfArticle(),
                    secondArticleTitle,
                    "Take another text"
            );
        } else {
            ArticlePageObject.waitForTitleElement(secondArticleTitle);
        }
    }

    @Test
    public void testSaveTwoArticlesDeleteOneArticle(){
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
