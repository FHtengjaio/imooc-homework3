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
    public static List<Course> readExcel(FileItem fileItem, String operator) {
        List<Course> list = new ArrayList<>();
        try {
            Workbook book = WorkbookFactory.create(fileItem.getInputStream());
            Sheet sheet = book.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            for (int i = 1; i <= rows; i++) {
                Row row = sheet.getRow(i);
                String id = (int)row.getCell(0).getNumericCellValue()+"";
                String name = row.getCell(1).getStringCellValue();
                String direction = row.getCell(2).getStringCellValue();
                String des = row.getCell(3).getStringCellValue();
                double time = row.getCell(4).getNumericCellValue();
                Course course = new Course(id, name, direction, des, time, operator);
                list.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Workbook writeExcel(List<Course> courses) {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet();
        CellStyle cellStyle = book.createCellStyle();
        Font font = book.createFont();
        cellStyle.setFont(font);
        font.setBold(true);
        Row firstRow = sheet.createRow(0);
        String[] titles = {"课程ID","课程名称", "方向", "描述", "时长", "创建人"};
        for (int i = 0; i < 6; i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }
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
