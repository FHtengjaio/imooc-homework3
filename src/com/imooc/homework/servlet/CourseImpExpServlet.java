package com.imooc.homework.servlet;

import com.imooc.homework.data.Course;
import com.imooc.homework.service.CourseDaoImpl;
import com.imooc.homework.utils.ExcelTool;
import com.imooc.homework.utils.RequestParser;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CourseImpExpServlet",urlPatterns = {"/CourseImport","/CourseExport","/PrepareExport"})
public class CourseImpExpServlet extends HttpServlet {
    private String size;
    private String page;
    private String title;

    public void setSize(String size) {
        this.size = size;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/CourseImport", request.getServletPath())) {
            FileItem fileItem = RequestParser.getExcel(request);
            if (fileItem != null) {
                List<Course> courses = ExcelTool.readExcel(fileItem, (String) request.getSession().getAttribute("LoginUser"));
                String str = CourseDaoImpl.addCourses(courses);
                System.out.println(str);
                request.setAttribute("msg", str);
                request.getRequestDispatcher("/GetCourse").forward(request, response);
            } else {
                request.setAttribute("msg", "请选择xls文件");
                request.getRequestDispatcher("/WEB-INF/views/biz/courseImport.jsp").forward(request, response);
            }
        }
        //prepareExport(defaultSize,currentPage,searchTitle);
        if (Objects.equals("/PrepareExport", request.getServletPath())) {
            String size = request.getParameter("size");
            String page = request.getParameter("page");
            String title = request.getParameter("title");
            if (size != null && page != null && title != null) {
                setSize(size);
                setPage(page);
                setTitle(title);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/text; charset=utf-8");
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                    out.write("ok");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.close();
                    }
                }
            }
        }
    }
}
