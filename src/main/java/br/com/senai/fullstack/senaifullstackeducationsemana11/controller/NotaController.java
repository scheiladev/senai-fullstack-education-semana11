package br.com.senai.fullstack.senaifullstackeducationsemana11.controller;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.AlterarNotaRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.IncluirNotaRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.NotaResponse;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("notas")
public class NotaController {

  private final NotaService service;

  @GetMapping
  public ResponseEntity<List<NotaResponse>> get(
    @RequestHeader(name = "Authorization") String token) {

    List<NotaResponse> listaNotas = service.buscarTodos(token.substring(7));

    return ResponseEntity.status(HttpStatus.OK).body(listaNotas);
  }

  @GetMapping("{id}")
  public ResponseEntity<NotaResponse> getId(
    @PathVariable Long id,
    @RequestHeader(name = "Authorization") String token) {

    NotaResponse notaResponse = service.buscarPorId(id,token.substring(7));

    return ResponseEntity.status(HttpStatus.OK).body(notaResponse);
  }

  @PostMapping
  public ResponseEntity<NotaResponse> post(
    @Validated @RequestBody IncluirNotaRequest incluirNotaRequest,
    @RequestHeader(name = "Authorization") String token) {

    NotaResponse notaResponse = service.criar(incluirNotaRequest, token.substring(7));

    return ResponseEntity.status(HttpStatus.CREATED).body(notaResponse);
  }

  @PutMapping("{id}")
  public ResponseEntity<NotaResponse> put(
    @PathVariable Long id,
    @Validated @RequestBody AlterarNotaRequest alterarNotaRequest,
    @RequestHeader(name = "Authorization") String token) {

    NotaResponse notaResponse = service.alterar(id, alterarNotaRequest, token.substring(7));

    return ResponseEntity.status(HttpStatus.OK).body(notaResponse);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(
    @PathVariable Long id,
    @RequestHeader(name = "Authorization") String token) {

    service.apagar(id, token.substring(7));

    return ResponseEntity.noContent().build();
  }
}
