package autorange;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestUtils extends DriverCore{


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {

        env = new EnvironmentUtilities();

        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "resources/");
        File app = new File(appDir, "rappi.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", env.getDeviceName());
        capabilities.setCapability("platformVersion", env.getVersion());
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", env.getAppPackage());
        driver = new AndroidDriver(new URL(env.getProvider()), capabilities);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
