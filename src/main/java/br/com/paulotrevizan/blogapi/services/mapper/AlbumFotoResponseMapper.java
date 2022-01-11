package br.com.paulotrevizan.blogapi.services.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.AlbumFoto;
import br.com.paulotrevizan.blogapi.dto.response.AlbumFotoResponse;
import br.com.paulotrevizan.blogapi.dto.response.FotoResponse;
import br.com.paulotrevizan.blogapi.dto.response.UsuarioResponse;

@Service
public class AlbumFotoResponseMapper {

  @Autowired
  private FotoResponseMapper fotoResponseMapper;

  public AlbumFotoResponse domainToResponse(AlbumFoto domain) {
    UsuarioResponse usuario = UsuarioResponse.builder()
            .id(domain.getUsuario().getId())
            .username(domain.getUsuario().getUsername())
            .nome(domain.getUsuario().getNome())
            .build();

    List<FotoResponse> fotos = new ArrayList<>();
    if (Optional.ofNullable(domain.getFotos()).isPresent()) {
      fotos = Collections.synchronizedList(domain.getFotos()
                .stream()
                .map(fotoResponseMapper::domainToResponse)
                .collect(Collectors.toList()));
    }

    return AlbumFotoResponse.builder()
            .id(domain.getId())
            .usuario(usuario)
            .nome(domain.getNome())
            .fotos(fotos)
            .build();
  }

}
