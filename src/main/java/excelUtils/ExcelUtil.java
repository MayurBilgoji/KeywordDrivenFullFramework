package excelUtils;

import base.Base;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ExcelUtil {

    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public Base base;
    public Properties prop;
    //public String scenarios="/Users/mayur.bilgoji/IdeaProjects/JPGJio/src/main/java/scenarios/JPG_TestCases.xlsx";
    public String scenarios;

    public int GetRowNumber(String sheetName)
    {
        base=new Base();
        prop=base.init_properties();
        scenarios=prop.getProperty("scenarios");

        FileInputStream fiss=null;
        try {
            // fiss=new FileInputStream("/Users/mayur.bilgoji/IdeaProjects/JPGJio/src/main/java/scenarios/JPG_TestCases.xlsx");
            fiss=new FileInputStream(scenarios);
            // fiss=new FileInputStream(prop.getProperty("scenarios"));

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
        int lastRow=sheet.getLastRowNum();
        return lastRow;

    }

    public String getCellValue(String sheetName,int col,int row)
    {

        FileInputStream fiss=null;
        try {
            //fiss=new FileInputStream("/Users/mayur.bilgoji/IdeaProjects/JPGJio/src/main/java/scenarios/JPG_TestCases.xlsx");
            //  fiss=new FileInputStream(prop.getProperty("scenarios"));
            base=new Base();
            prop=base.init_properties();
            scenarios=prop.getProperty("scenarios");
            fiss=new FileInputStream(scenarios);
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {

            workbook = new XSSFWorkbook(fiss);

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sheet=workbook.getSheet(sheetName);
        int lastRow=sheet.getLastRowNum();
        XSSFCell cell=sheet.getRow(row).getCell(col);
        if(cell!=null)
        {
            String cellValue=cell.toString().trim();
            return cellValue;
        }
        else
            return "NA";
    }

}
