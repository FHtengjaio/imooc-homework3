package com.imooc.homework.service;

import com.imooc.homework.data.Course;

import java.util.*;

public class CourseDao {

    private static final Map<String, Course> courseMap = new HashMap<>();

    static {
        courseMap.put("101",new Course("101","java之JVM","java","很好1",300.00,"imooc"));
        courseMap.put("102",new Course("102","html入门","前端","很好2",100.00,"imooc"));
        courseMap.put("103",new Course("103","css入门","前端","很好3",200.00,"imooc"));
        courseMap.put("104",new Course("104","javascript入门","前端","很好44",90.00,"imooc"));
        courseMap.put("105",new Course("105","Linux入门","Linux","很好77",100.00,"imooc"));
        courseMap.put("106",new Course("106","c#入门","C#","很好555",89.00,"imooc"));
        courseMap.put("107",new Course("107","javaWeb","java","很好很好",230.00,"imooc"));
        courseMap.put("108",new Course("108","java入门","java","很好的神色",309.00,"imooc"));
        courseMap.put("109",new Course("109","java内存","java","很好的双方各",320.00,"imooc"));
        courseMap.put("110",new Course("110","java基础","java","很好34433",123.00,"imooc"));
        courseMap.put("111",new Course("111","Linux脚本","Linux","很好 查询",334.00,"imooc"));
    }


    public boolean isCourseExist(String id) {
        return courseMap.containsKey(id);
    }

    public void addCourse(Course course) {
        courseMap.put(course.getId(), course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

    public int getCourseCount(String title){
        List<Course> list = getCourses(title);
        return list.size();
    }

    public List<Course> getCourses(String title, int size, int page) {

        List<Course> list = getCourses(title);

        int totalCourseCount = list.size();
        int start = (page - 1) * size > totalCourseCount ? 0 : (page - 1) * size;
        int end = totalCourseCount > page * size ? page * size : totalCourseCount;

        return list.subList(start, end);
    }

    public List<Course> getCourses(String title) {
        List<Course> list = new ArrayList<>();
        if (Objects.equals("", title) || Objects.equals(null, title)) {
            list.addAll(courseMap.values());
        } else {
            for (Course course : courseMap.values()) {
                if (Objects.equals(course.getId(), title)) {
                    list.add(course);
                    break;
                } else if (course.getName().contains(title)) {
                    list.add(course);
                } else if (Objects.equals(course.getDirection(), title)) {
                    list.add(course);
                } else if (Objects.equals(course.getOperator(), title)) {
                    list.add(course);
                } else if (course.getDes().contains(title)) {
                    list.add(course);
                }  else {
                    try {
                        double time = Double.valueOf(title);
                        if (time == course.getTime()) {
                            list.add(course);
                        }
                    } catch (NumberFormatException ignored) {
                    }

                }
            }
        }
        return list;
    }

    public String addCourses(List<Course> courses) {
        StringBuilder sb = new StringBuilder();
        for (Course course : courses) {
            if (!isCourseExist(course.getId())) {
                addCourse(course);
            } else {
                sb.append(course.getId()).append(",");
            }
        }
        if (sb.length() == 0) {
            sb.append("全部课程已经导入");
        } else {
            sb.append("没有导入，请修改id后再导入");
        }
        return sb.toString();
    }
}
