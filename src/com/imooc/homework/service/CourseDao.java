package com.imooc.homework.service;

import com.imooc.homework.data.Course;

import java.util.*;

public class CourseDao {

    private static final Map<Long, Course> courseMap = new HashMap<>();

    static {
        courseMap.put(101L,new Course(101L,"java之JVM","java","很好1",300.00,"imooc"));
        courseMap.put(102L,new Course(102L,"html入门","前端","很好101",100.00,"imooc"));
        courseMap.put(103L,new Course(103L,"css入门","前端","很好3",200.50,"imooc"));
        courseMap.put(104L,new Course(104L,"javascript入门","前端","很好44",90.00,"imooc"));
        courseMap.put(105L,new Course(105L,"Linux入门","Linux","很好77",100.00,"imooc"));
        courseMap.put(106L,new Course(106L,"c#入门","C#","很好555",89.00,"imooc"));
        courseMap.put(107L,new Course(107L,"javaWeb","java","很好很好",230.50,"imooc"));
        courseMap.put(108L,new Course(108L,"java入门","java","很好的神色102",309.00,"imooc"));
        courseMap.put(109L,new Course(109L,"java内存","java","很好的双方各",320.00,"imooc"));
        courseMap.put(110L,new Course(110L,"java基础","java","很好34433",123.30,"imooc"));
        courseMap.put(111L,new Course(111L,"Linux脚本","Linux","很好 查询",334.00,"imooc"));
    }

    /**
     * 判断想要添加的课程id是否已经被占用
     * */
    public boolean isCourseExist(long id) {
        return courseMap.containsKey(id);
    }

    /**
     * 添加课程
     * */
    public void addCourse(Course course) {
        courseMap.put(course.getId(), course);
    }

    /**
     * 获取所有的课程
     * */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

    /**
     * 根据搜索的关键字来获取相关课程数
     * */
    public int getCourseCount(String title){
        List<Course> list = getCourses(title);
        return list.size();
    }

    /**
     * 通过搜索的关键字，页面展示的数据量大小，和页面序号来获取课程
     * */
    public List<Course> getCourses(String title, int size, int page) {

        List<Course> list = getCourses(title);

        int totalCourseCount = list.size();

        //计算回传数据的起止位子，用于返回list
        int start = (page - 1) * size > totalCourseCount ? 0 : (page - 1) * size;
        int end = totalCourseCount > page * size ? page * size : totalCourseCount;

        return list.subList(start, end);
    }

    /**
     * 关键字搜索课程
     * */
    public List<Course> getCourses(String title) {
        List<Course> list = new ArrayList<>();
        //如果关键字为null或者为空，这返回所有的课程
        if (Objects.equals("", title) || Objects.equals(null, title)) {
            list.addAll(courseMap.values());
        } else {
            //开始匹配关键字
            for (Course course : courseMap.values()) {
                if (Objects.equals(String.valueOf(course.getId()), title)) {
                    list.add(course);
                } else if (course.getName().contains(title)) {   //名称包含关键字
                    list.add(course);
                } else if (Objects.equals(course.getDirection(), title)) {
                    list.add(course);
                } else if (Objects.equals(course.getOperator(), title)) {  //描述包含关键字
                    list.add(course);
                } else if (course.getDes().contains(title)) {
                    list.add(course);
                } else if (Objects.equals(String.valueOf(course.getTime()), title)) {
                    list.add(course);
                }
            }
        }
        return list;
    }

    /**
     * Excel批量导入课程
     * */
    public String addCourses(List<Course> courses) {
        StringBuilder sb = new StringBuilder();
        for (Course course : courses) {
            if (!isCourseExist(course.getId())) {
                addCourse(course);
            } else {
                //课程已经存在就将课程编号加进sb
                sb.append(course.getId()).append(",");
            }
        }
        //通过sb的长度来判断是否存在未导入的习题
        if (sb.length() == 0) {
            sb.append("全部课程已经导入");
        } else {
            sb.append("没有导入，请修改id后再导入");
        }
        return sb.toString();
    }
}
