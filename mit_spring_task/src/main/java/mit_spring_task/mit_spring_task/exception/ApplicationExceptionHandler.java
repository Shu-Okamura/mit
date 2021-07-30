package mit_spring_task.mit_spring_task.exception;

import mit_spring_task.mit_spring_task.exception.model.ErrorResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
        if(body == null){
            body = ex.getClass().toString();
        }
        return super.handleExceptionInternal(ex,body,headers,status,request);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, "MethodArgumentNotValid",headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidation(ConstraintViolationException ex, WebRequest request){
        ErrorResource errorResource = ErrorResource.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getConstraintViolations());
        return handleExceptionInternal(ex,errorResource,null,HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationPost(MethodArgumentNotValidException ex, WebRequest request){
//        ErrorResource errorResource = ErrorResource.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getConstraintViolations());
        return handleExceptionInternal(ex,"",null,HttpStatus.BAD_REQUEST,request);
//        return null;
    }

}
