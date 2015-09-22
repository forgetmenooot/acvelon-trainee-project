package com.springapp.mvc.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Entity
@Table
public class Film implements Serializable, Comparable<Film> {

    private static final String NAME_EMPTY = "Name can't be empty! ";
    private static final String GENRE_EMPTY = "Genre can't be empty! ";
    private static final String DATE_EMPTY = "Date can't be empty! ";
    private static final String GENRE_SIZE = "Genre can't be more than 45 symbols length! ";
    private static final String NAME_SIZE = "Name can't be more than 45 symbols length! ";
    private static final String MARK_SIZE = "Mark must be a number from 9 to 10! ";
    private static final String YEAR_SIZE = "Year must be from 1901 to 2015! ";
    private static final String REVIEW_SIZE = "Review can't be more than 400 symbols length! ";

    private static final int TEXT_MAX = 45;
    private static final int YEAR_MIN = 1901;
    private static final int YEAR_MAX = 2015;
    private static final int MARK_MIN = 1;
    private static final int MARK_MAX = 10;
    private static final int REVIEW_MAX = 400;

    @Id
    @Column
    @NotNull
    private Integer id;

    @Column
    @NotBlank(message = NAME_EMPTY)
    @Size(max = TEXT_MAX, message = NAME_SIZE)
    private String name;

    @Column
    @NotBlank(message = GENRE_EMPTY)
    @Size(max = TEXT_MAX, message = GENRE_SIZE)
    private String genre;

    @Column
    @NotNull(message = YEAR_SIZE)
    @Range(min = YEAR_MIN, max = YEAR_MAX, message = YEAR_SIZE)
    private Integer year;

    @Column
    @NotNull(message = MARK_SIZE)
    @Range(min = MARK_MIN, max = MARK_MAX, message = MARK_SIZE)
    private Integer mark;

    @Column(name = "date_seen")
    @NotNull(message = DATE_EMPTY)
    private Date dateSeen;

    @Column
    @Size(max = REVIEW_MAX, message = REVIEW_SIZE)
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
