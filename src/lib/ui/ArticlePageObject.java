package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends  MainPageObject{

    private static final String
        TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]",
        FOOTER_ELEMENT = "xpath://android.widget.TextView[@text='View article in browser']",
        PAGE_SAVE = "id:org.wikipedia:id/page_save",
        SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc = 'Navigate up']",
        DESCRIPTION = "xpath://*[@resource-id='pcs-edit-section-title-description']";


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
}
