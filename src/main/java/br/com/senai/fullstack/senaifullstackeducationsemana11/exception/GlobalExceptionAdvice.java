package br.com.senai.fullstack.senaifullstackeducationsemana11.exception;

import br.com.senai.fullstack.senaifullstackeducationsemana11.dto.ErroDto;
import br.com.senai.fullstack.senaifullstackeducationsemana11.dto.ExceptionDto;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.ConflitoDeDadosException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.RequisicaoInvalidaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
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

  @ExceptionHandler(RequisicaoInvalidaException.class)
  public ResponseEntity<?> handle(RequisicaoInvalidaException e) {
    ExceptionDto exceptionDto = ExceptionDto.builder()
      .codigo("400")
      .mensagem(e.getMessage())
      .build();
    log.error("[STATUS 400] Dados ausentes ou incorretos: {}", e.getMessage());
    return ResponseEntity.status(400).body(exceptionDto);
  }

  @ExceptionHandler(ConflitoDeDadosException.class)
  public ResponseEntity<?> handle(ConflitoDeDadosException e) {
    ExceptionDto exceptionDto = ExceptionDto.builder()
      .codigo("403")
      .mensagem(e.getMessage())
      .build();
    log.error("[STATUS 403] Conflito de dados: {}", e.getMessage());
    return ResponseEntity.status(400).body(exceptionDto);
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
