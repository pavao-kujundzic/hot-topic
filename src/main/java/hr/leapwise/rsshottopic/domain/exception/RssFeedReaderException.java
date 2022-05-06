package hr.leapwise.rsshottopic.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RssFeedReaderException extends RuntimeException{
    public RssFeedReaderException(String message){super(message);}
}
