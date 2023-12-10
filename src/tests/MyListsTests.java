package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.MyListsPageObjectFactory;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test_list";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        NavigationUi.clickSnackbarAction();
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticleAndDeleteOne() {
        String searchWord = "Java";
        String name_of_folder = "Test_list";
        String firstArticleTitle = "Object-oriented programming language";
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
}
