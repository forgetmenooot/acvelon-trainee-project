package com.springapp.mvc.repository.impl;

import com.springapp.mvc.domain.Film;
import com.springapp.mvc.repository.AbstractRepository;
import com.springapp.mvc.repository.IFilmRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Repository
public class FilmRepository extends AbstractRepository<Film> implements IFilmRepository {

    public FilmRepository() {
        super(Film.class);
    }
}
