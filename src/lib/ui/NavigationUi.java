package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends  MainPageObject{

    private static final String
            PAGE_SAVE = "id:org.wikipedia:id/page_save",
            SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action",
            BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            REMOVE_FROM_ELEMENT = "xpath://*[contains(@text, 'Remove from')]";
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
