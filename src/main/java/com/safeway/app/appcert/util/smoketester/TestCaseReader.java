package com.safeway.app.appcert.util.smoketester;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.safeway.app.appcert.smoketester.TestScriptTemplate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class TestCaseReader {

    public List<TestScriptTemplate> readExcel() throws Exception {

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
                tscripttemp.setHomePageElementType(getCellValueStr(row.getCell(6)));
                tscripttemp.setHomePageElement(getCellValueStr(row.getCell(7)));
                tscripttemp.setLevel1URL(getCellValueStr(row.getCell(8)));
                tscripttemp.setLevel1PageTitle(getCellValueStr(row.getCell(9)));
                tscripttemp.setLevel1ElementType(getCellValueStr(row.getCell(10)));
                tscripttemp.setLevel1Element(getCellValueStr(row.getCell(11)));

                TestScriptTemplateList.add(tscripttemp);
                System.out.println("this to string: " + tscripttemp.toString());
            }

        }
        file.close();
        return TestScriptTemplateList;
    }

    public void writetoExcel(List<TestScriptTemplate> testcases) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Smoke Test Result");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        //create header first
        Row rowhead = sheet.createRow(2);
        rowhead.createCell(0).setCellValue("Application Code");
        rowhead.createCell(1).setCellValue("Test Case #1: Login to application");
        rowhead.createCell(2).setCellValue("Test Case #2: Verify Home Page title and element");
        rowhead.createCell(3).setCellValue("Test Case #3: Verify next page title and element");
        rowhead.createCell(4).setCellValue("Date and Time Log");
        rowhead.createCell(5).setCellValue("Test Execution Log");

        Iterator i = testcases.iterator();
        int rownum = 3;
        while (i.hasNext()) {
            TestScriptTemplate testresult = (TestScriptTemplate) i.next();
            Row row = sheet.createRow(rownum);
            rownum++;

            Date date = new Date();
            
            row.createCell(0).setCellValue(testresult.getAppCode());
            row.createCell(1).setCellValue(testresult.getTc1_resultSummary());
            row.createCell(2).setCellValue(testresult.getTc2_resultSummary());
            row.createCell(3).setCellValue(testresult.getTc3_resultSummary());

            //row.createCell(4).getCellStyle().setWrapText(true);
            row.createCell(4).setCellValue(dateFormat.format(date));
            
            row.createCell(5).getCellStyle().setWrapText(true);
            row.createCell(5).setCellValue(testresult.getLogs());
            
            
        }

        try {
            Date date = new Date();
            DateFormat dateFormatFile1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            FileOutputStream out
                    = new FileOutputStream(new File("C:\\Nino\\SmokeTest_"+dateFormatFile1.format(date)+".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
