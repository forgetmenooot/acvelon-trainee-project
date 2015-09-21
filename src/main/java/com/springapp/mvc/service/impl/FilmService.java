package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Film;
import com.springapp.mvc.repository.IFilmRepository;
import com.springapp.mvc.service.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Service
@Transactional
public class FilmService implements IFilmService {

    @Autowired
    private IFilmRepository filmRepository;

    @Override
    public Film get(Integer id) {
        return filmRepository.get(id);
    }

    @Override
    public List<Film> getList() {
        List<Film> films = filmRepository.getList();
        Collections.sort(films);
        return films;
    }

    @Override
    public void add(Film entity) {
        filmRepository.add(entity);
    }

    @Override
    public void delete(Film entity) {
        filmRepository.delete(entity);
    }

    @Override
    public void update(Film entity) {
        filmRepository.update(entity);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            Film film = filmRepository.get(id);
            filmRepository.delete(film);
        }
    }
}
