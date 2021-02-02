package com.atguigu.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.IOException;
import java.io.*;

/**
 * @author: 钱恩强
 * @date: 2021/1/26 13:54
 */
public class TestPoi {
    @Test
    public void readExcel() throws IOException{
        XSSFWorkbook workbook=new XSSFWorkbook("D:\\hello.xlsx");
        XSSFSheet sheet=workbook.getSheetAt(0);
        for(Row row:sheet){
            for(Cell cell:row){
                String value=cell.getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }
    @Test
    public void exportExcel_lastRow() throws IOException{
        XSSFWorkbook workbook=new XSSFWorkbook("D:\\hello.xlsx");
        XSSFSheet sheet= workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for(int i=0;i<lastRowNum;i++){
            XSSFRow row=sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for(short j=0;j<lastCellNum;j++){
                String stringCellValue = row.getCell(j).getStringCellValue();
                System.out.println(stringCellValue);
            }
        }
        workbook.close();
    }
    @Test
    public void importExcel() throws IOException{
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet("尚硅谷");
        XSSFRow row=sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("年龄");
        XSSFRow row1=sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");
        XSSFRow row2=sheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("小王");
        row2.createCell(2).setCellValue("20");
        FileOutputStream out = new FileOutputStream("D:\\atguigu.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
    }
}
