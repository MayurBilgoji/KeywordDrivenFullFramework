package excelUtils;

import engine.KeywordEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiCases {
    public KeywordEngine keywordEngine;
    public static final Logger log = LogManager.getLogger(MultiCases.class.getName());

    public String ExecuteTest(String testCase,String sheetName)
    {
        ExcelUtil et=new ExcelUtil();
        int startRow = 0;
        int endRow = 0;
        for(int i=0;i<et.GetRowNumber(sheetName);i++)
        {
            if(testCase.equalsIgnoreCase(et.getCellValue(sheetName,0,i)))
            {
                startRow=i;
                //System.out.println(startRow);
                break;
            }

        }

        for(int j=startRow;j<et.GetRowNumber(sheetName);j++)
        {
            if(et.getCellValue(sheetName, 1, j+1).equalsIgnoreCase("TS_1")||et.getCellValue(sheetName, 1, j+1).equals("TESTEND"))
            {
                endRow=j;
                //System.out.println(endRow);
                break;
            }
        }
        //System.out.println(startRow);
        //System.out.println(endRow);
        log.info("Test case start row is "+startRow);
        log.info("Test case end row is "+endRow);
        keywordEngine=new KeywordEngine();
        String currentTestName=et.getCellValue(sheetName,2,startRow);
        //System.out.println(currentTestName);
        log.info("Test Case Name"+currentTestName);
        keywordEngine.startExecution(sheetName, startRow, endRow);

        return currentTestName;



    }

    public String getTestName(String testCase, String sheetName)
    {
        ExcelUtil et=new ExcelUtil();
        int startRow = 0;
        int endRow = 0;
        for(int i=0;i<et.GetRowNumber(sheetName);i++)
        {
            if(testCase.equalsIgnoreCase(et.getCellValue(sheetName,0,i)))
            {
                startRow=i;
                //System.out.println(startRow);
                break;
            }

        }
        keywordEngine=new KeywordEngine();
        String currentTestName=et.getCellValue(sheetName,2,startRow);
        return currentTestName;


    }

}
