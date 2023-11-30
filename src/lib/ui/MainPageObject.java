package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MainPageObject {
    protected static AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public static WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public static WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public static WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public static WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public static boolean waitForElementNotPresent(By by, String error_message, long timeoutIsSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutIsSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutIsSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutIsSeconds);
        element.clear();
        return element;
    }

    public static void assertElementHasText(WebElement assertElement, String waitingText, String error_message) {
        String textElement = assertElement.getAttribute("text");

        Assert.assertEquals(
                error_message,
                waitingText,
                textElement
        );

    }
    public static void assertElementWithText(WebElement assertElement, String waitingText, String error_message) {
        String textElement = assertElement.getAttribute("text");

        Assert.assertEquals(
                error_message,
                waitingText,
                textElement
        );

    }

    public static List quantityElements(By by) {
        List elements_search = driver.findElements(by);
        return elements_search;
    }

    protected void swipeUp (long timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }
    public void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            verticalSwipeToBottom();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();

    }

    public void verticalSwipeToBottom(){
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int centerX = size.width / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);

        //Дваигаем палец на начальную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(),centerX,(int)startY));
        //Палец прикасакается к экрану
        swipe.addAction(finger.createPointerDown(0));

        //Палец двигается к конечной точке
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX,(int)endY));

        //Убираем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        //Выполняем действия
        driver.perform(Arrays.asList(swipe));
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        return element.getAttribute(attribute);
    }

    public void LeftSwipeToBottom(By by, String error_message){

        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

//        Dimension size = driver.manage().window().getSize();
//        int startY = (int) (size.height * 0.70);
//        int endY = (int) (size.height * 0.30);
//        int centerX = size.width / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);

        //Дваигаем палец на начальную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(),right_x,(int)middle_y));
        //Палец прикасакается к экрану
        swipe.addAction(finger.createPointerDown(0));

        //Палец двигается к конечной точке
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),left_x,(int)middle_y));

        //Убираем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        //Выполняем действия
        driver.perform(Arrays.asList(swipe));
    }
    public void assertElementPresent(String locator, String error_message) {
        int amount_of_elements = driver.findElements(By.xpath("//*[@title='" + locator + "']")).size();
        if (amount_of_elements == 0) {
            String default_message = "An element '" + locator + "' is supposed to be present.";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
    public int getAmmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }
}
