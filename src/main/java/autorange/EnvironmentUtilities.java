package autorange;

import org.apache.log4j.Logger;

import java.io.IOException;

public class EnvironmentUtilities {

    private static Logger logger = Logger.getLogger(EnvironmentUtilities.class.getName());

    private static String deviceName;

    private static String platform;

    private static String appName;

    private static String platformVersion;

    private static String provider;

    private static String appLocation;

    private static String browser;
    
    private static String browserVersion;
    
    private static String os;


    static {
        try {
            setPlatform();
            setDeviceName();
            setVersion();
            setProvider();
            setAppName();
            setAppLocation();
            setBrowser();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set DeviceName
     */
    public static void setDeviceName() throws IOException {
        if (System.getProperty("deviceName") != null) {
            deviceName = System.getProperty("deviceName");
            logger.info(String.format("browser parameter set to [%s]", deviceName));
        }
    }

    /**
     * Set App Package
     */
    public static void setAppLocation() throws IOException {
        if (System.getProperty("appLocation") != null) {
            appLocation = System.getProperty("appLocation");
            logger.info(String.format("App Package parameter set to [%s]", appLocation));
        }
    }

    /**
     * Sets provider
     */
    public static void setProvider() throws IOException{
        if (System.getProperty("provider") != null) {
        	provider = System.getProperty("provider");
            logger.info(String.format("provider parameter set to [%s]", System.getProperty("provider")));
        }
    }


    /**
     * Sets Mobile Version
     */
    private static void setVersion() throws IOException {
        if (System.getProperty("platformVersion") != null) {
            platformVersion = System.getProperty("platformVersion");
            logger.info(String.format("Version parameter set to [%s]", platformVersion));
        }
    }

    public String getPlatform() {
        return platform;
    }

    private static void setPlatform() {
        if (System.getProperty("platform") != null) {
            platform = System.getProperty("platform");
            logger.info(String.format("Platform parameter set to [%s]", platform));
        }
    }

    public String getAppName() {
        return appName;
    }

    private static void setAppName() {
        if (System.getProperty("appName") != null) {
            appName = System.getProperty("appName");
            logger.info(String.format("AppName parameter set to [%s]", appName));
        }
    }

    public static void setBrowser() {
        if (System.getProperty("browser") != null) {
            browser = System.getProperty("browser");
            logger.info(String.format("browser parameter set to [%s]", browser));
        }
    }
    
    public static void setOs() {
        if (System.getProperty("os") != null) {
            browser = System.getProperty("os");
            logger.info(String.format("OS parameter set to [%s]", os));
        }
    }
    
    public static void setBrowserVersion() {
        if (System.getProperty("browserVersion") != null) {
            browser = System.getProperty("browserVersion");
            logger.info(String.format("browser Version parameter set to [%s]", browserVersion));
        }
    }

    /**
     * Sets all the stand alone driver paths.
     */
    public void setDriverPath(){
        logger.info("Setting Driver path...");
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        if (os.equals("mac")) {
            System.setProperty("webdriver.chrome.driver", "/Users/afarbelaez/Documents/Autorange/autorange/src/resources/drivers/chromedriver");
            System.setProperty("webdriver.gecko.driver",  "/Users/afarbelaez/Documents/Autorange/autorange/src/resources/drivers/geckodriver");
        }
    }


    public String getBrowser() { return browser; }

    /**
     * @return deviceName
     */
    public String getDeviceName() { return deviceName; }

    /**
     * @return provider
     */
    public String getProvider(){ return provider; }

    /**
     * @return app package
     */
    public String getAppLocation() { return appLocation; }

    /**
     * @return platform version
     */
    public String getVersion() { return platformVersion; }

	public String getOs() { return os; }

	public String getBrowserVersion() { return browserVersion; }
}
