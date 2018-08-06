package com.imooc.homework.servlet;

import com.imooc.homework.data.Course;
import com.imooc.homework.service.CourseDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CourseServlet", urlPatterns = {"/AddCourse", "/GetCourse"})
public class CourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/AddCourse", request.getServletPath())) {
            String courseId = request.getParameter("courseId");
            String courseName = request.getParameter("courseName");
            String courseType = request.getParameter("courseType");
            String description = request.getParameter("description");
            String courseTime = request.getParameter("courseTime");
            String operator = request.getParameter("operator");
            if (courseId != null && courseName != null && courseType != null
                    && description != null && courseTime != null && operator != null) {
                if (!CourseDaoImpl.isCourseExist(courseId)) {
                    Course course = new Course(courseId, courseName, courseType, description, Double.valueOf(courseTime), operator);
                    CourseDaoImpl.addCourse(course);
                    response.sendRedirect(request.getContextPath()+"/GetCourse");
                } else {
                    request.setAttribute("msg", "课程编号已经存在");
                    request.getRequestDispatcher("/WEB-INF/views/biz/addCourse.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "请填写所有字段");
                request.getRequestDispatcher("/WEB-INF/views/biz/addCourse.jsp").forward(request, response);
            }
        } else if(Objects.equals("/GetCourse", request.getServletPath())){
            int defaultSize = 5;
            int currentPage = 1;
            String size = request.getParameter("size");
            String title = request.getParameter("title");
            String page = request.getParameter("page");
            if (size != null && Objects.equals("", size)) {
                    defaultSize = Integer.valueOf(size);
            }
            if (page != null && Objects.equals("", page)) {
                currentPage = Integer.valueOf(page);
            }

            int searchedCount = CourseDaoImpl.getCourseCount(title);
            int totalPage = searchedCount % defaultSize > 0 ? searchedCount % defaultSize + 1 : searchedCount % defaultSize;
            List<Course> courses = CourseDaoImpl.getCourses(title, defaultSize, currentPage);

            request.setAttribute("searchedCount",searchedCount);
            request.setAttribute("currentPage",currentPage);
            request.setAttribute("totalCount", CourseDaoImpl.getAllCourses().size());
            request.setAttribute("totalPage", totalPage);

            request.setAttribute("allCourses", courses);
            request.getRequestDispatcher("/WEB-INF/views/biz/showCourse.jsp").forward(request, response);
        }
    }
}
