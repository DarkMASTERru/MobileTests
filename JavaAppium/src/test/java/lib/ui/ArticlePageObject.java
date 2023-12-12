package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract  class ArticlePageObject extends  MainPageObject{

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        PAGE_SAVE,
        SNACKBAR_ACTION,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        DESCRIPTION,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        SAVE_TO_MY_LIST_BUTTON;

    private static String getTitleNameElement(String substring) {
        return TITLE.replace("{SUBSTRING}", substring);
    }

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public WebElement waitForDescriptionElement()
    {
        return this.waitForElementPresent(DESCRIPTION, "Cannot find article description on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS())
        { return title_element.getAttribute("label"); }
        else {
            return title_element.getText();}
    }

    public String getArticleDescription()
    {
        WebElement title_element = waitForDescriptionElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS())
        { return title_element.getAttribute("label"); }
        else {
            return title_element.getText();}
    }

    public void addArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                PAGE_SAVE,
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndClick(
                SNACKBAR_ACTION,
                "Cannot find button for add to list",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find name list input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find OK button",
                5
        );
    }

    public void addArticleToMyExistingList(String name_of_folder)
    {

        this.waitForElementAndClick(
                PAGE_SAVE,
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndClick(
                SNACKBAR_ACTION,
                "Cannot find button for add to list",
                5
        );

        this.waitForElementAndClick(
                "xpath://*[@text='" + name_of_folder + "']",
                "Cannot find list for save",
                5
        );

        this.waitForElementAndClick(
                SNACKBAR_ACTION,
                "Cannot find button for add to list",
                5
        );
    }
    public void closeArticle()
    {

        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find button to save article",
                5
        );
    }
    public WebElement descriptionOfArticle()
    {
        WebElement search_element = this.waitForElementPresent(
                DESCRIPTION,
                "Cannot find search field",
                15
        );
        return search_element;
    }

    public WebElement waitForTitleElement(String title) {
        return this.waitForElementPresent(getTitleNameElement(title),
                "Cannot find article title",
                15);
    }

    public void addArticleToMySaved() {
            if (Platform.getInstance().isMW()) {
                removeArticleFromSavedIfItAdded();
            }
            this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to add article to reading list", 5);
        }

    public void deleteArticleToMySaved() {
        if (Platform.getInstance().isMW()) {
            removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to add article to reading list", 5);
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        } else {
            this.scrollWebPageTillElementNoVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        }
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from seved",
                    2
            );
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to save list after removing it from find X link",
                    2
            );
        }
    }
    public void waitArticleAddMW() {
            this.waitForElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to add an article to save list after removing it from find X link",
                    2
            );
        }

    public void addArticlesToMySaved(){
        if (Platform.getInstance().isMW()){
            System.out.println("BEGINS addArticlesToMySaved");
            this.removeArticleFromSavedIfItWasAdded();
            System.out.println("addArticlesToMySaved + removeArticleFromSavedIfItWasAdded");
        }
        this.waitForElementAndClick(SAVE_TO_MY_LIST_BUTTON, "Save button not found", 15);
    }

    public void removeArticleFromSavedIfItWasAdded(){
        System.out.println("BEGINS removeArticleFromSavedIfItWasAdded");
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            //System.out.println("waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON");
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click on 'Remove from Saved' button", 1);
            //System.out.println("BEGINs waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON");
            this.waitForElementPresent(SAVE_TO_MY_LIST_BUTTON,
                    "Cannot find 'Add to Saved' button after removing article from Saved", 1);
            //System.out.println("END waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON");
        }
    }

    }
