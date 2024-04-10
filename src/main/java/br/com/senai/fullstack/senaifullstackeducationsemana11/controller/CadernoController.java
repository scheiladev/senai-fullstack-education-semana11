package br.com.senai.fullstack.senaifullstackeducationsemana11.controller;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.CadernoEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.CadernoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cadernos")
public class CadernoController {

  private final CadernoService service;

  @GetMapping
  public ResponseEntity<List<CadernoEntity>> get() {
    return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
  }

  @GetMapping("{id}")
  public ResponseEntity<CadernoEntity> getId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
  }

  @PostMapping
  public ResponseEntity<CadernoEntity> post(@RequestBody CadernoEntity entity) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(entity));
  }

  @PutMapping("{id}")
  public ResponseEntity<CadernoEntity> put(@PathVariable Long id, @RequestBody CadernoEntity entity) {
    return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, entity));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.apagar(id);
    return ResponseEntity.noContent().build();
  }

}
