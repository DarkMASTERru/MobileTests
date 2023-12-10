package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        OPTIONS_ADD_TO_MY_LIST_BUTTON;

    private static String getTitleNameElement(String substring) {
        return TITLE.replace("{SUBSTRING}", substring);
    }

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20
        );
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
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }
}
