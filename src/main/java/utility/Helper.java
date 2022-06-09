package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static String captureScreenshot(WebDriver driver) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/JPG" + getCurrentDateTime() + ".png";

        try {

            FileHandler.copy(src, new File(screenshotPath));
        } catch (IOException e) {
            System.out.println("Unable to capture screenshot" + e.getMessage());
        }
        return screenshotPath;
    }
    public static String getCurrentDateTime() {
        DateFormat customFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return customFormat.format(currentDate);
    }

}
