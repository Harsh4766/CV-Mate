package com.example.cv_mate;

public class shareModel {

    String studentUrl,FullName,Email;

    public shareModel(String studentUrl, String fullName, String email) {
        this.studentUrl = studentUrl;
        FullName = fullName;
        Email = email;
    }

    public String getStudentUrl() {
        return studentUrl;
    }

    public void setStudentUrl(String studentUrl) {
        this.studentUrl = studentUrl;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
