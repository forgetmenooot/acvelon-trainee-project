package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Film;
import com.springapp.mvc.repository.IFilmRepository;
import com.springapp.mvc.service.IFilmService;
import com.springapp.mvc.validation.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Service
@Transactional
public class FilmService implements IFilmService {

    @Autowired
    private IFilmRepository filmRepository;

    @Autowired
    private IValidator<Film> filmValidator;

    @Override
    public Film get(Integer id) {
        return filmRepository.get(id);
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.getAll();
    }

    @Override
    public void add(Film entity) {
        filmValidator.validate(entity);
        filmRepository.add(entity);
    }

    @Override
    public void delete(Film entity) {
        filmRepository.delete(entity);
    }

    @Override
    public void update(Film entity) {
        filmValidator.validate(entity);
        filmRepository.update(entity);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            Film film = filmRepository.get(id);
            filmRepository.delete(film);
        }
    }
}
