package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            CLOSE_SYNC_OVERLAY_BUTTON,
            DELETE_ATRIKLE_FROM_LIST_TPL;


    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }


    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getDeleteButtonFromList(String article_title)
    {
        return DELETE_ATRIKLE_FROM_LIST_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                "xpath://[@text='" + folder_name_xpath + "']",
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void refreshPage()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.navigate().refresh();
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.LeftSwipeToBottom(
                article_xpath,
                "Cannot swipe to delete article"
        );
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_title,
                    "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);

    }
    public void clickArticleToMyList(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot click article by title " + article_title,
                15
        );
    }

    public void closeSyncOverlay(){
        this.waitForElementAndClick(CLOSE_SYNC_OVERLAY_BUTTON, "Close button not found on Sync overlay", 5);
    }

    public void deleteArticleFromList(String article_title){
        String button_xpath = getDeleteButtonFromList(article_title);
        this.waitForElementAndClick(button_xpath, "Close button not found on Sync overlay", 5);
    }

}
