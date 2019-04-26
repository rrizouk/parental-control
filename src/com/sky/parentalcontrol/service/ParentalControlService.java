package com.sky.parentalcontrol.service;

import static com.sky.parentalcontrol.service.ParentalControlLevel.UNIVERSAL;

import com.sky.parentalcontrol.exceptions.ParentalControlException;
import com.sky.parentalcontrol.exceptions.TechnicalFailureException;
import com.sky.parentalcontrol.exceptions.TitleNotFoundException;

public class ParentalControlService{

    private final MovieService movieService;

    public ParentalControlService(MovieService movieService) {
        this.movieService = movieService;
    }

    public boolean isAllowedToAccessMovie(String movieId, ParentalControlLevel currentParentalControlLevel) throws TechnicalFailureException, TitleNotFoundException {
        String parentalControlLevel;
        try{
            parentalControlLevel = this.movieService.getParentalControlLevel(movieId);
        }catch(TitleNotFoundException ex){
            throw new ParentalControlException(ex.getMessage(),ex.getCause());
        }catch(TechnicalFailureException ex){
            // log warning here
            return false;
        }
       switch(currentParentalControlLevel){
           case UNIVERSAL:
               return currentParentalControlLevel.getCode().equals(parentalControlLevel);
           case PARENTAL_GUIDANCE:
               return UNIVERSAL.getCode().equals(parentalControlLevel) || !currentParentalControlLevel.getCode().equals(parentalControlLevel);
           case TWELVE:
           case FIFTEEN:
           case EIGHTEEN:
               if(isDigit(parentalControlLevel)){
                   return !(Integer.valueOf(parentalControlLevel) > Integer.valueOf(currentParentalControlLevel.getCode())) ;
               }
       }
        return true;
    }

    private boolean isDigit(String str) {
        try{
            Integer.parseInt(str);
        }catch(NumberFormatException ex){
           return false;
        }
        return true;
    }
}