package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public abstract class NavigationUi extends  MainPageObject{

    protected static String
            PAGE_SAVE,
            SNACKBAR_ACTION,
            BACK_BUTTON,
            REMOVE_FROM_ELEMENT;
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickPageSave()
    {
        this.waitForElementAndClick(
                PAGE_SAVE,
                "Cannot find button to save article",
                5
        );
    }

    public void clickSnackbarAction()
    {
        this.waitForElementAndClick(
                SNACKBAR_ACTION,
                "Cannot find view list",
                5
        );
    }

    public void clickRemoveFromList()
    {
        this.waitForElementAndClick(
                REMOVE_FROM_ELEMENT,
                "Cannot find list for delete article",
                5
        );
    }
    public void backButton()
    {
        this.waitForElementAndClick(
                BACK_BUTTON,
                "Cannot find back button",
                5
        );
    }
}
