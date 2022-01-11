package br.com.paulotrevizan.blogapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public AlreadyExistsException(final String error) {
    super(error);
  }

}

