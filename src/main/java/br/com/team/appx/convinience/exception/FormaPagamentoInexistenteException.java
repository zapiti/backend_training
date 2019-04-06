package br.com.team.appx.convinience.exception;

public class FormaPagamentoInexistenteException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public FormaPagamentoInexistenteException() {
    super("Forma de pagamento inexistente.");
  }
}
