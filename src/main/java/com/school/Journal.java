package com.school;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "school-journal")
public class Journal {
    @Id
    private String id;
    
    private String student;
    private String clazz;
    private String teacher;
    private String subject;
    private String date;
    private String mark;
    private String teacherLevel;
    private String school;        
    private String address;
    private String phone;
    private String director;

    public Journal() {}

    public Journal(String student, String clazz, String teacher, String subject, String date, 
                   String mark, String teacherLevel, String school, String address, String phone, String director) {
        this.student = student;
        this.clazz = clazz;
        this.teacher = teacher;
        this.subject = subject;
        this.date = date;
        this.mark = mark;
        this.teacherLevel = teacherLevel;
        this.school = school;
        this.address = address;
        this.phone = phone;
        this.director = director;
    }


    @Override
    public String toString() {
        return "Journal {" +
                " id='" + id + '\'' +
                ", student='" + student + '\'' +
                ", clazz='" + clazz + '\'' +
                ", teacher='" + teacher + '\'' +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", mark='" + mark + '\'' +
                ", teacherLevel='" + teacherLevel + '\'' +
                ", school='" + school + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
    
    public String getStudent() { return student; }
    public String getClazz() { return clazz; }
    public String getTeacher() { return teacher; }
    public String getSubject() { return subject; }
    public String getDate() { return date; }
    public String getMark() { return mark; }
    public String getTeacherLevel() { return teacherLevel; }
    public String getSchool() { return school; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDirector() { return director; }
}