package com.imooc.homework.data;

public class Course {
    private long id;
    private String name;
    private String direction;
    private String des;
    private double time;
    private String operator;

    public Course(long id, String name, String direction, String des, double time, String operator) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.des = des;
        this.time = time;
        this.operator = operator;
    }

    public Course() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", des='" + des + '\'' +
                ", time=" + time +
                ", operator='" + operator + '\'' +
                '}';
    }
}
