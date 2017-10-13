package autorange;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverCore {
    static Logger log = Logger.getLogger(DriverCore.class.getName());


    protected static WebDriver driver;
    protected static EnvironmentUtilities env = new EnvironmentUtilities();
    protected static Properties envProps;

    /**
     * Gets Global driver
     * @return
     */
    public static WebDriver getDriver(){
        return driver;
    }

    /**
     * Sets the failures folder
     */
    protected void setFailuresFolder(String failuresFolder){
        // folder location for failure screenshots
        try {
            FileUtils.cleanDirectory(new File(failuresFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
