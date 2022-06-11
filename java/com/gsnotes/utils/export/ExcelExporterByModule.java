package com.gsnotes.utils.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

public class ExcelExporterByModule {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<String> columnNames;
    private String[][] data;
    private String sheetName = "";

    private String session;
    private String nomModule;
    private String annee;
    private String enseignantName;
    private String semestre;
    private String Classe;
    private int numberOfElement;


    public ExcelExporterByModule(List<String> columnNames, String[][] data, String sheetName, String session, String nomModule, String annee, String enseignantName, String semestre, String Classe,int numberOfElement) {
        this.columnNames = columnNames;
        this.data = data;
        this.sheetName = sheetName;
        this.session = session;
        this.semestre = semestre;
        this.nomModule = nomModule;
        this.annee = annee;
        this.enseignantName = enseignantName;
        this.Classe = Classe;
        workbook = new XSSFWorkbook();
        this.numberOfElement = numberOfElement;


    }

    private void writeInformationLine() {
        sheet = workbook.createSheet(sheetName);


        Row row1 = sheet.createRow(0);

        row1.setHeight((short) 1000);

        //Style Titre
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);

        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);


        //style.setWrapText(true);
        //style Normal

        CellStyle style1 = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();

        font1.setFontHeight(11);
        style1.setFont(font1);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.TOP);


        //the cells for the first row
        createCell(row1, 0, "Module", style);
        createCell(row1, 1, nomModule, style1);
        createCell(row1, 2, "Semestre", style);
        createCell(row1, 3, semestre, style1);
        createCell(row1, 4, "Année", style);
        createCell(row1, 5, annee, style1);


        Row row2 = sheet.createRow(1);

        row2.setHeight((short) 776);
        createCell(row2, 0, "Enseignant", style);
        createCell(row2, 1, enseignantName, style1);
        createCell(row2, 2, "Session", style);
        createCell(row2, 3, session, style1);
        createCell(row2, 4, "Classe", style);
        createCell(row2, 5, Classe, style1);


    }

    private void writeHeaderLine() {


        Row row = sheet.createRow(3);

        row.setHeight((short) 776);


        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);

        font.setFontHeight(16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);


        int i = 0;
        for (String it : columnNames) {

            createCell(row, (i++), it, style);
        }

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        //sheet.autoSizeColumn(columnCount);
        sheet.setColumnWidth(columnCount, 4245);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);


        style.setWrapText(true);

        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 4;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        int count=5;
        for (int i = 0,k=4; i < data.length; i++,k++) {
            Row row = sheet.createRow(rowCount);
            int columnCount = 0;

            for (int j = 0; j < data[i].length; j++) {
                createCell(row, columnCount++, data[i][j], style);





            }
            if(numberOfElement>=2){
                Cell cell =row.createCell(6);
                sheet.getRow(k).getCell(6).setCellFormula("E"+count+"*0.5+F"+count+"*0.5");




                Cell cell1 = row.createCell(7);
                sheet.getRow(k).getCell(7).setCellFormula("IF(G"+count+"<12,\"R\",\"V\")");
                XSSFFormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateFormulaCell(cell);
                formulaEvaluator.evaluateFormulaCell(cell1);


            }else{
                Cell cell =row.createCell(5);
                sheet.getRow(k).getCell(5).setCellFormula("E"+count+"*1");




                Cell cell1 = row.createCell(6);
                sheet.getRow(k).getCell(6).setCellFormula("IF(F"+count+"<12,\"R\",\"V\")");
                XSSFFormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateFormulaCell(cell);
                formulaEvaluator.evaluateFormulaCell(cell1);

            }

            count++;
            rowCount++;


        }



    }

    private void setBorderToEmptyCell() {


        for (int i = 4; i < data.length + 4; i++) {
            for (int j = 4; j < columnNames.size(); j++) {
                CellRangeAddress region = new CellRangeAddress(i, i, j, j);
                RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);

            }
        }

    }

 

    public void export(HttpServletResponse response) throws IOException {
        writeInformationLine();
        writeHeaderLine();
        writeDataLines();
        setBorderToEmptyCell();
        //setFormula();



        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

    public void exportAllFiles() throws IOException{
      


    }

}
