package com.springapp.mvc.bean;

import java.io.Serializable;

/**
 * Created by Y. Vovk on 20.09.15.
 */
public class FilmBean implements Serializable {

    private String id;
    private String name;
    private String genre;
    private String year;
    private String mark;
    private String dateSeen;
    private String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDateSeen() {
        return dateSeen;
    }

    public void setDateSeen(String dateSeen) {
        this.dateSeen = dateSeen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
