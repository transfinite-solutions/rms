package one.transfinite.rms.execption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiBadRequestException.class})
    public ResponseEntity<Object> handleApiBadRequestException(ApiBadRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        //Payload
        ApiException apiException = new ApiException(
          e.getMessage(),
                badRequest,
          LocalDateTime.now()
        );

        //Return response
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ApiBadRequestException e) {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        //Payload
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        //Return response
        return new ResponseEntity<>(apiException, badRequest);
    }
}
