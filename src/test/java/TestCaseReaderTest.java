/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;

import com.safeway.app.appcert.smoketester.TestScriptTemplate;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author nbret00 Using Apache POI to read information from the excel file
 * containing test scripts
 *
 */
public class TestCaseReaderTest {


    public void testSimple() throws Exception {
        //public List<TestScriptTemplate> readExcel(){

        FileInputStream file = new FileInputStream(new File("C:\\Users\\nbret00\\Documents\\SeleniumSmokeTest\\TestCases.xlsx"));

        List<TestScriptTemplate> tstList = new ArrayList<TestScriptTemplate>();
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();

        List<TestScriptTemplate> TestScriptTemplateList = new ArrayList();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //skip until row 5
            if (row.getRowNum() > 4) {

                TestScriptTemplate tscripttemp = new TestScriptTemplate();

                //Cell appcode = row.getCell(0); //this should be the item # on the list
                //System.out.println("application name #: "+itemnum.getStringCellValue());
                tscripttemp.setAppCode(getCellValueStr(row.getCell(1)));
                tscripttemp.setAppURL(getCellValueStr(row.getCell(2)));
                tscripttemp.setAppUserID(getCellValueStr(row.getCell(3)));
                tscripttemp.setAppPassword(getCellValueStr(row.getCell(4)));
                tscripttemp.setHomePageTitle(getCellValueStr(row.getCell(5)));
                tscripttemp.setHomePageElement(getCellValueStr(row.getCell(6)));
                tscripttemp.setLevel1URL(getCellValueStr(row.getCell(7)));
                tscripttemp.setLevel1PageTitle(getCellValueStr(row.getCell(8)));
                tscripttemp.setLevel1Element(getCellValueStr(row.getCell(9)));
                
                //tscripttemp.setAppUserID(row.getCell(3).getStringCellValue());
                //tscripttemp.setAppPassword(row.getCell(3).getStringCellValue());
                TestScriptTemplateList.add(tscripttemp);
                System.out.println("this to string: " + tscripttemp.toString());
            }

        }
        file.close();
    }

    private String getCellValueStr(Cell cell) {
        if (cell.CELL_TYPE_STRING == 1) {
            String toret = null;
            try {
                toret = cell.getStringCellValue();
            } catch (NullPointerException npe) {
                return "";
            }

            return toret;
        } else {
            return "";
        }
    }

}
