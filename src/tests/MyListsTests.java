package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test_list";
        NavigationUi NavigationUi = new NavigationUi(driver);
        ArticlePageObject.addArticleToMyList(name_of_folder);
        NavigationUi.clickSnackbarAction();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticleAndDeleteOne() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLines("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Test_list";

        NavigationUi NavigationUi = new NavigationUi(driver);

        ArticlePageObject.addArticleToMyList(name_of_folder);


        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        NavigationUi.backButton();
        SearchPageObject.clickByArticleWithSubstring("Island in Indonesia");

        ArticlePageObject.addArticleToMyExistingList(name_of_folder);

        MyListsPageObject.waitForArticleToAppearByTitle("Java (programming language)");
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");

        MyListsPageObject.waitForArticleToAppearByTitle("Java");
        MyListsPageObject.clickArticleToMyList("Java");


        MainPageObject.assertElementHasText(
                ArticlePageObject.descriptionOfArticle(),
                "Island in Indonesia",
                "Take another text"
        );
    }

}
