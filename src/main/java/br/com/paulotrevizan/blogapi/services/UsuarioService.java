package br.com.paulotrevizan.blogapi.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.Usuario;
import br.com.paulotrevizan.blogapi.dto.UsuarioRequest;
import br.com.paulotrevizan.blogapi.dto.response.UsuarioResponse;
import br.com.paulotrevizan.blogapi.exceptions.AlreadyExistsException;
import br.com.paulotrevizan.blogapi.exceptions.NotFoundException;
import br.com.paulotrevizan.blogapi.repositories.UsuarioRepository;
import br.com.paulotrevizan.blogapi.services.mapper.UsuarioResponseMapper;

import lombok.RequiredArgsConstructor;

@Lazy
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

  private static final String NOT_FOUND_MSG = "Usuário não encontrado";
  private static final String ALREADY_EXISTS_MSG = "Usuário já cadastrado";

  private final UsuarioRepository usuarioRepository;
  private final UsuarioResponseMapper usuarioResponseMapper;

  public Usuario insert(final UsuarioRequest request) {
    if (usuarioRepository.existsByUsername(request.getUsername())) {
      throw new AlreadyExistsException(ALREADY_EXISTS_MSG);
    }

    Usuario user = Usuario.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .nome(request.getNome())
            .build();

    return usuarioRepository.save(user);
  }

  public Usuario getUsuarioById(Integer id) {
    return usuarioRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(NOT_FOUND_MSG));
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    Usuario user = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MSG));

    return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles("USER")
            .build();
  }

  public UsuarioResponse getUsuarioByUsername(String username) {
    return usuarioRepository.findByUsername(username)
            .map(usuarioResponseMapper::domainToResponse)
            .orElseThrow(() -> new NotFoundException(NOT_FOUND_MSG));
  }

}
