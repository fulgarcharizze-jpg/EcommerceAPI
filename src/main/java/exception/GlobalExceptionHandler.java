package com.ws101.FulgarLim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ✅ 404 NOT FOUND
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle404(EntityNotFoundException e) {
        Map<String, Object> err = new HashMap<>();
        err.put("status", 404);
        err.put("error", "Not Found");
        err.put("message", e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    // ✅ 400 BAD REQUEST
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handle400(DataIntegrityViolationException e) {
        Map<String, Object> err = new HashMap<>();
        err.put("status", 400);
        err.put("error", "Bad Request");
        err.put("message", "Invalid input or duplicate data");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
