package br.com.paulotrevizan.blogapi.services.mapper;

import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.ComentarioPostagem;
import br.com.paulotrevizan.blogapi.dto.response.ComentarioPostagemResponse;
import br.com.paulotrevizan.blogapi.dto.response.UsuarioResponse;

@Service
public class ComentarioPostagemResponseMapper {

  public ComentarioPostagemResponse domainToResponse(ComentarioPostagem domain) {
    UsuarioResponse usuario = UsuarioResponse.builder()
            .id(domain.getUsuario().getId())
            .username(domain.getUsuario().getUsername())
            .nome(domain.getUsuario().getNome())
            .build();

    return ComentarioPostagemResponse.builder()
            .id(domain.getId())
            .usuario(usuario)
            .conteudo(domain.getConteudo())
            .data(domain.getData())
            .build();
  }

}
