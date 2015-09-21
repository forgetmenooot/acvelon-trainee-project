package com.springapp.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Entity
@Table
public class Film implements Serializable, Comparable<Film> {

    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String genre;

    @Column
    private Integer year;

    @Column
    private Integer mark;

    @Column(name = "date_seen")
    private Date dateSeen;

    @Column
    private String review;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDateSeen() {
        return dateSeen;
    }

    public void setDateSeen(Date dateSeen) {
        this.dateSeen = dateSeen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Film film = (Film) o;

        return id.equals(film.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Film {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", mark=" + mark +
                ", dateSeen=" + dateSeen +
                ", review='" + review + '\'' +
                '}';
    }

    @Override
    public int compareTo(Film film) {
        return this.getName().compareTo(film.getName());
    }
}
