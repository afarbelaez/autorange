package autorange;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebEnvironmentsUtils extends DriverCore{
    static Logger log = Logger.getLogger(WebEnvironmentsUtils.class.getName());
    
    public WebEnvironmentsUtils() throws MalformedURLException{
    	setWebBrowserDriver();
    }
    
    private static void setWebBrowserDriver() throws MalformedURLException{
    	String browser = env.getBrowser();
    	if(env.getProvider().equals("local")){
	        switch (browser){
	            case "chrome":
	                log.info("Starting local Chrome driver.");
	                setLocalChrome();
	                break;
	            case "firefox":
	                log.info("Starting local Firefox driver.");
	                setLocalFirefox();
	                break;
	            default:
	                break;
	        }
    	} else if(env.getProvider().equals("remote")){
    		DesiredCapabilities caps = null;
    		switch (browser) {
			case "chrome":
				caps = getChromeCapabilities();
				break;
			case "firefox":
				caps = getFirefoxCapabilities();
			default:
				break;
			}
    		
    		caps.setCapability("platform", env.getOs());
    		caps.setCapability("version", env.getBrowserVersion());
    		
    		driver = new RemoteWebDriver(new URL("http://afarbelaez:ee43ec4f-9bb4-47af-af72-6ebdf53384ee@ondemand.saucelabs.com:80/wd/hub"), caps);

    	}
    }

    private static void setLocalChrome(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--window-size=1900,1000");
        options.addArguments("--disable-internal-flash");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
    }

    private static void setLocalFirefox(){
        driver = new FirefoxDriver();
    }
    
    private static DesiredCapabilities getFirefoxCapabilities(){
		DesiredCapabilities caps = DesiredCapabilities.firefox();
		return caps;
    }
    
    private static DesiredCapabilities getChromeCapabilities(){
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		return caps;
    }
}
