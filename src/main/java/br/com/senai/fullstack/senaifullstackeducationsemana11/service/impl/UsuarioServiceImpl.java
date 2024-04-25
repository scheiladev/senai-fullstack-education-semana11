package br.com.senai.fullstack.senaifullstackeducationsemana11.service.impl;

import br.com.senai.fullstack.senaifullstackeducationsemana11.dto.request.UsuarioRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.dto.response.UsuarioResponse;
import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.UsuarioEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.ConflitoDeDadosException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.RequisicaoInvalidaException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.PerfilRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.UsuarioRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.TokenService;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

  private final BCryptPasswordEncoder bCryptEncoder;
  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final TokenService tokenService;

  public UsuarioResponse cadastroUsuario(UsuarioRequest usuarioRequest) {

    if (usuarioRequest.login() == null) {
      throw new RequisicaoInvalidaException("Campo 'login' é obrigatório.");
    }

    if (usuarioRequest.senha() == null) {
      throw new RequisicaoInvalidaException("Campo 'senha' é obrigatório.");
    }

    if (usuarioRequest.perfil() == null) {
      throw new RequisicaoInvalidaException("Campo 'perfil' é obrigatório.");
    }

    if (usuarioRepository.findByLogin(usuarioRequest.login()).isPresent()) {
      throw new ConflitoDeDadosException("Usuário já existe.");
    }

    UsuarioEntity usuario = new UsuarioEntity();
    usuario.setLogin(usuarioRequest.login());
    usuario.setSenha(bCryptEncoder.encode(usuarioRequest.senha()));
    usuario.setPerfil(perfilRepository.findByNome(usuarioRequest.perfil())
      .orElseThrow(() -> new RequisicaoInvalidaException("Erro: perfil inválido ou inexistente."))
    );

    usuarioRepository.save(usuario);

    return new UsuarioResponse(usuario.getId(), usuario.getLogin(), usuario.getPerfil().getNome());
  }

}