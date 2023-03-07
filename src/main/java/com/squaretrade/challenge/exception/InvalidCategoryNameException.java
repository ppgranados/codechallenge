package com.squaretrade.challenge.exception;


import lombok.extern.slf4j.Slf4j;

@Slf4j
// TODO: Add http response code as 400
public class InvalidCategoryNameException extends RuntimeException{
    public InvalidCategoryNameException(String message) {
        super(message);
        log.error(message);
    }
}
