package com.simon.demorecyclerview.model;

/**
 * Created by simon on 2016/4/21.
 */
public class User {
    private int id;
    private String name;
    private int headerImage;

    public User() {

    }

    public User(int id, String name, int headerImage) {
        this.id = id;
        this.name = name;
        this.headerImage = headerImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(int headerImage) {
        this.headerImage = headerImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
