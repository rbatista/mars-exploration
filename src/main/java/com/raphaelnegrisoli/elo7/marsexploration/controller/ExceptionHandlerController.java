package com.raphaelnegrisoli.elo7.marsexploration.controller;

import com.raphaelnegrisoli.elo7.marsexploration.controller.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse handleInternalServerError(final Exception e) {

        LOGGER.error("Internal Server Error", e);
        return new ErrorResponse("Internal Server Error");
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(final IllegalArgumentException e) {

        LOGGER.error("Illegal argument", e);
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public ErrorResponse handleIllegalStateException(final IllegalStateException e) {

        LOGGER.error("Illegal state", e);
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleMethodArgumentInvalid(final MethodArgumentNotValidException e) {

        LOGGER.error("Invalid argument", e);
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorResponse handleInvalidRequest(final HttpMessageNotReadableException e) {

        LOGGER.error("Invalid request", e);
        return new ErrorResponse(e.getMessage());
    }

}
