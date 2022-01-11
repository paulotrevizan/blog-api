package br.com.paulotrevizan.blogapi.services.mapper;

import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.Usuario;
import br.com.paulotrevizan.blogapi.dto.response.UsuarioResponse;

@Service
public class UsuarioResponseMapper {

  public UsuarioResponse domainToResponse(Usuario domain) {
    return UsuarioResponse.builder()
            .id(domain.getId())
            .username(domain.getUsername())
            .nome(domain.getNome())
            .build();
  }

}
