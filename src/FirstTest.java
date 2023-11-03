import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.time.Duration;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "8.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "C:\\Users\\Andrey Radugin\\OneDrive - ООО АЙФЭЛЛ\\Рабочий стол\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);
    }

    @After
    public void tearDown() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searchng by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find element",
                5
        );
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testAssertElementHasText() {
        WebElement search_element = waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_container']//*[@class = 'android.widget.TextView']"),
                "Cannot find search field",
                15
        );

        assertElementHasText(
                search_element,
                "Search Wikipedia",
                "Can not find search field"
        );
    }

    @Test
    public void testCancelSearchWithResults_Ex3_Ex4() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5
        );


        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java')]"),
                "Can't find search pages"
        );

        Assert.assertTrue("Not find search pages",
                quantityElements(By.xpath("//*[contains(@text, 'Java')]")).size() > 1);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on page",
                5
        );

        waitForElementNotPresent(
                By.id("//*[contains(@text, 'Java')]"),
                "Java pages is still present on page",
                5
        );

        Assert.assertTrue("Results are present",
                quantityElements(By.xpath("//*[contains(@text, 'Java')]")).size() == 0);

    }

    @Test
    public void testSwipeArticle() {

        waitForElementPresent(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );

        verticalSwipeToBottom();
        verticalSwipeToBottom();
        verticalSwipeToBottom();
        verticalSwipeToBottom();

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"),
                "Cannot find element",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@text = 'Automation for Apps']"),
                "Cannot find article title",
                15
        );

        verticalSwipeToBottom();
        verticalSwipeToBottom();
        verticalSwipeToBottom();

        swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );

    }

    @Test
    public void saveFirstArticleToMyList()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find element",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find button for add to list",
                5
        );

//        waitForElementAndClick(
//                By.id("org.wikipedia:id/snackbar_action"),
//                "Cannot find button for choose list",
//                5
//        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Test_list",
                "Cannot find name list input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot find OK button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find view list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'Remove from Test_list']"),
                "Cannot find list for delete article",
                5
        );

//        waitForElementAndClick(
//                By.xpath("//*[@text = 'Saved']"),
//                "Cannot choose save list",
//                5
//        );
//
//        waitForElementAndClick(
//                By.xpath("//*[@text = 'Test_list']"),
//                "Cannot choose save Test_list",
//                5
//        );

//        waitForElementAndClick(
//                By.xpath("//*[@text = 'OK']"),
//                "Cannot find OK button",
//                5
//        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language) removed from Test_list']"),
                "Cannot delete article from lists",
                5
        );
    }

    @Test
    public void saveTwoArticleAndDeleteOne() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find element",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find button for add to list",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Test_list",
                "Cannot find name list input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot find OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find view list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island in Indonesia']"),
                "Cannot find search article",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find add list button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Test_list']"),
                "Cannot find list for save",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='View list']"),
                "Cannot find view list",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Java (programming language)']"),
                "Cannot find added article",
                15
        );

        LeftSwipeToBottom(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Java (programming language)']"),
                "Cannot swipe element"
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Java (programming language)']"),
                "Article was not delete",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Island in Indonesia']"),
                "Cannot find second article",
                15
        );


        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Island in Indonesia']"),
                "Cannot find second article",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@text='Island in Indonesia']"),
                "Cannot find second article",
                5
        );

        WebElement search_element = waitForElementPresent(
                By.xpath("//*[@class = 'android.widget.TextView'][@text='Island in Indonesia']"),
                "Cannot find search field",
                15
        );

        assertElementHasText(
                search_element,
                "Island in Indonesia",
                "Take another text"
        );
    }

    @Test
    public void assertTittle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find element",
                5
        );

        assertElementPresent("TITTLE", "Not assert");
    }

    @Test
    public void testChangeOrientationOnSearchResult()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send text in search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find element search arti",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@class='android.widget.TextView'][@text='Java (programming language)']"),
                "Cannot find title of article",
                15
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@class='android.widget.TextView'][@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@class='android.widget.TextView'][@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screem rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@class='android.widget.TextView'][@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screem rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testChechSearchArticleBackground()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send text in search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find element search arti",
                5
        );

        driver.runAppInBackground(Duration.ofSeconds(2));

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutIsSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutIsSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutIsSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutIsSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(WebElement assertElement, String waitingText, String error_message) {
        String textElement = assertElement.getAttribute("text");

        Assert.assertEquals(
                error_message,
                waitingText,
                textElement
        );

    }

    private List quantityElements(By by) {
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
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
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

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutSeconds)
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
}
