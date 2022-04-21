package jpa.imform.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerErrorAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(MemberNotFoundException.class)
  public void handleMemberNotFoundException() {

  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ReviewNotFoundException.class)
  public void handleReviewNotFoundException() {

  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(InvalidTokenException.class)
  public void handleInvalidAccessTokenException() {
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(MissingRequestHeaderException.class)
  public void handleMissingRequestHeaderException() {

  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(LoginFailException.class)
  public void loginFailBadRequest() {

  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public void WrongPathIdBadRequest() {

  }

}
