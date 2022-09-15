package com.example.orders.advisor;



import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class OrderAdvisor extends ResponseEntityExceptionHandler {



   /**
     * Method to handle BaseCommonInvalidException which is thrown when header
     * validation or authentication fails
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {



       Map<String, Object> body = new LinkedHashMap<>();
        body.put("TIMESTAMP", LocalDateTime.now());
        body.put("MESSAGE", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}