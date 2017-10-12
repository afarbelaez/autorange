package autorange;

import org.apache.log4j.Logger;
import java.io.IOException;

public class EnvironmentUtilities {

    private static Logger logger = Logger.getLogger(EnvironmentUtilities.class.getName());

    private static String deviceName;

    private static String platformVersion;

    private static String provider;

    private static String appPackage;


    static {
        try {
            setDeviceName();
            setVersion();
            setProvider();
            setAppPackage();

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
    public static void setAppPackage() throws IOException {
        if (System.getProperty("appPackage") != null) {
            appPackage = System.getProperty("appPackage");
            logger.info(String.format("App Package parameter set to [%s]", appPackage));
        }
    }

    /**
     * Sets provider
     */
    public static void setProvider() throws IOException{
        String platformProp = System.getProperty("provider");
        if (System.getProperty("provider") != null) {
            switch (platformProp) {
                case "local":
                    provider = "http://127.0.0.1:4723/wd/hub";
                    break;
                default:
                    provider = "http://127.0.0.1:4723/wd/hub";
                    break;
                // u can set here more provider such TestDroid, BrowserStack or others.
            }
            logger.info(String.format("provider parameter set to [%s]", platformProp));
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

    /**
     * @return deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @return provider
     */
    public String getProvider(){
        return provider;
    }

    /**
     * @return app package
     */
    public String getAppPackage() {
        return appPackage;
    }

    /**
     * @return platform version
     */
    public String getVersion() {
        return platformVersion;
    }
}
