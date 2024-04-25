package br.com.senai.fullstack.senaifullstackeducationsemana11.dto.response;

public record LoginResponse(String tokenJWT, long tempoExpiracao) {
}

