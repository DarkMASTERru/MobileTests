package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase{
    protected RemoteWebDriver driver;
    protected Platform Platform;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        if (driver instanceof AppiumDriver) {
            this.rotateScreenPortrait();
            this.skipWelcomePageForIOSApp();
        } else this.openWikiWebPageForMobileWeb();

    }

    @After
    @Step("Remove driver and session")
    public void tearDown(){
        if (driver instanceof AppiumDriver) {
            driver.quit();
        } else driver.quit();
    }

    @Step("Rotate screen to portrait mode.")
    protected void rotateScreenPortrait(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
//            driver.rotate(ScreenOrientation.PORTRAIT);
        } else System.out.println("RotateScreenPortrait method does noting for platform " +
                Platform.getInstance().getPlatformVar());
    }

    @Step("Rotate screen to landscape mode.")
    protected void rotateScreenLandscape(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
//            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else System.out.println("RotateScreenLandscape method does noting for platform " +
                Platform.getInstance().getPlatformVar());
    }

    @Step("Send mobile app to background. This method doesn't work for mobile web.")
    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else System.out.println("RotateScreenLandscape method does noting for platform " +
                Platform.getInstance().getPlatformVar());
    }

    @Step("Opens start page for mobile web. This method doesn't work for Android and IOS.")
    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org/");
//            driver.manage().window().maximize();
        } else System.out.println("openWikiWebPageForMobileWeb method does noting for mobile platform " +
                Platform.getInstance().getPlatformVar());
    }

    @Step("Skips welcome page in mobile app. This method doesn't work for mobile web.")
    private void skipWelcomePageForIOSApp() {
        if (driver instanceof AppiumDriver) {
            WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
//            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
//    private void skipWelcomePage() {
//        if (Platform.getInstance.isIOS()) {
//            AppiumDriver driver = (AppiumDriver) this.driver;
//            WelcomePageObject WelcomePageObject = WelcomePageObject.get(driver);
//            WelcomePageObject.clickSkip();
//        } else System.out.println("SkipWelcomePage method does noting for platform " +
//                Platform.getInstance().getPlatformVar());
//    }

    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-framework/allure-core/wiki/Environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
