package com.hckim.listviewre.adapterview;

/**
 * 모델 클래스
 */

public class People {
    private String name;
    private String phone;
    private int picture;

    public People(String name, String phone, int picture) {
        this.name = name;
        this.phone = phone;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", picture=" + picture +
                '}';
    }
}
