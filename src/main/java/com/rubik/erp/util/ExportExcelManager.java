/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GRUCAS
 */
public class ExportExcelManager {
    
    InputStream fileStream = null;
   
    public ExportExcelManager(){
       
    }

    public InputStream getExcelFromListData(String sheetName, ArrayList headers,
            ArrayList data) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(sheetName);

            int rowIdx = 0;
            short cellIdx = 0;

            // Header
            XSSFRow hssfHeader = sheet.createRow(rowIdx);
            XSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            for (Iterator cells = headers.iterator(); cells.hasNext();) {
                XSSFCell hssfCell = hssfHeader.createCell(cellIdx++);
                hssfCell.setCellStyle(cellStyle);
                hssfCell.setCellValue((String) cells.next());
            }
            // Data
            rowIdx = 1;
            for (Iterator rows = data.iterator(); rows.hasNext();) {
                ArrayList row = (ArrayList) rows.next();
                XSSFRow hssfRow = sheet.createRow(rowIdx++);
                cellIdx = 0;
                for (Iterator cells = row.iterator(); cells.hasNext();) {
                    XSSFCell hssfCell = hssfRow.createCell(cellIdx++);
                    hssfCell.setCellValue((String) cells.next());
                    hssfCell.setCellStyle(cellStyle);
                   
                    sheet.autoSizeColumn(cellIdx);
                }
            }

            wb.setSheetName(0, sheetName);

            try {

                wb.write(baos);
                fileStream = new ByteArrayInputStream(baos.toByteArray());

                baos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileStream;
    }
   
}