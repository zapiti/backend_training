package br.com.team.appx.convinience.exception;

public class CategoriaInexistenteException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CategoriaInexistenteException() {
    super("Categoria inexistente.");
  }
}
