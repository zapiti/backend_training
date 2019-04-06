package br.com.team.appx.convinience.exception;

public class OperacaoNaoPermitidaException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public OperacaoNaoPermitidaException() {
    super("Operação não permitida");
  }
  
  public OperacaoNaoPermitidaException(String mensagem) {
    super("Operação não permitida: "+ mensagem);
  }
}
