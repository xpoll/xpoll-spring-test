package cn.blmdz.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * <pre>
 * HSSFWorkbook
 * EXCEL2003 -> *.xls -> 65535行256列
 * 一般不会OOM
 * 
 * XSSFWorkbook
 * EXCEL2007+ -> *.xlsx -> 1048576行16384列
 * 数据大会OOM
 * 
 * SXSSFWorkbook
 * EXCEL2007+ -> *.xlsx -> 1048576行16384列
 * new SXSSFWorkbook(DEFAULT_WINDOW_SIZE) 根据DEFAULT_WINDOW_SIZE大小 超出条数将原来的持久化到硬盘解决OOM问题 默认100(基本默认即可)
 */
public class ExcelPoi {
    
    private static void work(Workbook workbook, Integer num) {
        int s = num / 1000000 + (num % 1000000 > 0 ? 1 : 0)/* - workbook.getNumberOfSheets()*/;
        for (int i = 0; i < s; i++) {
            workbook.createSheet();
        }
        for (int i = 0; i < num; i++) {
            
            Sheet sheet = workbook.getSheetAt(i / 1000000);
            Row row = sheet.createRow(i % 1000000);
            for (int j = 0; j < 11; j++) {
                if(i == 0) {
                    // 首行
                    row.createCell(j).setCellValue("column" + j);
                } else {
                    // 数据
                    if (j == 0) {
                        CellUtil.createCell(row, j, String.valueOf(i));
                    } else
                        CellUtil.createCell(row, j, String.valueOf(Math.random()));
                }
            }
        }
    }

    public static void excelCreate(String filePath, Integer num, Workbook workbook) throws IOException {
        work(workbook, num);
        FileOutputStream out = new FileOutputStream(filePath, true);
        workbook.write(out);
        if (out != null) out.close();
        if (workbook instanceof SXSSFWorkbook)((SXSSFWorkbook) workbook).dispose();
        if (workbook != null) workbook.close();
    }


    public static void main(String[] args) throws Exception {
        Integer num = 100;
        long beginTime = System.currentTimeMillis();
        excelCreate("./excel1.xls", num, new HSSFWorkbook());
        System.out.println("excelHSSFWorkbook Cast time : " + (System.currentTimeMillis() - beginTime));
        beginTime = System.currentTimeMillis();
        excelCreate("./excel2.xlsx", num, new XSSFWorkbook());
        System.out.println("excelXSSFWorkbook Cast time : " + (System.currentTimeMillis() - beginTime));
        beginTime = System.currentTimeMillis();
        excelCreate("./excel3.xlsx", num, new SXSSFWorkbook());
        System.out.println("excelSXSSFWorkbook Cast time : " + (System.currentTimeMillis() - beginTime));
        
        excelRead("./excel3.xlsx", new GetWorkbook() {
            @Override
            public Workbook get(FileInputStream is) throws IOException {
                return new XSSFWorkbook(is);
            }
        });
        
    }
    
    public static void excelRead(String filePath, GetWorkbook getWorkbook) throws IOException {
        FileInputStream is = new FileInputStream(new File(filePath));
        Workbook workbook = getWorkbook.get(is);
        
        // Sheet
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            // Row
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                Row row = sheet.getRow(j);
                // Cell
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {

                    Cell cell = row.getCell(k);
                    String value = null;
                    switch (cell.getCellTypeEnum()) {
                    case _NONE:
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        NumberFormat nf = NumberFormat.getInstance();
                        value = nf.format(cell.getNumericCellValue()).replace(",", "");
                        break;
                    case FORMULA:
                        value = cell.getCellFormula();
                        break;
                    case BOOLEAN:
                        value = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case ERROR:
                    case BLANK:
                        value = "";
                        break;
                    
                    }
                    System.out.println(value);
                }
            }
        }
        
        if (is != null) is.close();
        if (workbook instanceof SXSSFWorkbook)((SXSSFWorkbook) workbook).dispose();
        if (workbook != null) workbook.close();
    }

    public interface GetWorkbook {
        Workbook get(FileInputStream is) throws IOException;
    }
}
