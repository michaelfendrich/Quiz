package quiz.entity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class IllegalExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getFieldError().getDefaultMessage());
    }
}
