package online.beautyskin.beauty.exception;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class APIHandleException {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequestException(MethodArgumentNotValidException exception){
        String message = "";
        for (FieldError error: exception.getBindingResult().getFieldErrors()){
            message += error.getDefaultMessage()+"\n";
        }
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

}
