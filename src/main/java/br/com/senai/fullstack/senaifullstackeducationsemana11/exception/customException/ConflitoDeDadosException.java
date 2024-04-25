package br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException;

public class ConflitoDeDadosException extends RuntimeException {

  public ConflitoDeDadosException() {
  }

  public ConflitoDeDadosException(String message) {
    super(message);
  }
}
