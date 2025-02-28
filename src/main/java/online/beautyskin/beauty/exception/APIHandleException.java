package online.beautyskin.beauty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice // dùng để đánh dấu class có nhiệm vụ handle exception của API
public class APIHandleException {


    @ExceptionHandler(MethodArgumentNotValidException.class) // dùng dể đánh dấu exception
    public ResponseEntity handleBadRequestException(MethodArgumentNotValidException exception) {
        String message = "";

        for(FieldError err : exception.getBindingResult().getFieldErrors()){
            message += err.getDefaultMessage() + "\n";
        }

        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }


    // duplicate Product.code
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class) // dùng dể đánh dấu exception
    public ResponseEntity handleDuplicate(SQLIntegrityConstraintViolationException exception) {
        //exception.getMessage().con
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handleAuthorizeException(AuthException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
