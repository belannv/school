package com.college.model; 


public class ScheduleInfoDTO {


    private String studentName;
    private String className;
    private String subject;
    private String rating;
    private String teacherName;


    public ScheduleInfoDTO() {
    }


    public ScheduleInfoDTO(String studentName, String className, String subject, String rating, String teacherName) {
        this.studentName = studentName;
        this.className = className;
        this.subject = subject;
        this.rating = rating;
        this.teacherName = teacherName;
    }


    @Override
    public String toString() {
        return "StudentGrade{" +
                "student='" + studentName + '\'' +
                ", class='" + className + '\'' +
                ", subject='" + subject + '\'' +
                ", rating='" + rating + '\'' +
                ", teacher='" + teacherName + '\'' +
                '}';
    }

 
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}