package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Film;
import com.springapp.mvc.exception.ValidationException;
import com.springapp.mvc.service.IFilmService;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Film> getAll() {
        return filmService.getAll();
    }

    @RequestMapping(value = "film/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(@RequestBody Integer[] ids) {
        filmService.deleteAll(ids);
    }

    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Film get(@PathVariable Integer id) {
        return filmService.get(id);
    }

    @RequestMapping(value = "/film/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Film film) {
        filmService.update(film);
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody Film film) {
        film.setId(UUID.randomUUID().hashCode());
        filmService.add(film);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleException(ValidationException exc) {
        return exc.getMessage();
    }

}
