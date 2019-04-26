package com.sky.parentalcontrol.service;

import com.sky.parentalcontrol.exceptions.TechnicalFailureException;
import com.sky.parentalcontrol.exceptions.TitleNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieServiceImplTest {

    private  MovieService underTest;

    @Before
    public void setUp() {
        underTest = new MovieServiceImpl();
    }

    @Test
    public void getParentalControlLevel() throws TechnicalFailureException, TitleNotFoundException {

        String movieId = "movieId";
        String result = underTest.getParentalControlLevel(movieId);
        assertNotNull(result);
    }
}