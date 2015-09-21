package com.springapp.mvc.controller;

import com.springapp.mvc.bean.FilmBean;
import com.springapp.mvc.domain.Film;
import com.springapp.mvc.exception.ValidatorException;
import com.springapp.mvc.service.IFilmService;
import com.springapp.mvc.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Controller
public class FilmController {

    @Autowired
    private IFilmService filmService;

    @Autowired
    private Validator filmValidator;

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Film> get() {
        return filmService.getList();
    }

    @RequestMapping(value = "film/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(@RequestBody Integer[] ids) {
        filmService.delete(ids);
    }

    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Film get(@PathVariable Integer id) {
        return filmService.get(id);
    }

    @RequestMapping(value = "/film/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody FilmBean filmBean) {
        Film film = filmValidator.validate(filmBean);
        filmService.update(film);
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody FilmBean filmBean) {
        Film film = filmValidator.validate(filmBean);
        film.setId(UUID.randomUUID().hashCode());
        filmService.add(film);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleException(ValidatorException exc) {
        return exc.getMessage();
    }

}
