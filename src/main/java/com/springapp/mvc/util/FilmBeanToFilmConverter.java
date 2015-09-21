package com.springapp.mvc.util;

import com.springapp.mvc.bean.FilmBean;
import com.springapp.mvc.domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Y. Vovk on 21.09.15.
 */
public class FilmBeanToFilmConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmBeanToFilmConverter.class);

    public static Film converter(FilmBean bean) {
        Film film = new Film();

        film.setId((Integer.parseInt(bean.getId())));
        film.setName(bean.getName());
        film.setGenre(bean.getGenre());
        film.setMark(Integer.parseInt(bean.getMark()));
        film.setYear(Integer.parseInt(bean.getYear()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(bean.getDateSeen());
            film.setDateSeen(new java.sql.Date(date.getTime()));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }

        film.setReview(bean.getReview());
        return film;
    }
}
