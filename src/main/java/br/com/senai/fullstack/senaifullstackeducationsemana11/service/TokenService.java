package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.dto.request.LoginRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.dto.response.LoginResponse;

public interface TokenService {

  LoginResponse gerarToken(LoginRequest loginRequest);

  String buscarCampo(String token, String claim);

}
