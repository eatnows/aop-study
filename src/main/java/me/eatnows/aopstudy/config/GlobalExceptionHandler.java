package me.eatnows.aopstudy.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// Exception 낚아채기
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String 요청잘못(IllegalArgumentException e) {
        return e.getMessage();
    }
}
