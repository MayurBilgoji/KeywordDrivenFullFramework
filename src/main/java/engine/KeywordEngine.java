package engine;

import base.Base;
import excelUtils.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utility.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class KeywordEngine
{
    //public WebDriver driver;
    public Properties prop;
    //public static Workbook book;
    public XSSFWorkbook workbook;
    //public static Sheet sheet;
    public XSSFSheet sheet;
    public Base base;
    public WebElement element;
    public static WebDriver driver;
    private static String browserCloseFlag="NO";

    public static final Logger log = LogManager.getLogger(KeywordEngine.class.getName());

    public final String SCENARIO_SHEET_PATH="/Users/mayur.bilgoji/IdeaProjects/JPGJio/src/main/java/scenarios/JPG_TestCases.xlsx";

    public void startExecution(String sheetName)
    {
        String LocatorType=null;
        String LocatorValue=null;
        FileInputStream fiss=null;
        try {
            fiss=new FileInputStream("/Users/mayur.bilgoji/IdeaProjects/JPGJio/src/main/java/scenarios/JPG_TestCases.xlsx");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            //book=WorkbookFactory.create(fiss);
            workbook = new XSSFWorkbook(fiss);
        } catch (EncryptedDocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sheet=workbook.getSheet(sheetName);
        int k=0;
        System.out.println("Getting row data");
        for(int i=0;i<sheet.getLastRowNum()-1;i++)
        {
            System.out.println(sheet.getRow(i+1).getCell(k+4));
            LocatorType=sheet.getRow(i+1).getCell(k+4).toString().trim();
            System.out.println("LocatorType is "+LocatorType);
            LocatorValue=sheet.getRow(i+1).getCell(k+5).toString().trim();
            System.out.println("LocatorValue is "+LocatorValue);

            String actionKeyword=sheet.getRow(i+1).getCell(k+6).toString().trim();
            System.out.println("ActionKeyword is "+actionKeyword);
            String value=sheet.getRow(i+1).getCell(k+7).toString().trim();
            System.out.println("Value is "+value);

            switch (actionKeyword)
            {
                case "openBrowser":
                    base=new Base();
                    prop=base.init_properties();
                    if(value.isEmpty()||value.equals("NA"))
                    {
                        driver=base.init_driver(prop.getProperty("browserName"));
                    }
                    else
                    {
                        driver=base.init_driver(value);
                    }
                    System.out.println("Browser Launched");
                    break;
                case "getUrl":
                    if(value.isEmpty()||value.equals("NA"))
                    {
                        driver.get(prop.getProperty("EnterURL"));
                    }
                    else
                    {
                        System.out.println("getting Url "+ value);
               /*try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }*/
                        driver.get(value);
                    }
                    System.out.println("URL entered");
                    break;
                case "quit"    :
                    driver.quit();
                    System.out.println("Browser closed");
                    break;
                case "checkTitle":
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String title=driver.getTitle();
                    Assert.assertEquals(title, value);
                    break;
                case "wait":
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    break;

                default:
                    break;
            }

            switch (LocatorType) {
                case "id":
                    element=driver.findElement(By.id(LocatorValue));
                    if(actionKeyword.equalsIgnoreCase("sendKeys"))
                    {   element.clear();
                        element.sendKeys(value);
                    }
                    else if(actionKeyword.equalsIgnoreCase("Click"))
                    {
                        element.click();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    LocatorType=null;

                    break;

                case "linkText":
                    element=driver.findElement(By.linkText(LocatorValue));
                    element.click();
                    LocatorType=null;

                case "xpath":
                    element=driver.findElement(By.xpath(LocatorValue));
                    if(actionKeyword.equalsIgnoreCase("sendKeys"))
                    {   element.clear();
                        element.sendKeys(value);
                    }
                    else if(actionKeyword.equalsIgnoreCase("Click"))
                    {
                        element.click();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                    else if(actionKeyword.equalsIgnoreCase("verifyText"))
                    {
                        String text=element.getText();
                        Assert.assertEquals(text, value);
                    }
                    LocatorType=null;
                    break;

                default:
                    break;
            }
        }


    }


    //===============================Mutlitests in one sheet code start===============================================




    public void startExecution(String sheetName,int startRow,int endRow)

    {

        String LocatorType=null;

        String LocatorValue=null;

        //sheet=workbook.getSheet(sheetName);
        //k is column number
        int k=0;
        //System.out.println("Getting row data");
        log.info("Getting row data");
        for(int i=startRow;i<endRow+1;i++)
        {    ExcelUtil et=new ExcelUtil();
            // System.out.println(sheet.getRow(i+1).getCell(k+4));
            LocatorType=et.getCellValue(sheetName,(k+4),i);
            // System.out.println("LocatorType is "+LocatorType);
            log.info("LocatorType is "+LocatorType);
            LocatorValue=et.getCellValue(sheetName,(k+5),i);
            // System.out.println("LocatorValue is "+LocatorValue);
            log.info("LocatorValue is "+LocatorValue);

            //String actionKeyword=sheet.getRow(i+1).getCell(k+6).toString().trim();
            String actionKeyword=et.getCellValue(sheetName,(k+6), (i));
            //System.out.println("ActionKeyword is "+actionKeyword);
            log.info("ActionKeyword is "+actionKeyword);
            //String value=sheet.getRow(i+1).getCell(k+7).toString().trim();
            String value=et.getCellValue(sheetName, (k+7), (i));
            //System.out.println("Value is "+value);
            log.info("Value is "+value);




            switch (actionKeyword)
            {
                case "openBrowser":
                    base=new Base();
                    prop=base.init_properties();
                    if(value.isEmpty()||value.equals("NA"))
                    {
                        driver=base.init_driver(prop.getProperty("browserName"));
                    }
                    else
                    {
                        driver=base.init_driver(value);
                    }
                    //System.out.println("Browser Launched");
                    log.info("Browser Launched");

                    break;
                case "getUrl":
                    if(value.isEmpty()||value.equals("NA"))
                    {
                        driver.get(prop.getProperty("EnterURL"));
                    }
                    else
                    {
                        // System.out.println("getting Url "+ value);
                        log.info("getting Url "+ value);
                        driver.get(value);
                    }
                    //System.out.println("URL entered");
                    log.info("URL entered");
                    break;
                case "quit"    :
                    //driver.quit();
                    //System.out.println("Browser closed");
                    flagSetter("YES");
                    log.info("Setting browser closeflag to Yes");
                    break;
                case "checkTitle":
                    String title=driver.getTitle();
                    Assert.assertEquals(title, value);
                    log.info("Title matched");
                    break;
                case "wait":
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    break;
                case "SCROLL_VIEW":
                    WebElement el= driver.findElement(By.xpath(value));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",el);
                    log.info("Scrolled");
                    break;
                case "IS_NOT_DISPLAYED":
                    try {
                        WebElement ele = driver.findElement(By.xpath(value));
                    }
                    catch(Exception NoSuchElementException)
                    {
                        Assert.assertTrue(true);
                    }

                case "CLICK_ENTER":
                    Actions act= new Actions(driver);
                    act.sendKeys(Keys.ENTER);
                    act.build().perform();
                    log.info("Pressed enter key");
                    break;



                default:
                    break;
            }



            switch (LocatorType) {
                case "id":
                    element=driver.findElement(By.id(LocatorValue));
                    if(actionKeyword.equalsIgnoreCase("sendKeys"))
                    {   element.clear();
                        element.sendKeys(value);
                        log.info("Performing sendKeys");
                    }
                    else if(actionKeyword.equalsIgnoreCase("Click"))
                    {
                        element.click();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        log.info("Clicked using id");
                    }
                    else if(actionKeyword.equalsIgnoreCase("IS_DISPLAYED"))
                    {
                        Assert.assertEquals(true,element.isDisplayed());
                        log.info("Element is displayed");
                    }
                    LocatorType=null;
                    break;

                case "linkText":
                    element=driver.findElement(By.linkText(LocatorValue));
                    element.click();
                    LocatorType=null;

                case "xpath":
                    element=driver.findElement(By.xpath(LocatorValue));
                    if(actionKeyword.equalsIgnoreCase("sendKeys"))
                    {   element.clear();
                        element.sendKeys(value);
                        log.info("Performing sendKeys using xpath");
                    }
                    else if(actionKeyword.equalsIgnoreCase("Click"))
                    {
                        element.click();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        log.info("Clicked using xpath");

                    }
                    else if(actionKeyword.equalsIgnoreCase("verifyText"))
                    {
                        String text=element.getText();
                        Assert.assertEquals(text, value);
                        log.info("Text matched");
                    }
                    else if(actionKeyword.equalsIgnoreCase("IS_NOT_DISPLAYED"))
                    {
                        Assert.assertEquals(false,element.isDisplayed());
                    }
                    else if(actionKeyword.equalsIgnoreCase("IS_NOT_ENABLED"))
                    {
                        Assert.assertEquals(false,element.isEnabled());
                        log.info("Element is disabled ");
                    }
                    else if(actionKeyword.equalsIgnoreCase("IS_SELECTED"))
                    {
                        Assert.assertEquals(true,element.isSelected());
                        log.info("Element is selected");
                    }
                    else if(actionKeyword.equalsIgnoreCase("IS_ENABLED")) {
                        Assert.assertEquals(true, element.isEnabled());
                        log.info("Element is Enabled ");
                    }
                    else if(actionKeyword.equalsIgnoreCase("SELECT"))
                    {
                        Select sel=new Select(element);
                        sel.selectByVisibleText(value);
                        log.info("Selected element by visible text");
                    }
                    else if(actionKeyword.equalsIgnoreCase("CLEAR"))
                    {
                        element.clear();
                        log.info("Cleared Text box");
                    }
                    else if(actionKeyword.equalsIgnoreCase("IS_DISPLAYED"))
                    {
                        Assert.assertEquals(true,element.isDisplayed());
                        log.info(LocatorValue+" is Displayed");
                    }
                    LocatorType=null;
                    break;

                default:
                    break;
            }
        }



    }

    //==================================MultiTests in one sheet end=======================================
   //below code is for capturing screenshot of web
    public static String captureScreenshot()
    {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/Reports/Screenshots/JPG_" + Helper.getCurrentDateTime() + ".png";

        try {

            FileHandler.copy(src, new File(screenshotPath));
            log.info("Captured screenshot");
        }
        catch (IOException e)
        {
            System.out.println("Unable to capture screenshot" + e.getMessage());
        }
        return screenshotPath;
    }




    public static void closeBrowser()
    {
        driver.quit();
        log.info("Browser closed");
    }

    public static void flagSetter(String con)
    {
        browserCloseFlag=con;
    }

    public static String flagGetter()
    {
        log.info("Flag value is "+ browserCloseFlag);
        return browserCloseFlag;
    }



}
