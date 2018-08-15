package com.imooc.homework.servlet;

import com.imooc.homework.data.Course;
import com.imooc.homework.service.CourseDaoImpl;
import com.imooc.homework.utils.ExcelTool;
import com.imooc.homework.utils.RequestParser;
import net.sf.json.JSONObject;
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
            //只响应XMLHttpRequest的请求
            String ajaxHeader = request.getHeader("x-requested-with");
            if (ajaxHeader == null) {
                response.sendRedirect("/Home");
                return;
            }
            //获取request的FileItem对象
            FileItem fileItem = RequestParser.getExcel(request);

            JSONObject jsonObject = new JSONObject();
            String res = null;
            String message = null;
            if (fileItem != null) {
                List<Course> courses = null;
                try {
                    //解析文件
                    courses = ExcelTool.readExcel(fileItem, (String) request.getSession().getAttribute("LoginUser"));
                    message = CourseDaoImpl.addCourses(courses);
                    res = "success";
                } catch (InvalidFormatException e) {   //非excel文件
                    message = "您上传的是excel吗？";
                    res = "fail";
                } catch (IllegalStateException e) {    //excel文件单元格格式存在问题
                    message = "您上传的excel，单元格格式不匹配!";
                    res = "fail";
                } catch (Exception e){  //其他错误
                    message = "抱歉，您上传的文件不匹配!";
                    res = "fail";
                } finally {
                    //向前端返回数据
                    jsonObject.put("result", res);
                    jsonObject.put("msg", message);
                    response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                }
            } else {
                jsonObject.put("result", "fail");
                jsonObject.put("msg", "抱歉，您上传文件了吗？");
                response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
            }
        } else if (Objects.equals("/CourseExport.do", request.getServletPath())) {
            //获取用户搜索的关键字，通过关键字曹肇数据，进行Excel生成，下载
            String title = request.getParameter("title");
            if (title != null) {
                List<Course> courses = CourseDaoImpl.getCourses(title);  //获取课程
                Workbook book = ExcelTool.writeExcel(courses);           //生成excel
                response.setHeader("Content-Disposition", "attachment;filename=export.xlsx");
                ServletOutputStream outputStream = response.getOutputStream();
                book.write(outputStream);
                book.close();
                outputStream.close();
            } else {
                //页面重定向至主页
                response.sendRedirect("/Home");
            }
        }
    }
}
