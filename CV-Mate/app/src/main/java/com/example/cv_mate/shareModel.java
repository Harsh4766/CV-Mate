package com.example.cv_mate;

public class shareModel {

    String admin_name,imageUrl,Email,userUid;

    public shareModel(String admin_name, String imageUrl, String email) {
        this.admin_name = admin_name;
        this.imageUrl = imageUrl;
        this.Email = email;
    }

    public shareModel(String userUid) {
        this.userUid = userUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
