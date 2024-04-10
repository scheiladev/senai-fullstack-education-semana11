package br.com.senai.fullstack.senaifullstackeducationsemana11.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {
  }

  public NotFoundException(String message) {
    super(message);
  }
}
