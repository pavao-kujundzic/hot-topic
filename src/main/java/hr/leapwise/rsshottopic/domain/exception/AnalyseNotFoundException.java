package hr.leapwise.rsshottopic.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AnalyseNotFoundException extends RuntimeException {
    public AnalyseNotFoundException(String message) { super(message);}
}
