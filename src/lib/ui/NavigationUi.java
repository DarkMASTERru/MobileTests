package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends  MainPageObject{

    private static final String
            PAGE_SAVE = "org.wikipedia:id/page_save",
            SNACKBAR_ACTION = "org.wikipedia:id/snackbar_action",
            BACK_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickPageSave()
    {
        this.waitForElementAndClick(
                By.id(PAGE_SAVE),
                "Cannot find button to save article",
                5
        );
    }

    public void clickSnackbarAction()
    {
        this.waitForElementAndClick(
                By.id(SNACKBAR_ACTION),
                "Cannot find view list",
                5
        );
    }

    public void clickRemoveFromList()
    {
        this.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Remove from')]"),
                "Cannot find list for delete article",
                5
        );
    }
    public void backButton()
    {
        this.waitForElementAndClick(
                By.xpath(BACK_BUTTON),
                "Cannot find back button",
                5
        );
    }
}
