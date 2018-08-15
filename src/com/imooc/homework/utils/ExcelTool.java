package com.imooc.homework.utils;

import com.imooc.homework.data.Course;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTool {
    /**
     * 解析excel文件
     * throws: IOException，InvalidFormatException
     * */
    public static List<Course> readExcel(FileItem fileItem, String operator) throws IOException, InvalidFormatException {
        List<Course> list = new ArrayList<>();  //用与保存解析出来的课程

        //开始解析
        Workbook book = WorkbookFactory.create(fileItem.getInputStream()); //读取Excel文件，会产生InvalidFormatException异常
        Sheet sheet = book.getSheetAt(0);  //开个第一个sheet
        int rows = sheet.getLastRowNum();    //获取行数
        for (int i = 1; i <= rows; i++) {    //获取每行的数据，会产生IllegalStateException异常
            Row row = sheet.getRow(i);
            long id = (long) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            String direction = row.getCell(2).getStringCellValue();
            String des = row.getCell(3).getStringCellValue();
            double time = row.getCell(4).getNumericCellValue();
            Course course = new Course(id, name, direction, des, time, operator);
            list.add(course);
        }
        return list;
    }

    /**
     * 生成excel，供用户下载
     * */
    public static Workbook writeExcel(List<Course> courses) {
        Workbook book = new XSSFWorkbook();   //2007版的excel
        Sheet sheet = book.createSheet();

        //设置首行的单元格格式
        CellStyle cellStyle = book.createCellStyle();
        Font font = book.createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        //创建首行，写入数据
        Row firstRow = sheet.createRow(0);
        String[] titles = {"课程ID","课程名称", "方向", "描述", "时长", "创建人"};
        for (int i = 0; i < 6; i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }

        //从第二行开始写入课程数据
        for (int i = 0; i < courses.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(courses.get(i).getId());
            row.createCell(1).setCellValue(courses.get(i).getName());
            row.createCell(2).setCellValue(courses.get(i).getDirection());
            row.createCell(3).setCellValue(courses.get(i).getDes());
            row.createCell(4).setCellValue(courses.get(i).getTime());
            row.createCell(5).setCellValue(courses.get(i).getOperator());
        }

        return book;
    }
}
