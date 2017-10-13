package autorange;

import org.testng.TestListenerAdapter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener extends TestListenerAdapter {

    static Logger log = Logger.getLogger(TestListener.class.getName());

    @Override
    public void onTestFailure(ITestResult tr) {
        // to get test method and parameter details
        String instanceName = tr.getInstanceName();
        String methodName = tr.getMethod().getMethodName();
        StringBuffer strBufferForParameters = new StringBuffer();
        Object[] parameters = tr.getParameters();
        for (Object parameter : parameters){
            // handling windows special characters
            if (parameter.toString().matches("[a-zA-Z0-9& ]*")) {
                strBufferForParameters.append(parameter.toString() + ",");
            }
        }

        // take screenshot
        WebDriver driver = DriverCore.getDriver();
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // get the destination folder path - relative path
        //Properties envProps = EnvironmentUtilities.readAllProperties(System.getProperty("env"));
        String failureScreenshotFolder = "";

        // define the destination file name
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        String failureScreenshotFileName = String.format("%s_%2s_%3s_%4s.png", dateFormat.format(new Date()), instanceName, methodName, strBufferForParameters.toString());

        // create folder if folder does not exist
        File theDir = new File(failureScreenshotFolder);
        if (!theDir.exists())
            new File(failureScreenshotFolder).mkdirs();

        // copy file to destination file path
        try {
            FileUtils.copyFile(screenshotFile, new File(failureScreenshotFolder + "/" + failureScreenshotFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // log the message for testng html file
        Reporter.log("FAILURE SCREEHSHOT CREATED.");
    }

}
