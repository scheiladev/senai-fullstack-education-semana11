package br.com.senai.fullstack.senaifullstackeducationsemana11.exception;

import br.com.senai.fullstack.senaifullstackeducationsemana11.dto.ErroDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handler(Exception e) {
    ErroDto erro = ErroDto.builder()
        .codigo("500")
        .mensagem(e.getMessage())
        .build();
    return ResponseEntity.status(500).body(erro);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handler(NotFoundException e) {
    ErroDto erro = ErroDto.builder()
        .codigo("404")
        .mensagem(e.getMessage())
        .build();
    return ResponseEntity.status(404).body(erro);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> handler(DataIntegrityViolationException e) {
    ErroDto erro = ErroDto.builder()
        .codigo("400")
        .mensagem(e.getMessage())
        .build();
    return ResponseEntity.status(400).body(erro);
  }
}
