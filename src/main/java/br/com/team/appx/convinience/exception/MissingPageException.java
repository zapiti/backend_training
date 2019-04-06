package br.com.team.appx.convinience.exception;

public class MissingPageException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MissingPageException(String msg) {
    super(msg);
  }
}
