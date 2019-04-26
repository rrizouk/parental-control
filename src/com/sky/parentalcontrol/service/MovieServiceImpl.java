package com.sky.parentalcontrol.service;

import com.sky.parentalcontrol.exceptions.TechnicalFailureException;
import com.sky.parentalcontrol.exceptions.TitleNotFoundException;

public class MovieServiceImpl implements MovieService {

    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        return ParentalControlLevel.UNIVERSAL.getCode(); // return universal for the sake of simplicity, in reality this will call another service
    }
}
