package br.com.senai.fullstack.senaifullstackeducationsemana11.controller;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.AlterarCadernoRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.IncluirCadernoRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.CadernoResponse;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.CadernoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cadernos")
public class CadernoController {

  private final CadernoService service;

  @GetMapping
  public ResponseEntity<List<CadernoResponse>> get(
    @RequestHeader(name = "Authorization") String token) {

    List<CadernoResponse> listaCadernos = service.buscarTodos(token.substring(7));

    return ResponseEntity.status(HttpStatus.OK).body(listaCadernos);
  }

  @GetMapping("{id}")
  public ResponseEntity<CadernoResponse> getId(
    @PathVariable Long id,
    @RequestHeader(name = "Authorization") String token) {

    CadernoResponse cadernoResponse = service.buscarPorId(id, token.substring(7));

    return ResponseEntity.status(HttpStatus.OK).body(cadernoResponse);
  }

  @PostMapping
  public ResponseEntity<CadernoResponse> post(
    @Validated @RequestBody IncluirCadernoRequest incluirCadernoRequest,
    @RequestHeader(name = "Authorization") String token) {

    CadernoResponse cadernoResponse = service.criar(incluirCadernoRequest, token.substring(7));

    return ResponseEntity.status(HttpStatus.CREATED).body(cadernoResponse);
  }

  @PutMapping("{id}")
  public ResponseEntity<CadernoResponse> put(
    @PathVariable Long id,
    @Validated @RequestBody AlterarCadernoRequest alterarCadernoRequest,
    @RequestHeader(name = "Authorization") String token) {

    CadernoResponse cadernoResponse = service.alterar(id, alterarCadernoRequest, token.substring(7));

    return ResponseEntity.status(HttpStatus.OK).body(cadernoResponse);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(
    @PathVariable Long id,
    @RequestHeader(name = "Authorization") String token) {

    service.apagar(id, token.substring(7));

    return ResponseEntity.noContent().build();
  }

}
