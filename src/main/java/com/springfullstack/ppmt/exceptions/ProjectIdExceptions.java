package com.springfullstack.ppmt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdExceptions  extends RuntimeException{

  public ProjectIdExceptions(String message) {
    super(message);
  }
}
