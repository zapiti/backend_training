package br.com.team.appx.convinience.exception;

public class UserInexistenteException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UserInexistenteException(String msg) {
    super(msg);
  }
  public UserInexistenteException() {
    super("Usuario ou senha válidos");
  }
}
