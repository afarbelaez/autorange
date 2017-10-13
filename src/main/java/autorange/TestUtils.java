package autorange;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

@Listeners({TestListener.class})
public class TestUtils extends DriverCore{

    /**
     * Starts driver instantiation
     * @throws Exception 
     */
    public void startDriver() throws Exception{
        String platform = env.getPlatform();
        switch (platform) {
            case "local_android":
                // set up appium
                File app = new File("/Users/afarbelaez/Downloads/" + env.getAppLocation());

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("automationName", "Appium");
                capabilities.setCapability("deviceName", env.getDeviceName());
                capabilities.setCapability("platformVersion", env.getVersion());
                capabilities.setCapability("app", app.getAbsolutePath());
                capabilities.setCapability("appPackage", env.getAppName());
                capabilities.setCapability("fullReset", false);
                capabilities.setCapability("newCommandTimeout", 360);
                capabilities.setCapability("eventTimings", true);
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                break;
            case "web":
                new WebEnvironmentsUtils();
            default:
                break;
        }
    }

    /**
     * Starts driver instantiation
     */
    public static void endDriver(){
        driver.quit();
    }

    /**
     * Set the listener adapters.
     */
    protected void setListenersAdapters(){
        // test listener adapter
        TestListenerAdapter listeners = new TestListenerAdapter();
        TestNG tester = new TestNG();
        tester.addListener(listeners);
    }
}
