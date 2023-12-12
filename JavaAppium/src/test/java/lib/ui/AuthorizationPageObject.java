package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "xpath://html/body/div[5]//a/span",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_INPUT = "css:button#wpLoginAttempt",
            MAIN_MENU = "css:[for='main-menu-input']",
            SEARCH_CANCEL_BUTTON = "css:button.cancel";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Clicking authorization button")
    public void clickAuthButton(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find and click Main menu button", 10);
//        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click Main menu button", 5);
//        this.waitForElementAndClick(MAIN_MENU, "Cannot find and click Main menu button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click Log In button", 5);
    }

    @Step("Entering login data")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and input login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and input password", 5);
    }

    @Step("Clicking submit button")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_INPUT, "Cannot find and click submit auth button", 5);
    }
}