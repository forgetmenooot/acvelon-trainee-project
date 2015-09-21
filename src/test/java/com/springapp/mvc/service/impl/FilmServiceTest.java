package com.springapp.mvc.service.impl;

import com.springapp.mvc.domain.Film;
import com.springapp.mvc.repository.IFilmRepository;
import com.springapp.mvc.service.IFilmService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Y. Vovk on 19.09.15.
 * <p/>
 * Services covering only
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

    @Before
    public void setUp() throws Exception {
        Film testFilm = new Film();
        testFilm.setId(1);

        List<Film> films = new ArrayList<>();
        films.add(testFilm);

        Mockito.when(filmRepository.getList()).thenReturn(films);
        Mockito.when(filmRepository.get(1)).thenReturn(testFilm);
    }

    @Test
    public void testGet() throws Exception {
        Film testFilm = new Film();
        testFilm.setId(1);

        Film result = filmService.get(1);
        assertEquals(result, testFilm);
    }

    @Test
    public void testGetList() throws Exception {
        Film testFilm = new Film();
        testFilm.setId(1);

        List<Film> result = filmService.getList();
        assertEquals(result.get(0), testFilm);
    }

    @Test
    public void testAdd() throws Exception {
        Film testFilm2 = new Film();
        testFilm2.setId(2);

        filmService.add(testFilm2);
        Mockito.verify(filmRepository).add(testFilm2);
    }

    @Test
    public void testDelete() throws Exception {
        Film testFilm2 = new Film();
        testFilm2.setId(2);

        filmService.delete(testFilm2);
        Mockito.verify(filmRepository).delete(testFilm2);
    }

    @Test
    public void testUpdate() throws Exception {
        Film testFilm2 = new Film();
        testFilm2.setId(2);

        filmService.update(testFilm2);
        Mockito.verify(filmRepository).update(testFilm2);
    }

    @Test
    public void testDelete1() throws Exception {
        Film testFilm2 = new Film();
        testFilm2.setId(1);

        filmService.delete(new Integer[]{1});
        Mockito.verify(filmRepository).delete(testFilm2);
    }
}