package com.example.cv_mate;

public class adminModel {

    String studentUrl,studentName,studentUserUID;

    public adminModel(String studentUrl, String studentName, String studentUserUID) {
        this.studentUrl = studentUrl;
        this.studentName = studentName;
        this.studentUserUID = studentUserUID;
    }

    public String getStudentUrl() {
        return studentUrl;
    }

    public void setStudentUrl(String studentUrl) {
        this.studentUrl = studentUrl;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentUserUID() {
        return studentUserUID;
    }

    public void setStudentUserUID(String studentUserUID) {
        this.studentUserUID = studentUserUID;
    }
}
