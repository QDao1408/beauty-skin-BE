package online.beautyskin.beauty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIHandleException {
    @ExceptionHandler(MethodArgumentNotValidException.class) // dùng dể đánh dấu exception
    public ResponseEntity handleBadRequestException(MethodArgumentNotValidException exception) {
        String message = "";

        for(FieldError err : exception.getBindingResult().getFieldErrors()){
            message += err.getDefaultMessage() + "\n";
        }

        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
}
