package br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response;

public record LoginResponse(String tokenJWT, long tempoExpiracao) {
}

