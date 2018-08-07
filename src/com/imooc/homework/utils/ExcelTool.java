package com.imooc.homework.utils;

import com.imooc.homework.data.Course;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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
}
