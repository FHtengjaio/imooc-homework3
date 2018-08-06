package com.imooc.homework.service;

import com.imooc.homework.data.Course;

import java.util.List;

public class CourseDaoImpl {
    private static final CourseDao courseDao = new CourseDao();

    public static boolean isCourseExist(String id) {
        return courseDao.isCourseExist(id);
    }

    public static void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    public static List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }
}
