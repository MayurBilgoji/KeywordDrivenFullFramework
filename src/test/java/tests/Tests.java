package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import engine.KeywordEngine;
import excelUtils.MultiCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Helper;

import java.io.File;
import java.io.IOException;

public class Tests {
    public KeywordEngine keywordEngine;
    public ExtentReports report;
    public ExtentTest logger;
    public WebDriver driver;
    // public static Logger log = LogManager.getLogger(Tests.class.getName());
    public static final Logger log = LogManager.getLogger(Tests.class.getName());


    @BeforeSuite
    public void setUp()
    {
        //System.out.println("Before Suite executed");
        log.info("Before Suite executed");
        ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/JPG"+ Helper.getCurrentDateTime()+".html"));
        report=new ExtentReports();
        report.attachReporter(extent);

    }

    //======================================================================================================================
    @Test(dataProvider="provideDataToTest")
    public void testExecutor(String testcase) throws InterruptedException
    {
        // System.out.println("Using data Provider");
        log.info("Using data Provider");
        MultiCases mcs=new MultiCases();
        String sheetName="Payments";
        logger=report.createTest(testcase+"  "+mcs.getTestName(testcase,sheetName));
        logger.info("Test executed ===> "+ mcs.getTestName(testcase,sheetName));
        String testname=mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);

    }

    @DataProvider
    public Object[][] provideDataToTest() throws Exception
    {
        //System.out.println("Data providing to test");
        log.info("Data providing to test");
        Object[][] obj=new Object[][]
                {
                        {"TC_1"},{"TC_2"},{"TC_3"}
                };
        return obj;
    }

    //======================================================================================================================

    @Test(dataProvider="provid")
    public void testEx(String testcase) throws InterruptedException
    {
        // System.out.println("Using data Provider");
        log.info("Using data Provider");
        MultiCases mcs=new MultiCases();
        String sheetName="scenarios";
        logger=report.createTest(testcase+"  "+mcs.getTestName(testcase,sheetName));
        logger.info("Test executed ===> "+ mcs.getTestName(testcase,sheetName));
        String testname=mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);

    }

    @DataProvider
    public Object[][] provid() throws Exception
    {
        //System.out.println("Data providing to test");
        log.info("Data providing to test");
        Object[][] obj=new Object[][]
                {
                        {"TC_1"},{"TC_2"}
                };
        return obj;
    }




    //==================
    @Test (dataProvider="provideDataToTest2")
    public void testExecutor2(String testcase) throws InterruptedException
    {
        // System.out.println("Using data Provider");
        log.info("Using data Provider");
        MultiCases mcs=new MultiCases();
        String sheetName="Cards_Tests";
        logger=report.createTest(testcase+"  "+mcs.getTestName(testcase,sheetName));
        logger.info("Test executed ===> "+ mcs.getTestName(testcase,sheetName));
        String testname=mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);

    }


    @DataProvider
    public Object[][] provideDataToTest2() throws Exception
    {
        //System.out.println("Data providing to test");
        log.info("Data providing to test");
        Object[][] obj=new Object[][]
                {
                        {"TC_1"},{"TC_2"},{"TC_3"},{"TC_4"},{"TC_5"},{"TC_6"},{"TC_7"},{"TC_8"},{"TC_9"},{"TC_10"},{"TC_11"},{"TC_12"}
                };
        return obj;
    }

    //======================================================================================================================
    @Test (dataProvider="tokenizationprovideDataToTest")
    public void tokenizationTestExecutor(String testcase) throws InterruptedException
    {
        // System.out.println("Using data Provider");
        log.info("Using data Provider");
        MultiCases mcs=new MultiCases();
        String sheetName="CardTokenization";
        logger=report.createTest(testcase+"  "+mcs.getTestName(testcase,sheetName));
        logger.info("Test executed ===> "+ mcs.getTestName(testcase,sheetName));
        String testname=mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);

    }

    @DataProvider
    public Object[][] tokenizationprovideDataToTest() throws Exception
    {
        //System.out.println("Data providing to test");
        log.info("Data providing to test");
        Object[][] obj=new Object[][]
                {
                        {"TC_1"},{"TC_2"},{"TC_3"},{"TC_4"},{"TC_5"},{"TC_6"},{"TC_7"},{"TC_8"},{"TC_9"},{"TC_10"},{"TC_11"},{"TC_12"},{"TC_13"},{"TC_14"}
                        ,{"TC_15"}
                };
        return obj;
    }

    //======================================================================================================================
    @Test (dataProvider="smokeDataToTest")
    public void smokeExecutor(String testcase) throws InterruptedException
    {
        // System.out.println("Using data Provider");
        log.info("Using data Provider");
        MultiCases mcs=new MultiCases();
        String sheetName="Smoke_Suite";
        logger=report.createTest(testcase+"  "+mcs.getTestName(testcase,sheetName));
        logger.info("Test executing ===> "+ mcs.getTestName(testcase,sheetName));
        String testname=mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);

    }


    @DataProvider
    public Object[][] smokeDataToTest() throws Exception
    {
        //System.out.println("Data providing to test");
        log.info("Data providing to test");
        Object[][] obj=new Object[][]
                {
                        {"TC_1"},{"TC_2"},{"TC_3"},{"TC_4"},{"TC_5"},{"TC_6"},{"TC_7"}

                };
        return obj;
    }

    //======================================================================================================================
    @Test (dataProvider="smoke_JioCom_Data")
    public void smoke_JioCom(String testcase) throws InterruptedException
    {
        // System.out.println("Using data Provider");
        log.info("Using data Provider");
        MultiCases mcs=new MultiCases();
        String sheetName="Smoke_JioSIT";
        logger=report.createTest(testcase+"  "+mcs.getTestName(testcase,sheetName));
        logger.info("Test executing ===> "+ mcs.getTestName(testcase,sheetName));
        String testname=mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);

    }


    @DataProvider
    public Object[][] smoke_JioCom_Data() throws Exception
    {
        //System.out.println("Data providing to test");
        log.info("Data providing to test");
        Object[][] obj=new Object[][]
                {
                        // {"TC_1"},{"TC_2"},{"TC_3"},{"TC_4"},{"TC_5"},{"TC_6"},{"TC_7"},{"TC_8"},{"TC_9"},{"TC_10"},{"TC_11"},{"TC_12"}
                        {"TC_2"},
                };
        return obj;
    }
//======================================================================================================================


   /*@Test
    public void tc_1()
    {
        log.info("Executing tc_1");
        String testcase="TC_1";
        System.out.println("TC_1");
        logger=report.createTest(testcase);
        String sheetName="Payments";
        ExcelUtil eu=new ExcelUtil();
        MultiCases mcs=new MultiCases();
        String testname=mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);
        log.info("Executed tc_1");
    }

   /* @Test
    public void tc_2()
    {
        String testcase="TC_2";
        logger=report.createTest(testcase);
        String sheetName="Payments";
        MultiCases mcs=new MultiCases();
        String testname= mcs.ExecuteTest(testcase,sheetName);
        logger.info("Test executed success ===> "+testname);

    }
*/


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException
    {

        //System.out.println("After Method executing");
        log.info("After Method Executing");
        if(result.getStatus()==ITestResult.FAILURE)
        {

            logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(KeywordEngine.captureScreenshot()).build());
            logger.fail("Test Failed"+result.getThrowable());
            log.error("Test Failed"+result.getThrowable());
            KeywordEngine.closeBrowser();
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(KeywordEngine.captureScreenshot()).build());
            logger.pass("Test Passed");
            log.info("Test passed");

        }

        report.flush();

        if(keywordEngine.flagGetter().equals("YES"))
        {
            KeywordEngine.closeBrowser();
            KeywordEngine.flagSetter("NO");
            log.info("Setting back browserCloseFlag to No");
        }
        log.info("After Method Executed");

    }

}
