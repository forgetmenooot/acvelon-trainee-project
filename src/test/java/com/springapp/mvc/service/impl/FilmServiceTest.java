package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Film;
import com.springapp.mvc.exception.ValidationException;
import com.springapp.mvc.repository.IFilmRepository;
import com.springapp.mvc.service.IFilmService;
import com.springapp.mvc.validation.IValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Y. Vovk on 19.09.15.
 * <p/>
 * Services' coverage only
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class,
        locations = "classpath:/application-test-context.xml")
public class FilmServiceTest {

    @Autowired
    private IFilmService filmService;

    @ReplaceWithMock
    @Autowired
    private IFilmRepository filmRepository;

    @Autowired
    private IValidator<Film> filmValidator;

    @Test
    public void testGet() {
        Film testFilm = new Film();
        testFilm.setId(1);

        Mockito.when(filmRepository.get(1)).thenReturn(testFilm);
        Film result = filmService.get(1);
        assertEquals(result, testFilm);
    }

    @Test
    public void testGetAll() {
        Film testFilm = new Film();
        testFilm.setId(1);

        List<Film> films = new ArrayList<>();
        films.add(testFilm);

        Mockito.when(filmRepository.getAll()).thenReturn(new ArrayList<>(films));
        List<Film> result = filmService.getAll();
        assertEquals(result.get(0), testFilm);
    }

    @Test(expected = ValidationException.class)
    public void testAddValidationError() {
        Film testFilm2 = new Film();
        testFilm2.setId(2);
        testFilm2.setGenre("drama");
        testFilm2.setName("Birdman");
        testFilm2.setReview("Great!");

        filmValidator.validate(testFilm2);
    }

    @Test
    public void testAdd() {
        Film testFilm2 = new Film();
        testFilm2.setId(2);
        testFilm2.setGenre("drama");
        testFilm2.setName("Birdman");
        testFilm2.setReview("Great!");
        testFilm2.setYear(2014);
        testFilm2.setMark(10);
        testFilm2.setDateSeen(new Date(System.currentTimeMillis()));

        filmValidator.validate(testFilm2);
    }

    @Test
    public void testUpdate() {
        Film testFilm2 = new Film();
        testFilm2.setId(2);
        testFilm2.setGenre("drama");
        testFilm2.setName("Birdman");
        testFilm2.setReview("Great!");
        testFilm2.setYear(2014);
        testFilm2.setMark(10);
        testFilm2.setDateSeen(new Date(System.currentTimeMillis()));

        filmValidator.validate(testFilm2);
    }

    @Test(expected = ValidationException.class)
    public void testUpdateValidationError() {
        Film testFilm2 = new Film();
        testFilm2.setId(2);
        testFilm2.setGenre("drama");
        testFilm2.setName("Birdman");
        testFilm2.setReview("Great!");

        filmValidator.validate(testFilm2);
    }

    @Test
    public void testDelete() {
        Film testFilm2 = new Film();
        testFilm2.setId(2);

        filmService.delete(testFilm2);
        Mockito.verify(filmRepository).delete(testFilm2);
    }

    @Test
    public void testDeleteAll() {
        Film testFilm2 = new Film();
        testFilm2.setId(1);

        filmService.deleteAll(new Integer[]{1});
        Mockito.verify(filmRepository).delete(testFilm2);
    }
}