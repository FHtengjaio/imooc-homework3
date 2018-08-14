package com.imooc.homework.servlet;

import com.imooc.homework.data.Course;
import com.imooc.homework.service.CourseDaoImpl;
import com.imooc.homework.utils.ExcelTool;
import com.imooc.homework.utils.RequestParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CourseImpExpServlet",urlPatterns = {"/CourseImport.do","/CourseExport.do"})
public class CourseImpExpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/CourseImport.do", request.getServletPath())) {
            FileItem fileItem = RequestParser.getExcel(request);
            if (fileItem != null) {
                List<Course> courses = null;
                try {
                    courses = ExcelTool.readExcel(fileItem, (String) request.getSession().getAttribute("LoginUser"));
                    String str = CourseDaoImpl.addCourses(courses);
                    request.setAttribute("msg", str);
                } catch (InvalidFormatException e) {
                    request.setAttribute("msg", "你传入的是excel吗？");
                }catch (IllegalStateException e) {
                    request.setAttribute("msg", "你传入的excel格式不对!");
                } finally {
                    request.getRequestDispatcher("/GetCourse.do").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "请选择xls文件");
                request.getRequestDispatcher("/WEB-INF/views/biz/courseImport.jsp").forward(request, response);
            }
        } else if (Objects.equals("/CourseExport.do", request.getServletPath())) {
            String title = request.getParameter("title");
            if (title != null) {
                List<Course> courses = CourseDaoImpl.getCourses(title);
                Workbook book = ExcelTool.writeExcel(courses);
                response.setHeader("Content-Disposition", "attachment;filename=export.xlsx");
                ServletOutputStream outputStream = response.getOutputStream();
                book.write(outputStream);
                book.close();
                outputStream.close();
            }
        }
    }
}
