package autorange;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtils extends DriverCore {
    static Logger log = Logger.getLogger(PageUtils.class);

    /**
     * Find Element
     *
     * @param locator
     * @return
     */
    public WebElement getElement(String locator){
        By elementId;
        if(locator.contains("//")){
            elementId = By.xpath(locator);
        } else{
            elementId = By.cssSelector(locator);
        }

        try{
            WebElement element = new WebDriverWait(driver, 30)
                    .until(ExpectedConditions.visibilityOfElementLocated(elementId));
            return element;
        }
        catch(NoSuchElementException e){
            return null;
        }
    }
    
    /**
     * Find Element
     *
     * @param locator
     * @return
     */
    public WebElement getElement(String locator, int customWait){
        By elementId;
        if(locator.contains("//")){
            elementId = By.xpath(locator);
        } else{
            elementId = By.cssSelector(locator);
        }

        try{
            WebElement element = new WebDriverWait(driver, customWait)
                    .until(ExpectedConditions.visibilityOfElementLocated(elementId));
            return element;
        }
        catch(NoSuchElementException e){
            return null;
        }
    }

    /**
     * Waits till second condition is completed
     *
     * @param timeOutInSeconds
     */
    public static void idle(final long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds + 2) {
        }.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                boolean retVal = false;
                long t1 = System.currentTimeMillis();
                while (true) {
                    long t2 = System.currentTimeMillis();
                    if ((t2 - t1) / 1000 > timeOutInSeconds) {
                        retVal = true;
                        break;
                    }
                }
                return retVal;
            }

        });
    }
}
