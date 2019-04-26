package com.sky.parentalcontrol.service;

import com.sky.parentalcontrol.exceptions.ParentalControlException;
import com.sky.parentalcontrol.exceptions.TechnicalFailureException;
import com.sky.parentalcontrol.exceptions.TitleNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

    @InjectMocks
    ParentalControlService underTest;

    @Mock
    MovieService movieService;

    @Test
    public void shouldBeAbleToAccessMovie() throws Exception {

        ParentalControlLevel currentParentalControlLevel = ParentalControlLevel.UNIVERSAL;
        String movieId = "universal movie";

        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.UNIVERSAL.getCode());
        boolean result = underTest.isAllowedToAccessMovie(movieId,currentParentalControlLevel);

        assertEquals(true, result);

        currentParentalControlLevel = ParentalControlLevel.EIGHTEEN;
        movieId = "suitable for 18 movie";

        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.FIFTEEN.getCode());
        result = underTest.isAllowedToAccessMovie(movieId,currentParentalControlLevel);

        assertEquals(true, result);
    }


    @Test
    public void shouldNotBeAbleToAccessMovie() throws Exception {

        ParentalControlLevel currentParentalControlLevel = ParentalControlLevel.UNIVERSAL;
        String movieId = "universal movie";

        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.PARENTAL_GUIDANCE.getCode());
        boolean result = underTest.isAllowedToAccessMovie(movieId,currentParentalControlLevel);

        assertEquals(false, result);

        currentParentalControlLevel = ParentalControlLevel.TWELVE;
        movieId = "suitable for 15 movie";

        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.FIFTEEN.getCode());
        result = underTest.isAllowedToAccessMovie(movieId,currentParentalControlLevel);

        assertEquals(false, result);

    }

    @SuppressWarnings("unchecked")
	@Test(expected = ParentalControlException.class)
    public void shouldThrowExceptionWhenTitleNotFound() throws Exception {

        ParentalControlLevel currentParentalControlLevel = ParentalControlLevel.UNIVERSAL;
        String movieId = "not found movie";

        given(movieService.getParentalControlLevel(movieId)).willThrow(TitleNotFoundException.class);
        underTest.isAllowedToAccessMovie(movieId, currentParentalControlLevel);
    }

    @SuppressWarnings("unchecked")
	@Test
    public void shouldNotBeAbleToAccessMovieWhenThereIsTechnicalFailure() throws Exception{
        ParentalControlLevel currentParentalControlLevel = ParentalControlLevel.UNIVERSAL;
        String movieId = "universal movie";

        given(movieService.getParentalControlLevel(movieId)).willThrow(TechnicalFailureException.class);
        boolean result = underTest.isAllowedToAccessMovie(movieId, currentParentalControlLevel);

        assertEquals(false, result);
    }


}