package com.springapp.mvc.service;

import com.springapp.mvc.domain.Film;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IFilmService extends IService<Film> {

    void deleteAll(Integer[] ids);
}
