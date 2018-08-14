package com.imooc.homework.servlet;

import com.imooc.homework.data.Course;
import com.imooc.homework.service.CourseDaoImpl;
import com.imooc.homework.utils.RegexUtil;
import com.imooc.homework.utils.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CourseServlet", urlPatterns = {"/AddCourse.do", "/GetCourse.do"})
public class CourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/AddCourse.do", request.getServletPath())) {
            String courseId = request.getParameter("courseId");
            String courseName = request.getParameter("courseName");
            String courseType = request.getParameter("courseType");
            String description = request.getParameter("description");
            String courseTime = request.getParameter("courseTime");
            //操作人需要跟登录人一致，页面提交的数据有可能被篡改
            String operator = (String) request.getSession().getAttribute("LoginUser");
            if (courseId != null && courseName != null && courseType != null
                    && description != null && courseTime != null && operator != null
                    && !Objects.equals(courseId, "") && !Objects.equals(courseName, "")
                    && !Objects.equals(courseType, "") && !Objects.equals(description, "")
                    && !Objects.equals(courseTime, "") && !Objects.equals(operator, "")) {
                if (RegexUtil.isCourseIdRight(courseId) && RegexUtil.isTimeRight(courseTime)) {
                    if (!CourseDaoImpl.isCourseExist(Long.valueOf(courseId))) {
                        Course course = new Course(Long.valueOf(courseId), StringUtil.trim(courseName), StringUtil.trim(courseType),
                                StringUtil.trim(description), Double.valueOf(courseTime), StringUtil.trim(operator));
                        CourseDaoImpl.addCourse(course);
                        response.sendRedirect(request.getContextPath() + "/GetCourse.do");
                    } else {
                        request.setAttribute("msg", "课程编号已经存在");
                        request.getRequestDispatcher("/WEB-INF/views/biz/addCourse.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("msg", "请填写格式正确的id和时长");
                    request.getRequestDispatcher("/WEB-INF/views/biz/addCourse.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "请填写所有字段");
                request.getRequestDispatcher("/WEB-INF/views/biz/addCourse.jsp").forward(request, response);
            }
        } else if(Objects.equals("/GetCourse.do", request.getServletPath())){
            int defaultSize = 5;
            int currentPage = 1;
            String searchTitle = "";
            String size = request.getParameter("size");
            String title = request.getParameter("title");
            String page = request.getParameter("page");
            if (size != null && !Objects.equals("", size)) {
                    defaultSize = Integer.valueOf(size);
            }
            if (page != null && !Objects.equals("", page)) {
                currentPage = Integer.valueOf(page);
            }
            if (title != null) {
                searchTitle = title;
            }

            int searchedCount = CourseDaoImpl.getCourseCount(searchTitle);
            int totalPage = searchedCount % defaultSize > 0 ? searchedCount / defaultSize + 1 : searchedCount / defaultSize;
            List<Course> courses = CourseDaoImpl.getCourses(searchTitle, defaultSize, currentPage);

            String ajaxHeader = request.getHeader("x-requested-with");
            if (ajaxHeader != null && Objects.equals(ajaxHeader.toLowerCase(), "XMLHttpRequest".toLowerCase())) {
                List<Map<String,String>> courseJSON = new ArrayList<>();
                for (Course c : courses) {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", c.getId()+"");
                    map.put("name", c.getName());
                    map.put("direction", c.getDirection());
                    map.put("des", c.getDes());
                    map.put("time", c.getTime()+"");
                    map.put("operator", c.getOperator());
                    courseJSON.add(map);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("allCourses", courseJSON);
                jsonObject.put("searchedCount", searchedCount);
                jsonObject.put("totalCount", CourseDaoImpl.getAllCourses().size());
                jsonObject.put("totalPage", totalPage);
                System.out.println(jsonObject.toString());
                response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
            } else {
                request.setAttribute("msg", request.getAttribute("msg"));  //导入课程时才会有此message
                request.setAttribute("searchedCount",searchedCount);    //设置所有关键字符合的数量
                request.setAttribute("totalCount", CourseDaoImpl.getAllCourses().size());
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("allCourses", courses);
                request.getRequestDispatcher("/WEB-INF/views/biz/showCourse.jsp").forward(request, response);
            }

        }
    }
}
