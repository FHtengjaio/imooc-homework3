package com.imooc.homework.service;

import com.imooc.homework.data.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDao {

    private static final Map<String, Course> courseMap = new HashMap<>();


    public boolean isCourseExist(String id) {
        return courseMap.containsKey(id);
    }

    public void addCourse(Course course) {
        courseMap.put(course.getId(), course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

}
