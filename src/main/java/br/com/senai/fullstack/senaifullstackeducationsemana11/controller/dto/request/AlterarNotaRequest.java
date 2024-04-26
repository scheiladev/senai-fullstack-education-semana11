package br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request;

public record AlterarNotaRequest(Long id, String title, String content, Long cadernoId) {
}
