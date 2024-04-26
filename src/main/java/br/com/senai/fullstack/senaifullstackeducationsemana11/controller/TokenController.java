package br.com.senai.fullstack.senaifullstackeducationsemana11.controller;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.LoginRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.LoginResponse;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TokenController {

  private final TokenService tokenService;

  @PostMapping("login")
  public ResponseEntity<LoginResponse> gerarToken(
    @RequestBody LoginRequest loginRequest) {

    LoginResponse loginResponse = tokenService.gerarToken(loginRequest);

    return ResponseEntity.ok(loginResponse);
  }

}



