package com.imooc.homework.service;

import com.imooc.homework.data.Course;

import java.util.List;

public class CourseDaoImpl {
    /*
    * 此类中的方法完全调用CourseDao中的方法
    * */
    private static final CourseDao courseDao = new CourseDao();

    public static boolean isCourseExist(long id) {
        return courseDao.isCourseExist(id);
    }

    public static void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    public static List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public static List<Course> getCourses(String title, int size, int page) {
        return courseDao.getCourses(title, size, page);
    }
    public static int getCourseCount(String title){
        return courseDao.getCourseCount(title);
    }

    public static String addCourses(List<Course> courses) {
        return courseDao.addCourses(courses);
    }

    public static List<Course> getCourses(String title) {
        return courseDao.getCourses(title);
    }
}
