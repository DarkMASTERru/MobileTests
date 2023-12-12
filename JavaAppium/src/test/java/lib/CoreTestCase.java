package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;
    protected Platform Platform;

    @Override
    protected void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        if (driver instanceof AppiumDriver) {
            this.rotateScreenPortrait();
            this.skipWelcomePageForIOSApp();
            super.tearDown();
        } else this.openWikiWebPageForMobileWeb();

    }

    @Override
    protected void tearDown() throws Exception {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
        super.tearDown();
        } else driver.quit();
    }

    protected void rotateScreenPortrait(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else System.out.println("RotateScreenPortrait method does noting for platform " +
                Platform.getInstance().getPlatformVar());
    }

    protected void rotateScreenLandscape(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else System.out.println("RotateScreenLandscape method does noting for platform " +
                Platform.getInstance().getPlatformVar());
    }

    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else System.out.println("RotateScreenLandscape method does noting for platform " +
                Platform.getInstance().getPlatformVar());
    }

    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org/");
//            driver.manage().window().maximize();
        } else System.out.println("openWikiWebPageForMobileWeb method does noting for mobile platform " +
                Platform.getInstance().getPlatformVar());
    }

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
}
