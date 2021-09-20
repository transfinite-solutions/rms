package one.transfinite.rms.execption;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    public ApiException() {
    }

    public ApiException(String message, HttpStatus httpStatus, LocalDateTime localDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
