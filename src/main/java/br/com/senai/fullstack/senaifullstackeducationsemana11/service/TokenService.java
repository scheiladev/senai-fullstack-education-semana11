package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.LoginRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.LoginResponse;

public interface TokenService {

  LoginResponse gerarToken(LoginRequest loginRequest);

  String buscarCampo(String token, String claim);

}
