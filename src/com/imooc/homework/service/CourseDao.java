package com.imooc.homework.service;

import com.imooc.homework.data.Course;

import java.util.*;

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

    public int getCourseCount(String title){
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
                } else {
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

        return list.size();
    }

    public List<Course> getCourses(String title, int size, int page) {
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
                } else {
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

        int totalCourseCount = list.size();
        int totalPage = totalCourseCount % size > 0 ? totalCourseCount % size + 1 : totalCourseCount % size;
        int start = (page - 1) * size;
        int end = totalCourseCount > page * size ? page * size : totalCourseCount;

        return list.subList(start, end);
    }

}
