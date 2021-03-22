package com.example.datacrudmysql;

public class Post {

    private String pic;
    private String name;
    private  String email;
    private String gender;


    public Post(String pic, String name, String email, String gender) {
        this.pic = pic;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

