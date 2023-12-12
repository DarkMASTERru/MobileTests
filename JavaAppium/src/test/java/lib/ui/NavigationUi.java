package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class NavigationUi extends  MainPageObject{

    protected static String
            PAGE_SAVE,
            SNACKBAR_ACTION,
            BACK_BUTTON,
            REMOVE_FROM_ELEMENT,
            OPEN_NAVIGATION,
            MY_LIST_LINK,
            NAVIGATE_TO_MAIN_PAGE_BUTTON,
            SAVED_LISTS_BUTTON;
    public NavigationUi(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Click PageSave button")
    public void clickPageSave()
    {
        this.waitForElementAndClick(
                PAGE_SAVE,
                "Cannot find button to save article",
                5
        );
    }

    @Step("Click snackbar action")
    public void clickSnackbarAction()
    {
        this.waitForElementAndClick(
                SNACKBAR_ACTION,
                "Cannot find view list",
                5
        );
    }

    @Step("Click remove from list")
    public void clickRemoveFromList()
    {
        this.waitForElementAndClick(
                REMOVE_FROM_ELEMENT,
                "Cannot find list for delete article",
                5
        );
    }
    @Step("Click back button")
    public void backButton()
    {
        this.waitForElementAndClick(
                BACK_BUTTON,
                "Cannot find back button",
                5
        );
    }

    @Step("Opening navigation panel")
    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyList() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_LIST_LINK,
                    "Cannot find navigation button to my list",
                    5);
        } else {
            this.waitForElementAndClick(
                    MY_LIST_LINK,
                    "Cannot find navigation button to my list",
                    5
            );
        }
    }

    @Step("Returning from search results page to main page")
    public void returnFromSearchResultsToMainPage(){
        //возвращаюсь со страницы результатов поиска на главную страницу
        this.waitForElementAndClick(NAVIGATE_TO_MAIN_PAGE_BUTTON,
                "'Navigate up' from search results page to main page not found on toolbar",
                5);
    }

    @Step("Clicking Saved lists button")
    public void clickSavedLists(){
        //click Saved button on navigation panel
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(SAVED_LISTS_BUTTON,
                    "Cannot find and click 'Saved' button on panel",15);
        } else {
        this.waitForElementAndClick(SAVED_LISTS_BUTTON,
                "Saved button not found on navigation panel",
                5);
    }}
}
