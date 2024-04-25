package br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException;

public class RequisicaoInvalidaException extends RuntimeException {

  public RequisicaoInvalidaException() {
  }

  public RequisicaoInvalidaException(String message) {
    super(message);
  }
}
