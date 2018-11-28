package cn.blmdz.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Data;



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
    
    public enum ExcelType {
        HSSFWorkbook("EXCEL2003; 65535行256列; 一般不会OOM;"),
        XSSFWorkbook("EXCEL2007; 1048576行16384列; 数据大会OOM;"),
        SXSSFWorkbook("EXCEL2007; 1048576行16384列; 根据DEFAULT_WINDOW_SIZE大小 超出条数将原来的持久化到硬盘解决OOM问题 默认100(基本默认即可);"),
        ;
        ExcelType(String description) {}
    }
    
    @Data
    public static class ExcelModel {
        private String fileName;
        private ExcelType excelType;
        private Boolean append = false;
        private String[] columnDescription;
        private String[] columnCode;
        private List<Map<String, String>> data;
    }
    
    public static void excelCreate(ExcelModel model) throws IOException {
        Workbook workbook = null;
        switch (model.getExcelType()) {
        case HSSFWorkbook:
            workbook = new HSSFWorkbook();
            break;
        case SXSSFWorkbook:
            workbook = new SXSSFWorkbook();
            break;
        case XSSFWorkbook:
            workbook = new XSSFWorkbook();
            break;
        }
        

        int s = model.getData().size() / 1000000 + (model.getData().size() % 1000000 > 0 ? 1 : 0);
        for (int i = 0; i < s; i++) {
            workbook.createSheet("sheet" + (i + 1));
        }
        String tmp = null;
        for (int i = 0; i < model.getData().size(); i++) {
            
            Sheet sheet = workbook.getSheetAt(i / 1000000);
            Row row = sheet.createRow(i % 1000000);

            for (int j = 0; j < model.getColumnDescription().length; j++) {
                if(i == 0) {
                    // 首行
                    tmp = model.getColumnDescription()[j];
                } else {
                    // 数据
                    tmp = model.getData().get(i).get(model.getColumnCode()[j]);
                }
//              row.createCell(j).setCellValue(tmp);
                CellUtil.createCell(row, j, tmp);
            }
        }

        FileOutputStream out = new FileOutputStream(model.getFileName(), true);
        workbook.write(out);
        if (out != null) out.close();
        if (workbook instanceof SXSSFWorkbook)((SXSSFWorkbook) workbook).dispose();
        if (workbook != null) workbook.close();
    }
    
    

    public static void main(String[] args) throws Exception {
        String[] columnDescription = {"阿斯", "蒂芬", "斯芬", "阿蒂"};
        String[] columnCode = {"a", "b", "c", "d"};
        List<Map<String, String>> lists = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 0; i < 100; i++) {
            map = new HashMap<>();
            map.put("a", String.valueOf(ThreadLocalRandom.current().nextDouble()));
            map.put("b", String.valueOf(ThreadLocalRandom.current().nextDouble()));
            map.put("c", String.valueOf(ThreadLocalRandom.current().nextDouble()));
            map.put("d", String.valueOf(ThreadLocalRandom.current().nextDouble()));
            lists.add(map);
        }
        ExcelModel model = new ExcelModel();
        model.setFileName("./excel.xlsx");
        model.setExcelType(ExcelType.XSSFWorkbook);
        model.setColumnDescription(columnDescription);
        model.setColumnCode(columnCode);
        model.setData(lists);
        excelCreate(model);

        
        excelRead("./excel.xlsx", ExcelType.SXSSFWorkbook);
        
    }
    
    public static void excelRead(String filePath, ExcelType type) throws IOException {

        
        FileInputStream is = new FileInputStream(new File(filePath));
        Workbook workbook = null;

        switch (type) {
        case HSSFWorkbook:
            workbook = new HSSFWorkbook(is);
        case SXSSFWorkbook:
            workbook = new SXSSFWorkbook(new XSSFWorkbook(is));
        case XSSFWorkbook:
            workbook = new XSSFWorkbook(is);
        }
        
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

}
