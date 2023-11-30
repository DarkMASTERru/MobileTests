package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends  MainPageObject{

    private static final String
        TITLE = "//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]",
        FOOTER_ELEMENT = "//android.widget.TextView[@text='View article in browser']",
        PAGE_SAVE = "org.wikipedia:id/page_save",
        SNACKBAR_ACTION = "org.wikipedia:id/snackbar_action",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text = 'OK']",
        CLOSE_ARTICLE_BUTTON = "//*[@content-desc = 'Navigate up']",
        DESCRIPTION = "//*[@resource-id='pcs-edit-section-title-description']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }
    public void addArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                By.id(PAGE_SAVE),
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndClick(
                By.id(SNACKBAR_ACTION),
                "Cannot find button for add to list",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find name list input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find OK button",
                5
        );
    }

    public void addArticleToMyExistingList(String name_of_folder)
    {

        this.waitForElementAndClick(
                By.id(PAGE_SAVE),
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndClick(
                By.id(SNACKBAR_ACTION),
                "Cannot find button for add to list",
                5
        );

        this.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find list for save",
                5
        );

        this.waitForElementAndClick(
                By.id(SNACKBAR_ACTION),
                "Cannot find button for add to list",
                5
        );
    }
    public void closeArticle()
    {

        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find button to save article",
                5
        );
    }
    public WebElement descriptionOfArticle()
    {
        WebElement search_element = this.waitForElementPresent(
                By.xpath(DESCRIPTION),
                "Cannot find search field",
                15
        );
        return search_element;
    }
}
