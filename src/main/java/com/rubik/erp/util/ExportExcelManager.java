/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GRUCAS
 */
public class ExportExcelManager {
    
    InputStream fileStream = null;
    String titulo;
    String subtitulo;
    int[] positionCurrency;
    int[] positionNumbers;
   
    public ExportExcelManager(){
    }

    public ExportExcelManager(String titulo, String subtitulo) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }    

    public int[] getPositionCurrency() {
        return positionCurrency;
    }

    public void setPositionCurrency(int[] positionCurrency) {
        this.positionCurrency = positionCurrency;
    }

    public int[] getPositionNumbers() {
        return positionNumbers;
    }

    public void setPositionNumbers(int[] positionNumbers) {
        this.positionNumbers = positionNumbers;
    }

    public InputStream getExcelFromListData(String sheetName, ArrayList headers,
            ArrayList data) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(sheetName);
            
            // Titles
            int rowTitle = 0;
            int rowSubTitle = 1;
            
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size()-1));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, headers.size()-1));

            XSSFFont fontTitle= wb.createFont();
            fontTitle.setFontHeightInPoints((short)14);
            fontTitle.setFontName("Arial");
            fontTitle.setColor(IndexedColors.BLACK.getIndex());
            fontTitle.setBold(true);
            fontTitle.setItalic(false);
            
            XSSFFont fontSubTitle= wb.createFont();
            fontSubTitle.setFontHeightInPoints((short)12);
            fontSubTitle.setFontName("Arial");
            fontSubTitle.setColor(IndexedColors.BLACK.getIndex());
            fontSubTitle.setBold(false);
            fontSubTitle.setItalic(false);
            
            XSSFFont fontHeader = wb.createFont();
            fontHeader.setFontHeightInPoints((short)10);
            fontHeader.setFontName("Arial");
            fontHeader.setColor(IndexedColors.BLACK.getIndex());
            fontHeader.setBold(true);
            fontHeader.setItalic(false);
            
            XSSFRow hssfTitle = sheet.createRow(rowTitle);
            XSSFCellStyle cellStyleTitle = wb.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setFont(fontTitle);
            XSSFCell hssfCellTitle = hssfTitle.createCell(0);
            hssfCellTitle.setCellStyle(cellStyleTitle);
            hssfCellTitle.setCellValue(titulo);
            
            XSSFRow hssfSubTitle = sheet.createRow(rowSubTitle);
            XSSFCellStyle cellStyleSubTitle = wb.createCellStyle();
            cellStyleSubTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleSubTitle.setFont(fontSubTitle);
            XSSFCell hssfCellSubTitle = hssfSubTitle.createCell(0);
            hssfCellSubTitle.setCellStyle(cellStyleSubTitle);
            hssfCellSubTitle.setCellValue(subtitulo);
            
            // Header
            int rowIdx = 3;
            short cellIdx = 0;

            XSSFRow hssfHeader = sheet.createRow(rowIdx);
            XSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setFont(fontHeader);
            cellStyle.setFillBackgroundColor(new XSSFColor(Color.getHSBColor(0, 0, 70)));
            for (Iterator cells = headers.iterator(); cells.hasNext();) {
                XSSFCell hssfCell = hssfHeader.createCell(cellIdx++);
                hssfCell.setCellStyle(cellStyle);
                hssfCell.setCellValue((String) cells.next());
            }
            
            // Data
            int rowIdxData = 4;
            XSSFCellStyle cellStyleData = wb.createCellStyle();
            cellStyleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
            for (Iterator rows = data.iterator(); rows.hasNext();) {
                ArrayList row = (ArrayList) rows.next();
                XSSFRow hssfRow = sheet.createRow(rowIdxData++);
                int cellIdxData = 0;
                for (Iterator cells = row.iterator(); cells.hasNext();) {
                    
                    if(positionCurrency!=null){
                        for (int i = 0; i < positionCurrency.length; i++) {
                            if(cellIdxData == positionCurrency[i]){
                                cellStyleData.setDataFormat((short)7);  // 8 en Java 7. 
                                cellStyleData.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            }
                        }
                    }

                    XSSFCell hssfCell = hssfRow.createCell(cellIdxData++);
                    hssfCell.setCellValue((String) cells.next());
                    hssfCell.setCellStyle(cellStyleData);
                   
                    sheet.autoSizeColumn(cellIdxData);
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