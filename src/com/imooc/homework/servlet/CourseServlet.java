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

            String headerContent = request.getHeader("x-requested-with");
            //判断请求是否为ajax请求，登录采用ajax请求，所以只响应ajax请求
            if (headerContent != null && Objects.equals(headerContent, "XMLHttpRequest")) {
                JSONObject jsonObject = new JSONObject();
                //判断表单数据是否为空或null
                if (courseId != null && courseName != null && courseType != null
                        && description != null && courseTime != null && operator != null
                        && !Objects.equals(courseId, "") && !Objects.equals(courseName, "")
                        && !Objects.equals(courseType, "") && !Objects.equals(description, "")
                        && !Objects.equals(courseTime, "") && !Objects.equals(operator, "")) {
                    //正则校验
                    if (RegexUtil.isCourseIdRight(courseId) && RegexUtil.isTimeRight(courseTime)) {
                        //去除前后空格
                        courseName = StringUtil.trim(courseName);
                        courseType = StringUtil.trim(courseType);
                        description = StringUtil.trim(description);

                        //判断提交的courseId是否已经被占用
                        if (!CourseDaoImpl.isCourseExist(Long.valueOf(courseId))) {
                            //没有被占用就添加课程，并向前端返回数据
                            Course course = new Course(Long.valueOf(courseId), StringUtil.trim(courseName), StringUtil.trim(courseType),
                                    StringUtil.trim(description), Double.valueOf(courseTime), StringUtil.trim(operator));
                            CourseDaoImpl.addCourse(course);
                            jsonObject.put("msg", null);
                            jsonObject.put("result", "success");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        } else {
                            // 向前端返回数据
                            jsonObject.put("msg", "课程编号已经存在");
                            jsonObject.put("result", "fail");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        }
                    } else {
                        // 向前端返回数据
                        jsonObject.put("msg", "请填写格式正确的id和时长");
                        jsonObject.put("result", "fail");
                        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                    }
                } else {
                    // 向前端返回数据
                    jsonObject.put("msg", "请填写所有字段");
                    jsonObject.put("result", "fail");
                    response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                }
            } else {
                //不是ajax请求就重定向页面到主页
                response.sendRedirect("/Home");
            }
        } else if(Objects.equals("/GetCourse.do", request.getServletPath())){
            //初始化参数
            int defaultSize = 5;
            int currentPage = 1;
            String searchTitle = "";

            //获取表单参数，如果参数存在就重新赋值
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

            //计算需要返回的参数值
            int searchedCount = CourseDaoImpl.getCourseCount(searchTitle);   //查询到的数据总数
            int totalPage = searchedCount % defaultSize > 0 ? searchedCount / defaultSize + 1 : searchedCount / defaultSize;  //页面数
            List<Course> courses = CourseDaoImpl.getCourses(searchTitle, defaultSize, currentPage);  //查询到的数据

            String ajaxHeader = request.getHeader("x-requested-with");
            //如果是ajax请求就返回JSON数据
            if (ajaxHeader != null && Objects.equals(ajaxHeader.toLowerCase(), "XMLHttpRequest".toLowerCase())) {
                //此处重新构造course的list，主要是在测试中发现，double类型的courseTime精度有损失
                //如120.0，json返回120，没有想到其他好的方法，老师有没有其他可行的方法？
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
                //构造json对象
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("allCourses", courseJSON);
                jsonObject.put("searchedCount", searchedCount);
                jsonObject.put("totalCount", CourseDaoImpl.getAllCourses().size());
                jsonObject.put("totalPage", totalPage);
                System.out.println(jsonObject.toString());
                response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
            } else {
                //正常的浏览器请求
                request.setAttribute("searchedCount",searchedCount);    //设置所有关键字符合的数量
                request.setAttribute("totalCount", CourseDaoImpl.getAllCourses().size());
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("allCourses", courses);
                request.getRequestDispatcher("/WEB-INF/views/biz/showCourse.jsp").forward(request, response);
            }

        }
    }
}
