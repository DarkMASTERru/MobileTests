package lib.ui;

import io.qameta.allure.Step;
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

    @Step("Waiting for article title element")
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    @Step("Waiting for article description element")
    public WebElement waitForDescriptionElement()
    {
        return this.waitForElementPresent(DESCRIPTION, "Cannot find article description on page!", 15);
    }

    @Step("Getting an article title")
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

    @Step("Getting an article description")
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

    @Step("Adding an article to a new list in Saved")
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

    @Step("Adding an article to a existing list in Saved")
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

    @Step("Closing the article and returning to search result page")
    public void closeArticle()
    {

        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find button to save article",
                5
        );
    }

    @Step("Waiting description of article")
    public WebElement descriptionOfArticle()
    {
        WebElement search_element = this.waitForElementPresent(
                DESCRIPTION,
                "Cannot find search field",
                15
        );
        return search_element;
    }

    @Step("Waiting title of article")
    public WebElement waitForTitleElement(String title) {
        return this.waitForElementPresent(getTitleNameElement(title),
                "Cannot find article title",
                15);
    }

    @Step("Add article to my saved list")
    public void addArticleToMySaved() {
            if (Platform.getInstance().isMW()) {
                removeArticleFromSavedIfItAdded();
            }
            this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to add article to reading list", 5);
        }

    @Step("Delete article to my saved list")
    public void deleteArticleToMySaved() {
        if (Platform.getInstance().isMW()) {
            removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to add article to reading list", 5);
    }

    @Step("Swiping to the footer element")
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

    @Step("Remove article from saved if it added")
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

    @Step("Wait for add article to watchlist")
    public void waitArticleAddMW() {
            this.waitForElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to add an article to save list after removing it from find X link",
                    2
            );
        }

    @Step("Add articles to my saved")
    public void addArticlesToMySaved(){
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItWasAdded();
        }
        this.waitForElementAndClick(SAVE_TO_MY_LIST_BUTTON, "Save button not found", 15);
    }

    @Step("Removing article from the Saved section. Not applicable on Android and iOS")
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
