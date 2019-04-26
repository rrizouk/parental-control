package com.sky.parentalcontrol.service;

import com.sky.parentalcontrol.exceptions.TechnicalFailureException;
import com.sky.parentalcontrol.exceptions.TitleNotFoundException;

public interface MovieService {
	
	String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
