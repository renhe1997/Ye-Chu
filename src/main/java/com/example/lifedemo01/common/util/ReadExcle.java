package com.example.lifedemo01.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: RenHe
 * @Date: 2021/2/5 15:06
 */
@Slf4j
public class ReadExcle {

    /**
     * 读取Excle头表信息
     * @param filePath
     * @return
     * @throws Exception
     */
    public Map<String, String> readExcelHead(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }
        InputStream in = new FileInputStream(file);

        // 读取整个Excel
        XSSFWorkbook sheets = new XSSFWorkbook(in);
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        Map dataMap = new HashMap();
        // 消费者账户 账号:[15666266453]
        XSSFCell cell = sheetAt.getRow(1).getCell(0);
        if (cell == null) {
            log.debug("行为空");
        } else {
            String custTel = getCellValue(cell);
            custTel = custTel.substring(4).split("]")[0];
            dataMap.put("custTel", custTel);
        }
        // 开始时间、结束时间 起始日期:[2021-01-01 00:00:00]    终止日期:[2021-02-05 15:01:48]
        cell = sheetAt.getRow(2).getCell(0);
        if (cell == null) {
            log.debug("行为空");
        } else {
            String date = getCellValue(cell);
            String beginDate = date.substring(date.indexOf("[")+1,date.indexOf("]")).replace("-","").replace(":","").replace(" ","");
            String endDate = date.substring(date.lastIndexOf("[")+1,date.lastIndexOf("]")).replace("-","").replace(":","").replace(" ","");
            dataMap.put("beginDate", beginDate);
            dataMap.put("endDate", endDate);
        }
        /**
         * 共237笔记录
         * 已收入:4笔
         * 待收入:0笔
         * 已支出:188笔
         * 待支出:0笔
         * 导出时间:[2021-02-05 15:01:49]    用户:李人和
         */
        int sumRows = sheetAt.getPhysicalNumberOfRows();
        // 用户姓名
        cell = sheetAt.getRow(sumRows-1).getCell(0);
        if (cell == null) {
            log.debug("行为空");
        } else {
            String custName = getCellValue(cell);
            custName = custName.substring(custName.lastIndexOf(":")+1);
            dataMap.put("custName", custName);
        }
        // 已支出 已支出:188笔
        cell = sheetAt.getRow(sumRows-3).getCell(0);
        if (cell == null) {
            log.debug("行为空");
        } else {
            String sumSpending = getCellValue(cell);
            sumSpending = sumSpending.substring(sumSpending.indexOf(":")+1).replace("笔","");
            dataMap.put("sumSpending", sumSpending);
        }
        // 已收入 已收入:4笔
        cell = sheetAt.getRow(sumRows-5).getCell(0);
        if (cell == null) {
            log.debug("行为空");
        } else {
            String sumIncome = getCellValue(cell);
            sumIncome = sumIncome.substring(sumIncome.indexOf(":")+1).replace("笔","");
            dataMap.put("sumIncome", sumIncome);
        }
        return dataMap;
    }

    /**
     * 读取excel内容
     * <p>
     * 用户模式下：
     * 弊端：对于少量的数据可以，单数对于大量的数据，会造成内存占据过大，有时候会造成内存溢出
     * 建议修改成事件模式
     */
    public List<List<String>> readExcel(String filePath, int beginLine) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }
        InputStream in = new FileInputStream(file);

        // 读取整个Excel
        XSSFWorkbook sheets = new XSSFWorkbook(in);
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        List<List<String>> list = new ArrayList<>();
        // 循环获取每一行数据
        for (int i = beginLine; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            if (row == null || row.getCell(0).getStringCellValue().contains("---------------------------------")) {
                break;
            }
            List<String> rowList = new ArrayList<>();
            // 读取每一格内容
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                XSSFCell cell = row.getCell(index);
                if (null == cell) {
                    continue;
                }
                rowList.add(getCellValue(cell));
            }
            if (rowList.isEmpty()) {
                continue;
            }
            list.add(rowList);
        }
        return list;
    }

    /**
     * 把单元格的内容转为字符串
     *
     * @param xssfCell 单元格
     * @return String
     */
    public static String getString(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else {
            return xssfCell.getStringCellValue();
        }
    }

    public String getCellValue(XSSFCell cell) {
        String cellValue = "";
        // 以下是判断数据的类型
        // case中使用的的必须是常量，也就是要使用进行判断的值要是常量，要进行final修饰才可以；
        int cellType = cell.getCellType();
        // 数字
        if (cellType == CellType.NUMERIC.getCode()) {
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
            } else {
                DataFormatter dataFormatter = new DataFormatter();
                cellValue = dataFormatter.formatCellValue(cell);
            }
            // 字符串
        } else if (cellType == CellType.STRING.getCode()) {
            cellValue = cell.getStringCellValue();
            // Boolean
        } else if (cellType == CellType.BOOLEAN.getCode()) {
            cellValue = cell.getBooleanCellValue() + "";
            // 公式
        } else if (cellType == CellType.FORMULA.getCode()) {
            cellValue = cell.getCellFormula() + "";
            // 空值
        } else if (cellType == CellType.BLANK.getCode()) {
            cellValue = "";
            // 故障
        } else if (cellType == CellType.ERROR.getCode()) {
            cellValue = "非法字符";
        } else {
            cellValue = "未知类型";
        }
        return cellValue;
    }
}
