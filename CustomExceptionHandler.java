package com.flags.demo.core;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(FlagNotFoundException.class)
    protected ResponseEntity<Object> handleNoFlagFoundException(final Exception ex) {
        logger.info(ex.getClass().getName());
        //
        final String error = "Flag not found for the specified country";

        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ContinentNotFoundException.class)
    protected ResponseEntity<Object> handleNoCountryFoundException(final Exception ex) {
        logger.info(ex.getClass().getName());
        //
        final String error = "Flag not found for the specified country";

        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
        
    }
}
