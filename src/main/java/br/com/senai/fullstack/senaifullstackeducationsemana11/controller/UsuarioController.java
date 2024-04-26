package br.com.senai.fullstack.senaifullstackeducationsemana11.controller;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.UsuarioRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.UsuarioResponse;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class UsuarioController {

  private final UsuarioService usuarioService;

  @PostMapping("cadastro")
  public ResponseEntity<UsuarioResponse> cadastroUsuario(
    @Validated @RequestBody UsuarioRequest usuarioRequest) {

    UsuarioResponse usuarioResponse = usuarioService.cadastroUsuario(usuarioRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
  }

}