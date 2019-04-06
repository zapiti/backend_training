package br.com.team.appx.convinience.exception;

public class DadoNaoEncontradoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DadoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public DadoNaoEncontradoException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
