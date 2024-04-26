package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.UsuarioRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.UsuarioResponse;

public interface UsuarioService {

  UsuarioResponse cadastroUsuario(UsuarioRequest usuarioRequest);

}
