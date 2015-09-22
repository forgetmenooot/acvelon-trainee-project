package com.springapp.mvc.util.impl;

import com.springapp.mvc.bean.FilmBean;
import com.springapp.mvc.domain.Film;
import com.springapp.mvc.exception.ValidatorException;
import com.springapp.mvc.util.IConverter;
import com.springapp.mvc.util.IValidator;
import com.springapp.mvc.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Y. Vovk on 20.09.15.
 */
@Component
public class FilmValidator implements IValidator<Film, FilmBean> {

    private class FilmConverter implements IConverter<Film, FilmBean> {

        private final Logger logger = LoggerFactory.getLogger(FilmConverter.class);

        public Film convert(FilmBean bean) {
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
                logger.error(e.getMessage(), e);
            }

            film.setReview(bean.getReview());
            return film;
        }
    }

    public Film validate(FilmBean bean) {
        StringBuilder builder = new StringBuilder();

        if (Utils.isEmptyString(bean.getName())) {
            builder.append("Name can't be empty! ");
        } else if (!Utils.isValidNumberRange(bean.getName().length(), 1, 45)) {
            builder.append("Name can't be more than 45 symbols length! ");
        }

        if (Utils.isEmptyString(bean.getGenre())) {
            builder.append("Genre can't be empty! ");
        } else if (!Utils.isValidNumberRange(bean.getGenre().length(), 1, 45)) {
            builder.append("Genre can't be more than 45 symbols length! ");
        }

        if (!Utils.isInteger(bean.getMark()) ||
                !Utils.isValidNumberRange(Integer.parseInt(bean.getMark()), 1, 10)) {
            builder.append("Mark must be a number from 1 to 10! ");
        }

        if (!Utils.isInteger(bean.getMark()) ||
                !Utils.isValidNumberRange(Integer.parseInt(bean.getYear()), 1901, 2015)) {
            builder.append("Year must be a number from 1901 to 2015! ");
        }

        if (!Utils.isValidDate(bean.getDateSeen())) {
            builder.append("Date must be yyyy-MM-dd format!");
        } else if (!Utils.isValidNumberRange(bean.getName().length(), 0, 400)) {
            builder.append("Review can't be more than 400 symbols length! ");
        }

        String error = builder.toString();
        if (!error.isEmpty()) {
            throw new ValidatorException(error);
        }

        return new FilmConverter().convert(bean);
    }
}
